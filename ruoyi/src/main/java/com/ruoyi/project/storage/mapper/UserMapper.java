package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户Mapper接口
 *
 * @author wangdong
 * @date 2020-05-02
 */
public interface UserMapper {

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<User> selectUserList(User user);

    /**
     * 插入用户
     *
     * @param user 用户对象
     * @return 条数
     */
    int insertUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(User user);

    /**
     * 批量删除用户信息
     *
     * @param map userIds:需要删除的用户ID
     *            userType:用户类型（01：后台端用户；02：手机端用户）
     *            user:操作人
     *            time:操作时间
     * @return 结果
     */
    int deleteUserByUserIds(Map<String, Object> map);

    /**
     * 批量重置用户密码
     *
     * @param map userIds:需要删除的用户ID
     *            userType:用户类型（01：后台端用户；02：手机端用户）
     *            password:加密后的初始密码
     *            user:操作人
     *            time:操作时间
     * @return 结果
     */
    int resetUserByUserIds(Map<String, Object> map);

    /**
     * 批量启用/停用用户
     *
     * @param map userIds:需要启用/停用的用户ID
     *            userType:用户类型（01：后台端用户；02：手机端用户）
     *            operate:操作类型（"enable"：启用；"disable"：停用）
     *            user:操作人
     *            time:操作时间
     * @return 结果
     */
    int operateUserByUserIds(Map<String, Object> map);

    /**
     * 更新用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUserPassword(User user);

    /**
     * 获取用户积分
     *
     * @param userId 用户id
     * @return 结果
     */
    Long getUserPoints(Long userId);

    /**
     * 操作用户积分
     *
     * @param user 用户信息
     * @return 结果
     */
    int operateUserPoints(User user);
}
