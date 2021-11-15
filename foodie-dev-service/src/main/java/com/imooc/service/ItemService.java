package com.imooc.service;

import com.imooc.pojo.*;
import com.imooc.vo.CategoryVO;
import com.imooc.vo.IndexFloorVO;

import java.util.List;

public interface ItemService {
    Items queryItemById(String itemId);
    List<ItemsImg> queryItemImgList(String itemId);
    ItemsParam queryItemParam(String itemId);
    List<ItemsSpec> queryItemSpecList(String itemId);
}
