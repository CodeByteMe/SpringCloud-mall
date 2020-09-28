package com.mall.cart.list.controller;


import com.mall.cart.list.service.CartItemService;
import com.mall.common.pojo.CartItem;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
@Api(tags = "购物车查询接口")
public class CartItemController {

    @Resource
    private CartItemService cartItemService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "前台用户购物车查询接口", notes = "用户查询自己所有购物车的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getCartList(@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            List<CartItem> cartItemByMemberId = cartItemService.getCartItemByMemberId(memberId);
            if (cartItemByMemberId != null) {
                return new ResultVO(0, "查询成功", cartItemByMemberId);
            } else {
                return new ResultVO(1, "查询失败", null);
            }
        }else {
            return new ResultVO(1, "权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "前台用户购物车删除接口", notes = "用户删除购物车一条记录的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getCartList(@RequestParam("id") Integer id,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            boolean b = cartItemService.deleteCart(id);
            if (b) {
                return new ResultVO(0, "删除成功");
            } else {
                return new ResultVO(1, "删除失败");
            }
        }else {
            return new ResultVO(1, "权限校验未通过");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "前台用户购物车新增接口", notes = "用户新增购物车的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO addCart(@RequestParam("changeSkuId") String changeSkuId,
                            @RequestParam("pic") String pic,
                            @RequestParam("note") String note,
                            @RequestParam("productAttributeCategoryId") String productAttributeCategoryId,
                            @RequestParam("num") String num,
                            @RequestParam("changePrice") String changePrice,
                            @RequestParam("productId") String productId,
                            @RequestParam("name") String name,
                            @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            int i = Integer.parseInt(num);
            double v = Double.parseDouble(changePrice);
            int i1 = Integer.parseInt(productAttributeCategoryId);
            boolean b = cartItemService.addCart(new CartItem(0,productId,changeSkuId,memberId,null,i,v,pic,name,null,new Date(),null,i1,null,null));
            if (b) {
                return new ResultVO(0, "删除成功");
            } else {
                return new ResultVO(1, "删除失败");
            }
        }else {
            return new ResultVO(1, "权限校验未通过");
        }
    }
}
