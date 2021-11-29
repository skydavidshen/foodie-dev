package com.imooc.service.impl;

import com.imooc.bo.UserAddressBo;
import com.imooc.mapper.CarouselMapper;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.UserAddress;
import com.imooc.service.CarouselService;
import com.imooc.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAddress(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        List<UserAddress> list = userAddressMapper.select(userAddress);
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void add(UserAddressBo userAddress) {
        UserAddress address = new UserAddress();
        address.setUserId(userAddress.getUserId());
        List<UserAddress> list = userAddressMapper.select(address);
        if (list == null || list.isEmpty()) {
            address.setIsDefault(1);
        }
        BeanUtils.copyProperties(userAddress, address);
        String id = Sid.nextShort();
        address.setId(id);
        address.setCreatedTime(new Date());
        address.setUpdatedTime(new Date());
        userAddressMapper.insert(address);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void update(UserAddressBo userAddress) {
        UserAddress address = new UserAddress();
        BeanUtils.copyProperties(userAddress, address);
        address.setId(userAddress.getAddressId());
        address.setUpdatedTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void setDefault(String userId, String addressId) {
        UserAddress address = new UserAddress();
        address.setUserId(userId);
        List<UserAddress> list = userAddressMapper.select(address);
        for (UserAddress item:list) {
            item.setIsDefault(0);
            userAddressMapper.updateByPrimaryKeySelective(item);
        }

        UserAddress updateAddress = new UserAddress();
        updateAddress.setId(addressId);
        updateAddress.setIsDefault(1);
        userAddressMapper.updateByPrimaryKeySelective(updateAddress);
    }

    @Override
    public void delete(String userId, String addressId) {
        UserAddress address = new UserAddress();
        address.setUserId(userId);
        address.setId(addressId);
        userAddressMapper.delete(address);
    }

    @Override
    public UserAddress getById(String addressId) {
        return userAddressMapper.selectByPrimaryKey(addressId);
    }
}
