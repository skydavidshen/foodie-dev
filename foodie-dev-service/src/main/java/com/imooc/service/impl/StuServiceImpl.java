package com.imooc.service.impl;

import com.imooc.mapper.StuMapper;
import com.imooc.pojo.Stu;
import com.imooc.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.awt.geom.AreaOp;
import tk.mybatis.mapper.entity.Example;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Stu getStuInfo(int id) {
        return stuMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveStu() {
        Stu stu = new Stu();
        stu.setName("jack");
        stu.setAge(20);
        stuMapper.insert(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStu(int id) {
        Example stuExample = new Example(Stu.class);
        Example.Criteria criteria = stuExample.createCriteria();
        criteria.andEqualTo("id", id);
        Stu stu = stuMapper.selectOneByExample(stuExample);
        stu.setName("sdw");
        stuMapper.updateByPrimaryKey(stu);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteStu(int id) {
        Example stuExample = new Example(Stu.class);
        Example.Criteria criteria = stuExample.createCriteria();
        criteria.andEqualTo("id", id);
        Stu stu = stuMapper.selectOneByExample(stuExample);
        stuMapper.deleteByPrimaryKey(stu);
    }
}
