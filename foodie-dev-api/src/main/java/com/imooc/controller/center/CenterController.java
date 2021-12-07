package com.imooc.controller.center;

import com.imooc.bo.SubmitOrderBo;
import com.imooc.bo.center.CenterUserBo;
import com.imooc.controller.BaseController;
import com.imooc.pojo.Users;
import com.imooc.service.OrderService;
import com.imooc.service.UserService;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CommonJsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/center")
public class CenterController extends BaseController {
    @Autowired
    private CenterUserService centerUserService;

    @GetMapping("/userInfo")
    public CommonJsonResult userInfo(@RequestParam String userId) {
        Users users = centerUserService.userinfo(userId);
        return CommonJsonResult.ok(users);
    }
}
