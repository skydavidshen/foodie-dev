package com.imooc.service;

import com.imooc.bo.UserBo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Users;

import java.util.List;

public interface CarouselService {
    public List<Carousel> queryAll(Integer isShow);
}
