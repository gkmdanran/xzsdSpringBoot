package com.ruoyi.project.storage.service;

import com.ruoyi.project.storage.domain.Address;

import java.util.List;

/**
 * 地址Service接口
 *
 * @author wangdong
 * @date 2020.05.10
 */
public interface IAddressService {

    /**
     * 查询地址列表
     *
     * @return 地址集合
     */
    List<Address> selectAddressList();

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
     * @param id 需要删除的地址ID
     * @return 结果
     */
    int deleteAddressById(Long id);

    /**
     * 设置默认地址
     *
     * @param id 需要设置默认的地址ID
     * @return 结果
     */
    int setDefaultAddressById(Long id);

    /**
     * 获取当前用户默认地址
     *
     * @return 结果
     */
    Address selectDefaultAddress();

    /**
     * 设置当前用户的默认地址为非默认
     *
     * @return 更新条数
     */
    int setUnDefaultAddressByUserId();
}
