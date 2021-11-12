package com.imooc.controller;

import com.imooc.bo.UserBo;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.imooc.utils.CommonJsonResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/passport")
public class PassportController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("/usernameIsExist")
    public CommonJsonResult usernameIsExist(@RequestParam String username) {
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return CommonJsonResult.errorMsg("用户名已存在。");
        }

        return CommonJsonResult.ok();
    }

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

    @PostMapping("/login")
    public CommonJsonResult login(@RequestBody UserBo userBo,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        if (StringUtils.isBlank(username) ||
                StringUtils.isBlank(password)) {
            return CommonJsonResult.errorMsg("用户名或密码不能为空。");
        }
        Users user = userService.queryUserForLogin(username, password);
        if (user == null) {
            return CommonJsonResult.errorMsg("用户名或密码不正确。");
        }

        user = setNullProperty(user);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        return CommonJsonResult.ok(user);
    }

    @PostMapping("/logout")
    public CommonJsonResult logout(@RequestParam String userId,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, "user");
        return CommonJsonResult.ok();
    }
}
