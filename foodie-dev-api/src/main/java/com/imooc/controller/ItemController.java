package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.service.ItemService;
import com.imooc.utils.CommonJsonResult;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.CommentLevelCountsVO;
import com.imooc.vo.ItemInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@Slf4j
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/info/{itemId}")
    public CommonJsonResult info(@PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            CommonJsonResult.errorMsg("itemId 不能为空。");
        }

        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImg = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParams = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImg);
        itemInfoVO.setItemParams(itemsParams);
        itemInfoVO.setItemSpecList(itemsSpecs);

        return CommonJsonResult.ok(itemInfoVO);
    }

    @GetMapping("/commentLevel")
    public CommonJsonResult commentLevel(@RequestParam String itemId) {
        CommentLevelCountsVO commentLevelCountsVO = itemService.queryCommentCounts(itemId);
        return CommonJsonResult.ok(commentLevelCountsVO);
    }

    @GetMapping("/comments")
    public CommonJsonResult comments(@RequestParam String itemId,
                                     @RequestParam Integer level,
                                     @RequestParam(name = "page", defaultValue = "1") Integer page) {
        PagedGridResult result = itemService.queryPagedComments(itemId, level, page, 10);
        return CommonJsonResult.ok(result);
    }
}
