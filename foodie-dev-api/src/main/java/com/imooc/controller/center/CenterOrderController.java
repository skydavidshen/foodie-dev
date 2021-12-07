package com.imooc.controller.center;

import com.imooc.bo.center.CenterUserBo;
import com.imooc.controller.BaseController;
import com.imooc.pojo.Users;
import com.imooc.service.center.CenterOrderService;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CommonJsonResult;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/myorders")
public class CenterOrderController extends BaseController {
    @Autowired
    private CenterOrderService centerOrderService;

    @PostMapping("/query")
    public CommonJsonResult update(@RequestParam String userId,
                                   @RequestParam Integer orderStatus,
                                   @RequestParam(name = "page",defaultValue = "1") Integer page,
                                   @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize) {
        PagedGridResult list = centerOrderService.queryMyOrders(userId, orderStatus, page, pageSize);
        return CommonJsonResult.ok(list);
    }
}
