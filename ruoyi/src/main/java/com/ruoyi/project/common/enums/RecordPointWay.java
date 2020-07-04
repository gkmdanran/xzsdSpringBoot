package com.ruoyi.project.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 积分获取方式
 *
 * @author wangdong
 * @date 2020-05-04
 */
@AllArgsConstructor
@Getter
public enum RecordPointWay {

    /**
     * 注册赠送（注册）
     */
    REGIST(1),

    /**
     * 活动获得（看广告）
     */
    ADVERTISEMENT(2),

    /**
     * 积分使用（下单）
     */
    ORDER(3);

    /**
     * 代码
     */
    private int code;

}
