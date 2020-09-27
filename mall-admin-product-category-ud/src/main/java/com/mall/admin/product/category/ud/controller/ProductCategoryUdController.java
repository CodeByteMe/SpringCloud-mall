package com.mall.admin.product.category.ud.controller;

import com.mall.admin.product.category.ud.service.ProductCategoryService;
import com.mall.common.pojo.ProductCategory;
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
 *   日期：2020-09-27
 */
@RestController
@CrossOrigin
@RequestMapping("/productCategoryUd")
@Api(tags = "后台商品分类修改接口")
public class ProductCategoryUdController {

    @Resource
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ApiOperation(value = "后台商品分类修改接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productCategoryDel(@RequestParam Integer productCategoryId, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            boolean b = productCategoryService.productCategoryDel(productCategoryId);
            if (b){
                return new ResultVO(0,"删除成功");
            }else {
                return new ResultVO(1,"删除失败，请联系管理员！");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "后台商品分类修改接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productCategoryUpdate(@RequestParam Integer productCategoryId,@RequestParam String name,@RequestParam Integer parentId,@RequestParam String productUnit,@RequestParam Integer sort,@RequestParam String description, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(productCategoryId);
            productCategory.setName(name);
            productCategory.setParentId(parentId);
            productCategory.setProductUnit(productUnit);
            productCategory.setSort(sort);
            productCategory.setDescription(description);
            boolean b = productCategoryService.productCategoryUpdate(productCategory);
            if (b){
                return new ResultVO(0,"修改成功");
            }else {
                return new ResultVO(1,"修改失败，请联系管理员！");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }
    }

}
