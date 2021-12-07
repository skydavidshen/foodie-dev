package com.imooc.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imooc.bo.center.CenterUserBo;
import com.imooc.mapper.OrdersMapperCustom;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.service.center.CenterOrderService;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.CenterOrdersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CenterOrderServiceImpl implements CenterOrderService {
    @Autowired
    private OrdersMapperCustom ordersMapperCustom;

    @Override
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<CenterOrdersVO> list = ordersMapperCustom.queryMyOrders(userId, orderStatus);
        return setterPagedGrid(list, page);
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
}
