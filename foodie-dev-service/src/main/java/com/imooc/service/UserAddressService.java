package com.imooc.service;

import com.imooc.bo.UserAddressBo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.UserAddress;

import java.util.List;

public interface UserAddressService {
    public List<UserAddress> queryAddress(String userId);
    public void add(UserAddressBo userAddress);
    public void update(UserAddressBo userAddress);
    void setDefault(String userId, String addressId);
    void delete(String userId, String addressId);
    UserAddress getById(String addressId);
}
