package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsComments;
import com.imooc.vo.ItemCommentVO;
import com.imooc.vo.ItemSpecIdsVO;
import com.imooc.vo.SearchItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom extends MyMapper<Items> {
    List<ItemCommentVO> queryItemComments(@Param("itemId") String itemId, @Param("level") Integer level);
    List<SearchItemVO> searchItem(@Param("keywords") String keywords, @Param("sort") String sort);
    List<SearchItemVO> searchCatItems(@Param("catId") Integer catId, @Param("sort") String sort);
    List<ItemSpecIdsVO> queryItemBySpecIds(@Param("specIdsList") List<String> specIdsList);
}