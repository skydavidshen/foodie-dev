package com.imooc.controller;

import com.imooc.bo.ItemSpecIdsBO;
import com.imooc.enums.YesOrNo;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.service.CarouselService;
import com.imooc.service.CategoryService;
import com.imooc.service.ItemService;
import com.imooc.utils.CommonJsonResult;
import com.imooc.vo.CategoryVO;
import com.imooc.vo.IndexFloorVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/shopcart")
@Slf4j
public class ShopCartController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public CommonJsonResult add(@RequestParam String userId,
                                @RequestBody ItemSpecIdsBO itemSpecIdsBO,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if(StringUtils.isBlank(userId)){
            return CommonJsonResult.errorMsg("用户未登录的情况下，后端啥都不处理，前端的cookie进行记录即可");
        }
        log.info("前端传过来的购物车数据为：{}", itemSpecIdsBO);
        //TODO 前端用户在登录的情况下添加商品到购物车里，会同步在后端往redis中添加缓存
        return CommonJsonResult.ok();
    }

    @PostMapping("/del")
    public CommonJsonResult del(@RequestParam String userId,
                                @RequestParam String itemSpecId,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if(StringUtils.isBlank(userId)){
            return CommonJsonResult.errorMsg("用户未登录的情况下，后端啥都不处理，前端的cookie进行记录即可");
        }
        log.info("前端传过来的购物车数据为：{}", itemSpecId);
        //TODO 前端用户在登录的情况下添加商品到购物车里，会同步在后端往redis中删除缓存
        return CommonJsonResult.ok();
    }
}
