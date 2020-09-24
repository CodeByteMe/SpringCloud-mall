package com.mall.admin.order.list.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.admin.order.list.service.AuthService;
import com.mall.admin.order.list.service.OrderService;
import com.mall.common.pojo.Menu;
import com.mall.common.pojo.Order;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * OrderController
 *
 * @Author BessCroft
 * @Date 2020/9/24 17:17
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private AuthService authService;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "订单信息查询接口" , notes = "查询当前用户有权限看到的所有的订单")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getOrder(@RequestHeader(required = true) String token){
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)) {
            String jsonStr = authService.getId(adminId);
            try {
                Integer id = mapper.readValue(jsonStr, Integer.class);
                String companyId = orderService.getCompanyId(id);
                List<Order> orders = orderService.listOrderByCompanyId(companyId);
                System.out.println(companyId);
                System.out.println(orders);
                if (orders != null) {
                    return new ResultVO(0,"success",orders);
                } else {
                    return new ResultVO(1,"fail",null);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ResultVO(1,"fail",null);
            }
        } else {
            return new ResultVO(1,"没有权限，请联系管理员",null);
        }
    }

    public static void main(String[] args) {
        String s = UUID.randomUUID().toString().replace("-", "");
        System.out.println(s);
    }
}
