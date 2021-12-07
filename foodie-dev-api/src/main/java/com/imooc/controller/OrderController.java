package com.imooc.controller;

import com.imooc.bo.SubmitOrderBo;
import com.imooc.service.OrderService;
import com.imooc.utils.CommonJsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
    final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public CommonJsonResult create(@RequestBody SubmitOrderBo submitOrderBo) {
        String orderId = orderService.createOrder(submitOrderBo);
        return CommonJsonResult.ok(orderId);
    }

    @PostMapping("/pay")
    public CommonJsonResult payOrder(@RequestParam String orderId) {
        orderService.payOrder(orderId);
        return CommonJsonResult.ok(orderId);
    }
}
