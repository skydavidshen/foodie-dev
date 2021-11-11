package com.imooc.service;

import com.imooc.bo.UserBo;
import com.imooc.pojo.Users;

public interface UserService {
    boolean queryUsernameIsExist(String username);
    Users registerNewUser(UserBo userBo);
    Users queryUserForLogin(String username, String password);
}
