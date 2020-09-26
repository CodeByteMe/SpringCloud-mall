package com.mall.admin.product.urd.controller;

import com.mall.admin.product.urd.service.ProductUrdService;
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
 *   日期：2020-09-26
 */
@RestController
@CrossOrigin
@RequestMapping("/productUrd")
@Api(tags = "后台商品修改接口")
public class ProductUrdController {

    @Resource
    private ProductUrdService productUrdService;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "后台商品修改接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "product", value = "需要修改商品的属性",required = true, dataType = "Product"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productUpdate(@RequestParam String productId,@RequestParam String name,@RequestParam String subTitle,@RequestParam String description,@RequestParam double price,@RequestParam double originalPrice,@RequestParam Integer stock,
                                  @RequestParam String unit,@RequestParam double weight,@RequestParam Integer sort,@RequestParam Integer previewStatus,@RequestParam Integer publishStatus,@RequestParam String detailTitle,
                                  @RequestParam String detailDesc,@RequestParam String note,@RequestParam Integer productAttributeCategoryId,@RequestParam String pic, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            Product product = new Product();
            product.setProductId(productId);
            product.setName(name);
            product.setSubTitle(subTitle);
            product.setDescription(description);
            product.setPrice(price);
            product.setOriginalPrice(originalPrice);
            product.setUnit(unit);
            product.setWeight(weight);
            product.setDetailDesc(detailDesc);
            product.setNote(note);
            product.setProductAttributeCategoryId(productAttributeCategoryId);
            product.setSort(sort);
            product.setPreviewStatus(previewStatus);
            product.setPublishStatus(publishStatus);
            product.setPic(pic);
            product.setStock(stock);
            product.setDetailTitle(detailTitle);
            boolean b = productUrdService.productUpdate(product);
            if (b){
                return new ResultVO(0,"修改成功");
            }else {
                return new ResultVO(0,"修改失败，请联系管理员！");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ApiOperation(value = "后台商品修改接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "需要删除商品的ID",required = true, dataType = "integer"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productDel(@RequestParam String productId, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            boolean b = productUrdService.productDel(productId);
            if (b){
                return new ResultVO(0,"删除成功");
            }else {
                return new ResultVO(0,"删除失败，请联系管理员！");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }
    }

    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    @ApiOperation(value = "后台商品修改接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productId", value = "需要删除商品的ID",required = true, dataType = "integer"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productUpdateStatus(@RequestParam String productId,@RequestParam Integer publishStatus, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            boolean b = productUrdService.productStatus(productId,publishStatus);
            if (b){
                return new ResultVO(0,"修改成功");
            }else {
                return new ResultVO(0,"修改失败，请联系管理员！");
            }
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }
    }

}
