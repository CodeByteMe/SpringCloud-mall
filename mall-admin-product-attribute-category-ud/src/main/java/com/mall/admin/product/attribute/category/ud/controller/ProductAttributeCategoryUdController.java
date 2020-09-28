package com.mall.admin.product.attribute.category.ud.controller;

import com.mall.admin.product.attribute.category.ud.service.ProductAttributeCategoryUdService;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/productAttributeCategoryUd")
@Api(tags = "后台商品属性分类表修改接口")
public class ProductAttributeCategoryUdController {

    @Resource
    private ProductAttributeCategoryUdService productAttributeCategoryUdService;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "后台商品属性分类表修改接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称",required = true, dataType = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productAttributeCategoryUpdate( @RequestParam String name,@RequestParam Integer productAttributeCategoryId, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String id = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            boolean b = productAttributeCategoryUdService.productAttributeCategoryUpdate(name,productAttributeCategoryId);
            if (b){
                return new ResultVO(0,"修改成功");
            }else {
                return new ResultVO(1,"修改失败，请联系管理员！");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }

    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ApiOperation(value = "后台商品属性分类表删除接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productAttributeCategoryId", value = "ID",required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productAttributeCategoryDel(@RequestParam Integer productAttributeCategoryId, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String id = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            boolean b = productAttributeCategoryUdService.productAttributeCategoryDel(productAttributeCategoryId);
            if (b){
                return new ResultVO(0,"删除成功");
            }else {
                return new ResultVO(1,"删除失败，请联系管理员！");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }

    }
}
