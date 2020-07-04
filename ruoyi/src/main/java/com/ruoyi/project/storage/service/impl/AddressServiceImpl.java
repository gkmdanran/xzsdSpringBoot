package com.ruoyi.project.storage.service.impl;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.common.util.ParameterUtil;
import com.ruoyi.project.storage.domain.Address;
import com.ruoyi.project.storage.mapper.AddressMapper;
import com.ruoyi.project.storage.service.IAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 地址Service业务层处理
 *
 * @author wangdong
 * @date 2020.05.10
 */
@Service
@Slf4j
public class AddressServiceImpl implements IAddressService {

    /**
     * 地址Mapper
     */
    private final AddressMapper addressMapper;

    /**
     * 构造方法注入
     *
     * @param addressMapper 地址Mapper
     */
    @Autowired
    public AddressServiceImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    /**
     * 查询地址列表
     *
     * @return 地址集合
     */
    @Override
    public List<Address> selectAddressList() {
        // 返回地址列表数据
        return addressMapper.selectAddressList(SecurityUtils.getUserId());
    }

    /**
     * 新增地址
     *
     * @param address 地址
     * @return 条数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAddress(Address address) {
        // 校验当前是否为默认地址
        if (address.getIsDefault() == 0) {
            // 拿到当前代理类
            IAddressService addressService = (IAddressService) AopContext.currentProxy();
            // 设置当前用户的默认地址为非默认
            int setUnDefault = addressService.setUnDefaultAddressByUserId();
            // 打log
            log.info("新增地址，当前为默认地址，共修改{}条地址为非默认", setUnDefault);
        }
        // 设置创建基础字段
        ParameterUtil.setCreateEntity(address);
        // 设置用户id
        address.setUserId(SecurityUtils.getUserId());
        // 返回插入地址条数
        return addressMapper.insertAddress(address);
    }

    /**
     * 修改地址
     *
     * @param address 地址
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAddress(Address address) {
        // 校验地址id不能为空
        if (address.getId() == null) {
            // 手动抛异常
            throw new CustomException("修改地址失败，未传递地址id", HttpStatus.ERROR);
        }
        // 拿到当前代理类
        IAddressService addressService = (IAddressService) AopContext.currentProxy();
        // 拿到当前默认地址
        Address defaultAddress = addressService.selectDefaultAddress();
        // 校验默认地址不为空（如果默认地址为空，则没有必要修改默认地址为非默认）
        if (defaultAddress != null) {
            // 校验当前要设置为默认地址，且，当前地址不是默认地址
            if (address.getIsDefault() == 0 && !address.getId().equals(defaultAddress.getId())) {
                // 设置当前用户的默认地址为非默认
                int setUnDefault = addressService.setUnDefaultAddressByUserId();
                // 打log
                log.info("修改地址，当前为默认地址，共修改{}条地址为非默认", setUnDefault);
            }
        }
        // 设置更新基础字段
        ParameterUtil.setUpdateEntity(address);
        // 返回修改条数
        return addressMapper.updateAddress(address);
    }

    /**
     * 删除地址
     *
     * @param id 需要删除的地址ID
     * @return 结果
     */
    @Override
    public int deleteAddressById(Long id) {
        // 返回修改条数
        return addressMapper.deleteAddressByIds(ParameterUtil.getUpdateMapById(id));
    }

    /**
     * 设置默认地址
     *
     * @param id 需要设置默认的地址ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int setDefaultAddressById(Long id) {
        // 拿到当前代理类
        IAddressService addressService = (IAddressService) AopContext.currentProxy();
        // 设置当前用户默认地址为非默认，返回更新条数
        int setUnDefaultCount = addressService.setUnDefaultAddressByUserId();
        // 记录更新条数
        log.info("设置默认地址，修改{}条地址为非默认", setUnDefaultCount);
        // 返回设置默认地址修改条数
        return addressMapper.setDefaultAddressById(ParameterUtil.getUpdateMapById(id));
    }

    /**
     * 获取当前用户默认地址
     *
     * @return 结果
     */
    @Override
    public Address selectDefaultAddress() {
        // 返回地址列表数据
        return addressMapper.selectDefaultAddressByUserId(SecurityUtils.getUserId());
    }

    /**
     * 设置当前用户的默认地址为非默认
     *
     * @return 更新条数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int setUnDefaultAddressByUserId() {
        // 返回更新条数
        return addressMapper.setUnDefaultAddressByUserId(ParameterUtil.getUpdateMapByUserId(SecurityUtils.getUserId()));
    }

}
