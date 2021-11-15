package com.imooc.service.impl;

import com.imooc.bo.UserBo;
import com.imooc.enums.Sex;
import com.imooc.mapper.CarouselMapper;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Users;
import com.imooc.service.CarouselService;
import com.imooc.service.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", isShow);
        List<Carousel> carouselList = carouselMapper.selectByExample(example);
        log.info("查询轮播图数据列表结束，查询到的数据为：{}",carouselList);
        return carouselList;
    }
}
