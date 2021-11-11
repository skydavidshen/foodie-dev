package com.imooc.controller;

import com.imooc.bo.UserBo;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.imooc.utils.CommonJsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassportController {
    @Autowired
    private UserService userService;

    @PostMapping("/regist")
    public CommonJsonResult regist(@RequestBody UserBo userBo) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPwd = userBo.getConfirmPassword();
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(confirmPwd)) {
            return CommonJsonResult.errorMsg("用户名或密码不能为空。");
        }

        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CommonJsonResult.errorMsg("用户名已存在。");
        }

        if (password.length() < 6) {
            return CommonJsonResult.errorMsg("密码长度不能少于6。");
        }

        if (!password.equals(confirmPwd)) {
            return CommonJsonResult.errorMsg("密码不一致。");
        }

        Users user = null;
        try {
            user = userService.registerNewUser(userBo);
        } catch (Exception e) {
            return CommonJsonResult.errorMsg(e.getMessage());
        }

        return CommonJsonResult.ok(user);
    }
}
