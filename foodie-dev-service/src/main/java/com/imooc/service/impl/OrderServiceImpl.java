package com.imooc.service.impl;

import com.imooc.bo.SubmitOrderBo;
import com.imooc.bo.UserBo;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.Sex;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.*;
import com.imooc.pojo.*;
import com.imooc.service.ItemService;
import com.imooc.service.OrderService;
import com.imooc.service.UserAddressService;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String createOrder(SubmitOrderBo submitOrderBo) {
        String orderId = Sid.nextShort();
        UserAddress userAddress = userAddressService.getById(submitOrderBo.getAddressId());
        Integer totalAmount = 0;
        Integer realPayAmount = 0;
        String[] specIds = StringUtils.split(submitOrderBo.getItemSpecIds(), ",");
        for (String specId : specIds) {
            ItemsSpec itemsSpec = itemService.getItemSpecById(specId);
            Items item = itemService.queryItemById(itemsSpec.getItemId());
            ItemsImg itemsImg = itemService.getItemMainImgByItemId(item.getId());
            String orderItemId = Sid.nextShort();
            Integer buyCount = 1;

            OrderItems orderItems = new OrderItems();
            orderItems.setId(orderItemId);
            orderItems.setOrderId(orderId);
            orderItems.setItemId(item.getId());
            orderItems.setItemImg(itemsImg.getUrl());
            orderItems.setItemName(item.getItemName());
            orderItems.setItemSpecId(itemsSpec.getId());
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setPrice(itemsSpec.getPriceDiscount());
            orderItems.setBuyCounts(buyCount);
            orderItemsMapper.insert(orderItems);

            totalAmount += itemsSpec.getPriceNormal();
            realPayAmount += itemsSpec.getPriceDiscount();

            itemsMapperCustom.decreaseItemSpecStock(itemsSpec.getId(), buyCount);
        }

        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setUserId(submitOrderBo.getUserId());
        orders.setReceiverName(userAddress.getReceiver());
        orders.setReceiverMobile(userAddress.getMobile());
        orders.setReceiverAddress(userAddress.getProvince() + " "
                + userAddress.getCity() + " "
                + userAddress.getDistrict() + " "
                +userAddress.getDetail());
        Integer postAmount = 0;
        orders.setPostAmount(postAmount);
        orders.setPayMethod(submitOrderBo.getPayMethod());
        orders.setLeftMsg(submitOrderBo.getLeftMsg());
        orders.setIsComment(YesOrNo.NO.type);
        orders.setIsDelete(YesOrNo.NO.type);
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());
        orders.setTotalAmount(totalAmount);
        orders.setRealPayAmount(realPayAmount);
        ordersMapper.insert(orders);

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        orderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(orderStatus);

        return orderId;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void payOrder(String orderId) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setOrderStatus(OrderStatusEnum.WAIT_DELIVER.type);
        orderStatus.setPayTime(new Date());
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }
}
