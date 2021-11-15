package com.imooc.controller;

import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.vo.CategoryVO;
import com.imooc.vo.IndexFloorVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imooc.utils.CommonJsonResult;

import java.util.List;

@RestController
@RequestMapping("/index")
@Slf4j
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/carousel")
    public CommonJsonResult carousel() {
        List<Carousel> carouselList = carouselService.queryAll(YesOrNo.YES.type);
        return CommonJsonResult.ok(carouselList);
    }

    @GetMapping("/cats")
    public CommonJsonResult cats() {
        List<Category> categoryList = categoryService.queryAllRootLevelCat();
        return CommonJsonResult.ok(categoryList);
    }

    @GetMapping("/subCat/{rootCatId}")
    public CommonJsonResult subCat(@PathVariable Integer rootCatId) {
        log.info("rootCatId: " + rootCatId);
        List<CategoryVO> categoryList = categoryService.getSubCatList(rootCatId);
        return CommonJsonResult.ok(categoryList);
    }

    @GetMapping("/sixNewItems/{rootCatId}")
    public CommonJsonResult sixNewItems(@PathVariable Integer rootCatId) {
        log.info("rootCatId: " + rootCatId);
        List<IndexFloorVO> list = categoryService.getSixNewItemsLazy(rootCatId);
        return CommonJsonResult.ok(list);
    }
}
