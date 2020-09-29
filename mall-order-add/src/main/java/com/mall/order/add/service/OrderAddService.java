package com.mall.order.add.service;

import com.alibaba.fastjson.JSONObject;
import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderItem;

import java.util.List;

public interface OrderAddService {
    public boolean addOrder(JSONObject order,String memberId);
}
