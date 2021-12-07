package com.imooc.controller.center;

import com.imooc.bo.center.CenterUserBo;
import com.imooc.controller.BaseController;
import com.imooc.pojo.Users;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CommonJsonResult;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
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
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {
    @Autowired
    private CenterUserService centerUserService;

    @PostMapping("/update")
    public CommonJsonResult update(@RequestParam String userId,
                                   @RequestBody @Valid CenterUserBo centerUserBo,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                String errorField = error.getField();
                String errorMsg = error.getDefaultMessage();
                errorMap.put(errorField, errorMsg);
            }
            return CommonJsonResult.errorMap(errorMap);
        }

        Users users = centerUserService.userUpdate(userId, centerUserBo);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(users), true);
        return CommonJsonResult.ok();
    }
}
