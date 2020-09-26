package com.mall.admin.product.sku.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.admin.product.sku.service.ProductSkuService;
import com.mall.common.pojo.SkuStock;
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
import java.util.List;

/*
 *   作者：官宣轩
 *   日期：2020-09-26
 */
@RestController
@CrossOrigin
@RequestMapping("/productSku")
@Api(tags = "后台套餐接口")
public class ProductSkuController {

    @Resource
    private ProductSkuService productSkuService;
    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ApiOperation(value = "后台套餐查询接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productSkuList( @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            List<SkuStock> skuStocks = productSkuService.productSkuList();
            return new ResultVO(0,"成功",skuStocks);
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "后台套餐添加接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productSkuAdd( @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            SkuStock skuStock = new SkuStock();
            boolean b = productSkuService.productSkuAdd(skuStock);
            if (b){
                return new ResultVO(0,"添加成功");
            }else {
                return new ResultVO(1,"添加失败，请联系管理员！");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }

    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ApiOperation(value = "后台套餐删除接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productSkuDel(@RequestHeader String skuId, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            boolean b = productSkuService.productSkuDel(skuId);
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
