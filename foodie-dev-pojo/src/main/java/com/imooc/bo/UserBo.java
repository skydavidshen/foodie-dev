package com.imooc.bo;

import lombok.Data;

@Data
public class UserBo {
    private String username;
    private String password;
    private String confirmPassword;
}
