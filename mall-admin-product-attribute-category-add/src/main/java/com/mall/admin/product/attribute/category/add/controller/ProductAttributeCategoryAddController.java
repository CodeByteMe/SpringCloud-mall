package com.mall.admin.product.attribute.category.add.controller;

import com.mall.admin.product.attribute.category.add.service.ProductAttributeCategoryAddService;
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
@RequestMapping("/productAttributeCategoryAdd")
@Api(tags = "后台商品属性分类表添加接口")
public class ProductAttributeCategoryAddController {

    @Resource
    private ProductAttributeCategoryAddService productAttributeCategoryAddService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "后台商品添加接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称",required = true, dataType = "String"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productCategoryAdd( @RequestParam String name, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String id = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            boolean b = productAttributeCategoryAddService.productAttributeCategoryAdd(name);
            if (b){
                return new ResultVO(0,"添加成功");
            }else {
                return new ResultVO(1,"添加失败，请联系管理员！");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }

    }
}
