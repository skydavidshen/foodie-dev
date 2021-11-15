package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Category;
import com.imooc.vo.CategoryVO;
import com.imooc.vo.IndexFloorVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom extends MyMapper<Category> {
    List<CategoryVO> getSubCatList(Integer rootCatId);
    List<IndexFloorVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}