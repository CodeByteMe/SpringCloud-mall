package com.mall.admin.order.list.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.order.list.service.AuthService;
import com.mall.admin.order.list.service.OrderService;
import com.mall.common.pojo.Address;
import com.mall.common.pojo.MemberUser;
import com.mall.common.pojo.Menu;
import com.mall.common.pojo.Order;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "从第几页显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO getOrder(@RequestParam Integer page, @RequestParam Integer pageSize,@RequestHeader(required = true) String token){
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)) {
            String jsonStr = authService.getId(adminId);
            try {
                Integer id = mapper.readValue(jsonStr, Integer.class);
                String companyId = orderService.getCompanyId(id);
                PageInfo pageInfo = orderService.listOrderByCompanyId(page, pageSize,companyId);
                if (pageInfo != null) {
                    return new ResultVO(0,"success",pageInfo);
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

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ApiOperation(value = "订单删除接口" , notes = "根据id删除当前用户有权限删除的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单uuid",required = true, dataType = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO deleteOrder(@RequestParam String orderId,@RequestHeader(required = true) String token){
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)) {
            boolean b = orderService.deleteOrderById(orderId);
            if (b) {
                return new ResultVO(0,"success");
            } else {
                return new ResultVO(1,"fail");
            }
        } else {
            return new ResultVO(1,"没有权限，请联系管理员",null);
        }
    }

    @RequestMapping(value = "/address",method = RequestMethod.GET)
    @ApiOperation(value = "订单地址查询接口" , notes = "根据订单的地址id查询当前订单的地址信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addressId", value = "订单地址uuid",required = true, dataType = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO getAddress(@RequestParam String addressId,@RequestHeader(required = true) String token){
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)) {
            Address addressByAddressId = orderService.getAddressByAddressId(addressId);
            if (addressByAddressId != null) {
                return new ResultVO(0,"success",addressByAddressId);
            } else {
                return new ResultVO(1,"fail",null);
            }
        } else {
            return new ResultVO(1,"没有权限，请联系管理员",null);
        }
    }

    @RequestMapping(value = "/member",method = RequestMethod.GET)
    @ApiOperation(value = "订单用户查询接口" , notes = "根据订单的用户id查询当前订单的用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "订单用户uuid",required = true, dataType = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO getMember(@RequestParam String memberId,@RequestHeader(required = true) String token){
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)) {
            MemberUser memberUserByMemberId = orderService.getMemberUserByMemberId(memberId);
            if (memberUserByMemberId != null) {
                return new ResultVO(0,"success",memberUserByMemberId);
            } else {
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
