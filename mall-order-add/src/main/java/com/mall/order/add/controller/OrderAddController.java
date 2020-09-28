package com.mall.order.add.controller;


import com.alibaba.fastjson.JSONObject;
import com.mall.common.pojo.CartItem;
import com.mall.common.pojo.Order;
import com.mall.common.pojo.OrderItem;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import com.mall.order.add.service.OrderAddService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/addOrder")
public class OrderAddController {

    @Resource
    private OrderAddService orderAddService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "前台用户录单接口", notes = "用户录单的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO addOrder(@RequestBody JSONObject order,
                             @RequestHeader(required = true) String token) {
        System.out.println(order);
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            String orderId = UUID.randomUUID().toString().replace("-", "");
            String addressId1 = order.getString("addressId");
            String money = order.getString("money");
            double money1 = Double.parseDouble(money);

            String num = order.getString("num");
            int num1 = Integer.parseInt(num);
            List<CartItem> jsonStrList = Arrays.asList( order.getObject("jsonStrList",CartItem[].class));
            List<OrderItem> orderItems=new ArrayList<>();
            for (CartItem cartItem : jsonStrList) {
                OrderItem orderItem=new OrderItem();
                orderItem.setOrderId(orderId);
                orderItem.setProductId(cartItem.getProductId());
                orderItem.setProductPic(cartItem.getProductPic());
                orderItem.setProductName(cartItem.getProductName());
                orderItem.setProductPrice(cartItem.getPrice());
                orderItem.setProductQuantity(cartItem.getQuantity());
                orderItem.setProductSkuId(cartItem.getProductSkuId());
                orderItems.add(orderItem);
            }
            boolean b = orderAddService.addOrder(new Order(0,orderId,memberId,new Date(),"lisi",money1,money1,0,0,0,0,addressId1,7,null,null,0,null,null,null,null,null),orderItems);
            if (b) {
                return new ResultVO(0, "添加成功");
            } else {
                return new ResultVO(1, "添加失败");
            }
        }else {
            return new ResultVO(1, "权限校验未通过");
        }
    }
}
