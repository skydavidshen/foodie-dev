package com.imooc.service.center;

import com.imooc.bo.center.CenterUserBo;
import com.imooc.pojo.Users;
import com.imooc.utils.PagedGridResult;
import com.imooc.vo.CenterOrdersVO;

import java.util.List;

public interface CenterOrderService {
    PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);
}
