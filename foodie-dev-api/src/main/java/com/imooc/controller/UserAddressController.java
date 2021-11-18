package com.imooc.controller;

import com.imooc.bo.UserAddressBo;
import com.imooc.pojo.UserAddress;
import com.imooc.service.UserAddressService;
import com.imooc.utils.CommonJsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/address")
@Slf4j
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    @PostMapping("/add")
    public CommonJsonResult add(@RequestBody UserAddressBo userAddressBo,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if(StringUtils.isBlank(userAddressBo.getUserId())){
            return CommonJsonResult.errorMsg("用户未登录的情况下，后端啥都不处理");
        }
        log.info("前端传过来的数据为：{}", userAddressBo);
        userAddressService.add(userAddressBo);
        return CommonJsonResult.ok();
    }

    @PostMapping("/list")
    public CommonJsonResult list(@RequestParam String userId,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if(StringUtils.isBlank(userId)){
            return CommonJsonResult.errorMsg("用户未登录的情况下，后端啥都不处理");
        }

        List<UserAddress> list = userAddressService.queryAddress(userId);
        return CommonJsonResult.ok(list);
    }

    @PostMapping("/update")
    public CommonJsonResult update(@RequestBody UserAddressBo userAddressBo,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if(StringUtils.isBlank(userAddressBo.getUserId())){
            return CommonJsonResult.errorMsg("用户未登录的情况下，后端啥都不处理");
        }

        userAddressService.update(userAddressBo);
        return CommonJsonResult.ok();
    }

    /**
     * 前端setDefalut写错，为了保持一致，将错就错
     *
     * @param userId
     * @param addressId
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/setDefalut")
    public CommonJsonResult setDefault(@RequestParam String userId,
                                       @RequestParam String addressId,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        if(StringUtils.isBlank(userId)){
            return CommonJsonResult.errorMsg("用户未登录的情况下，后端啥都不处理");
        }

        if(StringUtils.isBlank(addressId)){
            return CommonJsonResult.errorMsg("缺少addressId");
        }

        userAddressService.setDefault(userId, addressId);
        return CommonJsonResult.ok();
    }

    @PostMapping("/delete")
    public CommonJsonResult delete(@RequestParam String userId,
                                   @RequestParam String addressId,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        if(StringUtils.isBlank(userId)){
            return CommonJsonResult.errorMsg("用户未登录的情况下，后端啥都不处理");
        }

        userAddressService.delete(userId, addressId);
        return CommonJsonResult.ok();
    }
}
