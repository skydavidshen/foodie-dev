package com.imooc.service.impl.center;

import com.imooc.bo.UserBo;
import com.imooc.bo.center.CenterUserBo;
import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.UserService;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class CenterUserServiceImpl implements CenterUserService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users userinfo(String userId) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setPassword(null);
        return users;
    }

    @Override
    public Users userUpdate(String userId, CenterUserBo centerUserBo) {
        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setNickname(centerUserBo.getNickname());
        users.setRealname(centerUserBo.getRealname());
        users.setMobile(centerUserBo.getMobile());
        users.setEmail(centerUserBo.getEmail());
        users.setSex(centerUserBo.getSex());
        users.setBirthday(centerUserBo.getBirthday());
        users.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(users);
        return users;
    }
}
