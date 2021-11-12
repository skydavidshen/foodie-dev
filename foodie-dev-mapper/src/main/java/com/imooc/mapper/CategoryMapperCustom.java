package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Category;
import com.imooc.vo.CategoryVO;

import java.util.List;

public interface CategoryMapperCustom extends MyMapper<Category> {
    List<CategoryVO> getSubCatList(Integer rootCatId);
}