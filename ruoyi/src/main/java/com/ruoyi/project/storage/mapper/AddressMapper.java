package com.ruoyi.project.storage.mapper;

import com.ruoyi.project.storage.domain.Address;

import java.util.List;
import java.util.Map;

/**
 * 地址Mapper接口
 *
 * @author wangdong
 * @date 2020.05.10
 */
public interface AddressMapper {

    /**
     * 查询地址列表
     *
     * @param userId 用户id
     * @return 地址集合
     */
    List<Address> selectAddressList(Long userId);

    /**
     * 新增地址
     *
     * @param address 地址
     * @return 结果
     */
    int insertAddress(Address address);

    /**
     * 修改地址
     *
     * @param address 地址
     * @return 结果
     */
    int updateAddress(Address address);

    /**
     * 删除地址
     *
     * @param map id:需要删除的数据ID
     *            user:操作人
     *            time:操作时间
     * @return 结果
     */
    int deleteAddressByIds(Map<String, Object> map);

    /**
     * 设置默认地址
     *
     * @param map id:需要设置默认的数据ID
     *            user:操作人
     *            time:操作时间
     * @return 结果
     */
    int setDefaultAddressById(Map<String, Object> map);

    /**
     * 通过用户id设置当前用户的默认地址为非默认
     *
     * @param map userId:需要设置非默认的用户id
     *            user:操作人
     *            time:操作时间
     * @return 结果
     */
    int setUnDefaultAddressByUserId(Map<String, Object> map);

    /**
     * 通过用户id获取该用户的默认地址
     *
     * @param userId 用户id
     * @return 结果
     */
    Address selectDefaultAddressByUserId(Long userId);

}
