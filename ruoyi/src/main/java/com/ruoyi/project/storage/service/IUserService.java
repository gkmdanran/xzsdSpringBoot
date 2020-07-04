package com.ruoyi.project.storage.service;

import com.ruoyi.project.common.enums.TerminalEnum;
import com.ruoyi.project.storage.domain.User;

import java.util.List;

/**
 * 用户Service接口
 *
 * @author wangdong
 * @date 2020-05-02
 */
public interface IUserService {

    /**
     * 根据条件分页查询用户列表
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param user     用户信息
     * @return 用户信息集合信息
     */
    List<User> selectUserList(TerminalEnum terminalEnum, User user);

    /**
     * 新增用户信息
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param user     用户信息
     * @return 条数
     */
    int insertUser(TerminalEnum terminalEnum, User user);

    /**
     * 修改用户信息
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param user     用户信息
     * @return 结果
     */
    int updateUser(TerminalEnum terminalEnum, User user);

    /**
     * 批量用户启用/停用
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param operate  操作类型（"enable"：启用；"disable"：停用）
     * @param userIds  需要启用/停用的用户ID
     * @return 结果
     */
    int operateUserByIds(TerminalEnum terminalEnum, String operate, Long[] userIds);

    /**
     * 批量删除用户信息
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param userIds  需要删除的用户ID
     * @return 结果
     */
    int deleteUserByUserIds(TerminalEnum terminalEnum, Long[] userIds);

    /**
     * 批量重置用户密码
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param userIds  需要重置的用户ID
     * @return 结果
     */
    int resetUserByUserIds(TerminalEnum terminalEnum, Long[] userIds);

    /**
     * 修改密码
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 结果
     */
    int updateUserPassword(TerminalEnum terminalEnum, String oldPassword, String newPassword);

    /**
     * 获取当前用户积分
     *
     * @param userId 用户id
     * @return 当前用户积分
     */
    long getUserPoints(Long userId);

}
