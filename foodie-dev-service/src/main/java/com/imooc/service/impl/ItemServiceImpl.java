package com.imooc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.enums.CategoryType;
import com.imooc.enums.CommentLevel;
import com.imooc.enums.YesOrNo;
import com.imooc.mapper.*;
import com.imooc.pojo.*;
import com.imooc.service.CategoryService;
import com.imooc.service.ItemService;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Autowired
    private ItemsImgMapper itemsImgMapper;

    @Autowired
    private ItemsSpecMapper itemsSpecMapper;

    @Autowired
    private ItemsParamMapper itemsParamMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        Example example = new Example(Items.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", itemId);
        Items items = itemsMapper.selectOneByExample(example);
        return items;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        List<ItemsImg> list = itemsImgMapper.selectByExample(example);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        ItemsParam itemsParam = itemsParamMapper.selectOneByExample(example);
        return itemsParam;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        List<ItemsSpec> list = itemsSpecMapper.selectByExample(example);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryCommentCounts(String itemId) {
        CommentLevelCountsVO commentLevelCountsVO = new CommentLevelCountsVO();
        commentLevelCountsVO.setGoodCounts(getCommentCounts(itemId, CommentLevel.GOOD.type));
        commentLevelCountsVO.setNormalCounts(getCommentCounts(itemId, CommentLevel.NORMAL.type));
        commentLevelCountsVO.setBadCounts(getCommentCounts(itemId, CommentLevel.BAD.type));

        Integer totalCount = commentLevelCountsVO.getBadCounts() + commentLevelCountsVO.getGoodCounts() +
                commentLevelCountsVO.getNormalCounts();
        commentLevelCountsVO.setTotalCounts(totalCount);

        return commentLevelCountsVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> list = itemsMapperCustom.queryItemComments(itemId, level);
        log.info("查询商品:{}和评价等级为{}的评论分页数据为：{}", itemId, level, list);
        return setterPagedGrid(list, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchItem(String keywords, String sort, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<SearchItemVO> list = itemsMapperCustom.searchItem(keywords, sort);
        log.info("查询商品searchItem: {}和评价等级为{}的评论分页数据为：{}", keywords, sort, list);
        return setterPagedGrid(list, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searchCatItems(Integer catId, String sort, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<SearchItemVO> list = itemsMapperCustom.searchCatItems(catId, sort);
        log.info("查询商品searchCatItems: {}和评价等级为{}的评论分页数据为：{}", catId, sort, list);
        return setterPagedGrid(list, page);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemSpecIdsVO> queryItemBySpecIds(List<String> specIds) {
        List<ItemSpecIdsVO> itemSpecIdsVO = itemsMapperCustom.queryItemBySpecIds(specIds);
        return itemSpecIdsVO;
    }

    @Override
    public ItemsSpec getItemSpecById(String specId) {
        return itemsSpecMapper.selectByPrimaryKey(specId);
    }

    @Override
    public ItemsImg getItemMainImgByItemId(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId", itemId);
        criteria.andEqualTo("isMain", YesOrNo.YES.type);
        return itemsImgMapper.selectOneByExample(example);
    }

    private PagedGridResult setterPagedGrid(List<?> list,Integer page){
        //包含佷多的分页的数据，需要反馈给前端
        PageInfo<?> pageList = new PageInfo<>(list);
        PagedGridResult grid = new PagedGridResult();
        //当前页
        grid.setPage(page);
        //总记录数
        grid.setRecords(pageList.getTotal());
        //每行显示的内容
        grid.setRows(pageList.getList());
        //总页数
        grid.setTotal(pageList.getPages());
        return grid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    Integer getCommentCounts(String itemId, Integer level) {
        ItemsComments itemsComments = new ItemsComments();
        itemsComments.setItemId(itemId);
        if (level != null) {
            itemsComments.setCommentLevel(level);
        }
        return itemsCommentsMapper.selectCount(itemsComments);
    }
}
