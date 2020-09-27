package com.mall.admin.product.category.add.controller;

import com.mall.admin.product.category.add.service.ProductCategoryService;
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
@RequestMapping("/productCategoryAdd")
@Api(tags = "后台商品分类添加接口")
public class ProductCategoryUdController {

    @Resource
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "后台商品分类添加接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productCategoryAdd(@RequestParam String name,@RequestParam Integer parentId,@RequestParam String productUnit,@RequestParam Integer sort,@RequestParam String description, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            ProductCategory productCategory = new ProductCategory();
            if (parentId==0){
                productCategory.setLevel(0);
            }else {
                productCategory.setLevel(1);
            }
            productCategory.setName(name);
            productCategory.setParentId(parentId);
            productCategory.setProductUnit(productUnit);
            productCategory.setSort(sort);
            productCategory.setDescription(description);
            boolean b = productCategoryService.productCategoryAdd(productCategory);
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
