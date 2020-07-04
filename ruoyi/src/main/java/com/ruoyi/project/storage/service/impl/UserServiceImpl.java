package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.project.common.enums.RecordPointWay;
import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.common.util.ParameterUtil;
import com.ruoyi.project.storage.domain.User;
import com.ruoyi.project.storage.mapper.UserMapper;
import com.ruoyi.project.storage.service.IPointRecordService;
import com.ruoyi.project.storage.service.IUserService;
import com.ruoyi.project.system.domain.SysUserRole;
import com.ruoyi.project.system.mapper.SysUserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    /**
     * token验证Service
     */
    private final TokenService tokenService;

    /**
     * 用户Mapper
     */
    private final UserMapper userMapper;

    /**
     * 用户角色关联Mapper
     */
    private final SysUserRoleMapper userRoleMapper;

    /**
     * 积分记录Service
     */
    private final IPointRecordService pointRecordService;

    /**
     * 构造方法注入
     *
     * @param userMapper         用户Mapper
     * @param userRoleMapper     用户角色关联Mapper
     * @param tokenService       token验证Service
     * @param pointRecordService 积分记录Service
     */
    @Autowired
    public UserServiceImpl(UserMapper userMapper, SysUserRoleMapper userRoleMapper, TokenService tokenService, IPointRecordService pointRecordService) {
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.tokenService = tokenService;
        this.pointRecordService = pointRecordService;
    }


    /**
     * 根据条件分页查询用户列表
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param user         用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<User> selectUserList(TerminalEnum terminalEnum, User user) {
        // 根据终端，设置用户类型
        user.setUserType(terminalEnum.getType());
        // 根据条件分页查询用户列表并返回
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户信息
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param user         用户信息
     * @return 结果（插入条数）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(TerminalEnum terminalEnum, User user) {
        // 如果为手机注册端
        if (TerminalEnum.REGIST.equals(terminalEnum)) {
            // 设置注册基础字段
            ParameterUtil.setRegistEntity(user);
        } else {
            // 设置创建基础字段
            ParameterUtil.setCreateEntity(user);
        }
        // 根据终端，设置部门id
        user.setDeptId(terminalEnum.getDept());
        // 根据终端，设置用户类型
        user.setUserType(terminalEnum.getType());
        // 根据终端，设置加密后的用户初始密码
        user.setPassword(SecurityUtils.encryptPassword("".equals(terminalEnum.getPassword()) ? user.getPassword() : terminalEnum.getPassword()));
        // 设置启用
        user.setStatus("0");
        // 根据终端，设置当前积分
        user.setCurrentPoints(terminalEnum.getPoint());
        // 插入用户返回条数
        int userCount = userMapper.insertUser(user);
        // 结果大于0则继续
        if (userCount > 0) {
            // 如果为注册，则插入积分记录表
            if(TerminalEnum.REGIST.equals(terminalEnum)) {
                // 插入积分记录表并返回条数
                userCount = pointRecordService.insertPointRecord(RecordPointWay.REGIST, user.getUserId(), terminalEnum.getPoint(), null, null);
            }
            // 结果大于0则继续
            if (userCount > 0) {
                // 实例化List<SysUserRole>
                List<SysUserRole> list = new ArrayList<>();
                // 实例化SysUserRole
                SysUserRole sysUserRole = new SysUserRole();
                // 设置用户id
                sysUserRole.setUserId(user.getUserId());
                // 设置角色id
                sysUserRole.setRoleId(terminalEnum.getRole());
                // list赋值
                list.add(sysUserRole);
                // 插入用户角色关联表并返回插入条数
                return userRoleMapper.batchUserRole(list);
            } else {
                // 手动抛异常
                throw new CustomException("插入积分记录表失败");
            }
        } else {
            // 手动抛异常
            throw new CustomException("插入用户表失败");
        }
    }

    /**
     * 修改用户信息
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param user         用户信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(TerminalEnum terminalEnum, User user) {
        // 设置更新基础字段
        ParameterUtil.setUpdateEntity(user);
        // 根据终端，设置用户类型
        user.setUserType(terminalEnum.getType());
        // 修改条数
        int count = userMapper.updateUser(user);
        // 乐观锁判断
        if (count == 0) {
            log.error("UserServiceImpl.updateUser failed: 乐观锁");
            // 抛出异常标记乐观锁
            throw new CustomException("用户已被他人率先修改，请刷新后重试", HttpStatus.CONFLICT);
        }
        // 返回修改条数
        return count;
    }

    /**
     * 批量用户启用/停用
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param operate      操作类型（"enable"：启用；"disable"：停用）
     * @param userIds      需要启用/停用的用户ID
     * @return 结果
     */
    @Override
    public int operateUserByIds(TerminalEnum terminalEnum, String operate, Long[] userIds) {
        // 返回修改条数
        return userMapper.operateUserByUserIds(ParameterUtil.getBatchUpdateMapByOperateUserIds(terminalEnum, operate, userIds));
    }

    /**
     * 批量删除用户信息
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param userIds      需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deleteUserByUserIds(TerminalEnum terminalEnum, Long[] userIds) {
        // 返回修改条数
        return userMapper.deleteUserByUserIds(ParameterUtil.getBatchUpdateMapByUserIds(terminalEnum, userIds));
    }

    /**
     * 批量重置用户密码
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param userIds      需要重置的用户ID
     * @return 结果
     */
    @Override
    public int resetUserByUserIds(TerminalEnum terminalEnum, Long[] userIds) {
        // 返回修改条数
        return userMapper.resetUserByUserIds(ParameterUtil.getBatchResetMapByUserIds(terminalEnum, userIds));
    }

    /**
     * 修改密码
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param oldPassword  旧密码
     * @param newPassword  新密码
     * @return 结果
     */
    @Override
    public int updateUserPassword(TerminalEnum terminalEnum, String oldPassword, String newPassword) {
        // 通过token获取用户身份信息
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 获取用户名
        String userName = loginUser.getUsername();
        // 获取密码
        String password = loginUser.getPassword();
        // 判断旧密码是否正确
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            throw new CustomException("修改密码失败，旧密码错误", HttpStatus.ERROR);
        }
        // 判断新旧密码是否相同
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            throw new CustomException("修改密码失败，新密码不能与旧密码相同", HttpStatus.ERROR);
        }
        // 定义更新参数
        User user = new User();
        // 设置用户名
        user.setUserName(userName);
        // 设置密码
        user.setPassword(SecurityUtils.encryptPassword(newPassword));
        // 设置用户类型
        user.setUserType(terminalEnum.getType());
        // 设置更新基础字段
        ParameterUtil.setUpdateEntity(user);
        // 判断更新密码结果
        if (userMapper.updateUserPassword(user) > 0) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            // 设置用户身份信息
            tokenService.setLoginUser(loginUser);
            // 返回操作结果
            return 1;
        }
        throw new CustomException("修改密码异常，请联系管理员", HttpStatus.ERROR);
    }

    /**
     * 获取当前用户积分
     *
     * @param userId 用户id
     * @return 当前用户积分
     */
    @Override
    public long getUserPoints(Long userId) {
        // 获取当前用户积分
        Long userPoints = userMapper.getUserPoints(userId);
        // 返回当前用户积分
        return userPoints == null ? 0L : userPoints;
    }

}
