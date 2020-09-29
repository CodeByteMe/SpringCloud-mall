package com.mall.product.list.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.mall.common.pojo.Product;
import com.mall.common.pojo.SkuStock;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import com.mall.product.list.service.ProductListService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(tags = "后台商品列表接口")
public class ProductListController {

    @Resource
    private ProductListService productListService;

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "后台商品列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "从第几页显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productList(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
       String adminId = jws.getBody().getId();

        PageInfo pageInfo = productListService.productList(page, pageSize,adminId);
        return new ResultVO(0,"success",pageInfo);
    }

    @RequestMapping(value = "/allList",method = RequestMethod.GET)
    @ApiOperation(value = "前台商品列表接口", notes = "不需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageInfo", value = "从第几行显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int")
    })
    public ResultVO productAllList(@RequestParam Integer page, @RequestParam Integer pageSize) {
        PageInfo pageInfo = productListService.producAlltList(page, pageSize);
        return new ResultVO(0,"success",pageInfo);
    }

    @RequestMapping(value = "/productDetail",method = RequestMethod.GET)
    @ApiOperation(value = "前台商品详情接口", notes = "不需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "商品id",required = true, dataType = "int")
    })
    public ResultVO productDetail(@RequestParam("productId") String productId) {
        Product product = productListService.productDetailByProductId(productId);
        List<SkuStock> skuStock = productListService.getSkuStockByProductId(productId);
        if(skuStock!=null){
            product.setSkuStock(skuStock);
            return new ResultVO(0,"success",product);
        }
        return new ResultVO(0,"fail",product);
    }

}
