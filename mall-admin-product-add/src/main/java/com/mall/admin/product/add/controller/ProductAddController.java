package com.mall.admin.product.add.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.admin.product.add.service.ProductAddService;
import com.mall.common.pojo.Product;
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

/*
 *   作者：官宣轩
 *   日期：2020-09-25
 */
@RestController
@CrossOrigin
@RequestMapping("/productAdd")
@Api(tags = "后台商品添加接口")
public class ProductAddController {

    @Resource
    private ProductAddService productAddService;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "后台商品添加接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "product", value = "商品数据",required = true, dataType = "Product"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productCategoryList(@RequestParam Product product, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            boolean b = productAddService.productAdd(product);
            if (b){
                return new ResultVO(0,"success");
            }else {
                return new ResultVO(0,"fail");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }

    }

}
