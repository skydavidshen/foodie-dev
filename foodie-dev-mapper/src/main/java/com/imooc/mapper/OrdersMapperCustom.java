package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.OrderItems;
import com.imooc.vo.CenterOrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapperCustom {
    List<CenterOrdersVO> queryMyOrders(@Param("userId") String userId, @Param("orderStatus") Integer orderStatus);
}