package com.ruoyi.project.common.util;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.common.enums.TerminalEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 参数工具类
 *
 * @author wangdong
 * @date 2020.04.28
 */
public class ParameterUtil {

    /**
     * 通过传入id返回Map<String, Object>
     *
     * @param id 传入id
     * @return Map
     */
    public static Map<String, Object> getUpdateMapById(Long id) {
        // 初始化map
        Map<String, Object> map = initMap();
        // 设置id
        map.put("id", id);
        // 返回map
        return map;
    }

    /**
     * 通过传入id数组返回Map<String, Object>
     *
     * @param ids 传入id数组
     * @return Map
     */
    public static Map<String, Object> getBatchUpdateMapByIds(Long[] ids) {
        // 初始化map
        Map<String, Object> map = initMap();
        // 设置id数组
        map.put("ids", ids);
        // 返回map
        return map;
    }

    /**
     * 通过传入id数组及操作返回Map<String, Object>
     *
     * @param operate 操作类型（"enable"：启用；"disable"：停用）
     * @param ids     传入id数组
     * @return Map
     */
    public static Map<String, Object> getBatchUpdateMapByOperateIds(String operate, Long[] ids) {
        // 初始化map
        Map<String, Object> map = initMap();
        // 设置操作类型
        map.put("operate", operate);
        // 设置id数组
        map.put("ids", ids);
        // 返回map
        return map;
    }

    /**
     * 通过传入userId返回Map<String, Object>
     *
     * @param userId 传入userId
     * @return Map
     */
    public static Map<String, Object> getUpdateMapByUserId(Long userId) {
        // 初始化map
        Map<String, Object> map = initMap();
        // 设置id
        map.put("userId", userId);
        // 返回map
        return map;
    }

    /**
     * 通过传入userId数组返回Map<String, Object>
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param userIds      传入userId数组
     * @return Map
     */
    public static Map<String, Object> getBatchUpdateMapByUserIds(TerminalEnum terminalEnum, Long[] userIds) {
        // 初始化map
        Map<String, Object> map = initMap();
        // 设置id数组
        map.put("userIds", userIds);
        // 设置用户类型
        map.put("userType", terminalEnum.getType());
        // 返回map
        return map;
    }

    /**
     * 通过传入userId数组返回重置密码Map<String, Object>
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param userIds      传入userId数组
     * @return Map
     */
    public static Map<String, Object> getBatchResetMapByUserIds(TerminalEnum terminalEnum, Long[] userIds) {
        // 初始化map
        Map<String, Object> map = initMap();
        // 设置id数组
        map.put("userIds", userIds);
        // 设置用户类型
        map.put("userType", terminalEnum.getType());
        // 设置用户密码
        map.put("password", SecurityUtils.encryptPassword(terminalEnum.getPassword()));
        // 返回map
        return map;
    }

    /**
     * 通过传入userId数组及操作返回Map<String, Object>
     *
     * @param terminalEnum 终端类型（APP：手机端；BACKEND：后台端；SYSTEM：系统端；REGIST：手机注册端）
     * @param operate      操作类型（"enable"：启用；"disable"：停用）
     * @param userIds      传入userId数组
     * @return Map
     */
    public static Map<String, Object> getBatchUpdateMapByOperateUserIds(TerminalEnum terminalEnum, String operate, Long[] userIds) {
        // 初始化map
        Map<String, Object> map = initMap();
        // 设置操作类型
        map.put("operate", operate);
        // 设置id数组
        map.put("userIds", userIds);
        // 设置用户类型
        map.put("userType", terminalEnum.getType());
        // 返回map
        return map;
    }

    /**
     * 设置注册基础字段
     *
     * @param t 泛型
     */
    public static <T extends BaseEntity> void setRegistEntity(T t) {
        // 获取当前时间
        Date date = DateUtils.getNowDate();
        // 获取当前登录人用户名
        String userName = "appRegist";
        // 设置更新时间
        t.setUpdateTime(date);
        // 设置更新人
        t.setUpdateBy(userName);
        // 设置创建时间
        t.setCreateTime(date);
        // 设置创建人
        t.setCreateBy(userName);
        // 设置版本号
        t.setVersion(0L);
        // 设置未删除
        t.setDelFlag("0");
    }

    /**
     * 设置创建基础字段
     *
     * @param t 泛型
     */
    public static <T extends BaseEntity> void setCreateEntity(T t) {
        // 获取当前时间
        Date date = DateUtils.getNowDate();
        // 获取当前登录人用户名
        String userName = SecurityUtils.getUsername();
        // 设置创建时间
        t.setCreateTime(date);
        // 设置创建人
        t.setCreateBy(userName);
        // 设置更新时间
        t.setUpdateTime(date);
        // 设置更新人
        t.setUpdateBy(userName);
        // 设置版本号
        t.setVersion(0L);
        // 设置未删除
        t.setDelFlag("0");
    }

    /**
     * 设置更新基础字段
     *
     * @param t 泛型
     */
    public static <T extends BaseEntity> void setUpdateEntity(T t) {
        // 获取当前时间
        Date date = DateUtils.getNowDate();
        // 获取当前登录人用户名
        String userName = SecurityUtils.getUsername();
        // 设置更新时间
        t.setUpdateTime(date);
        // 设置更新人
        t.setUpdateBy(userName);
    }

    /**
     * 初始化返回参数Map<String, Object>
     *
     * @return 返回参数
     */
    private static Map<String, Object> initMap() {
        // 定义Map
        Map<String, Object> map = new HashMap<>(16);
        // 设置更新时间
        map.put("time", DateUtils.getNowDate());
        // 设置更新人
        map.put("user", SecurityUtils.getUsername());
        return map;
    }

}
