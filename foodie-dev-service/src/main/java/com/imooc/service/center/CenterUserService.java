package com.imooc.service.center;

import com.imooc.bo.center.CenterUserBo;
import com.imooc.pojo.Users;

public interface CenterUserService {
    Users userinfo(String userId);
    Users userUpdate(String userId, CenterUserBo centerUserBo);
}
