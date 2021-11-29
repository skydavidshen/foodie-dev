package com.imooc.service;

import com.imooc.bo.SubmitOrderBo;
import com.imooc.bo.UserBo;
import com.imooc.pojo.Users;

public interface OrderService {
    String createOrder(SubmitOrderBo submitOrderBo);
}
