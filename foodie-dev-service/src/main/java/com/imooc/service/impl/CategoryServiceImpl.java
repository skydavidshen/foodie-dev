package com.imooc.service.impl;

import com.imooc.enums.CategoryType;
import com.imooc.mapper.CarouselMapper;
import com.imooc.mapper.CategoryMapper;
import com.imooc.mapper.CategoryMapperCustom;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.vo.CategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;

    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", CategoryType.ONE.type);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        log.info("分类数据，查询到的数据为：{}", categoryList);
        return categoryList;
    }

    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        List<CategoryVO> categoryList = categoryMapperCustom.getSubCatList(rootCatId);
        log.info("SubCatList数据，查询到的数据为：{}", categoryList);
        return categoryList;
    }
}
