package com.imooc.service;

import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.vo.CategoryVO;
import com.imooc.vo.IndexFloorVO;

import java.util.List;

public interface CategoryService {
    List<Category> queryAllRootLevelCat();
    List<CategoryVO> getSubCatList(Integer rootCatId);

    List<IndexFloorVO> getSixNewItemsLazy(Integer rootCatId);
}
