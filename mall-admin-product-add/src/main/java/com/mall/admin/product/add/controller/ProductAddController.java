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
import java.util.UUID;

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
    public ResultVO productCategoryAdd(@RequestParam String name,@RequestParam String subTitle,@RequestParam String description,@RequestParam double price,@RequestParam double originalPrice,@RequestParam Integer stock,
                                       @RequestParam String unit,@RequestParam double weight,@RequestParam Integer sort,@RequestParam Integer previewStatus,@RequestParam Integer publishStatus,@RequestParam String detailTitle,
                                       @RequestParam String detailDesc,@RequestParam String note,@RequestParam Integer productAttributeCategoryId,@RequestParam String pic,@RequestParam Integer productCategoryId,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String id = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            String companyId = productAddService.searchCompanyIdByAdminUUID(id);
            String productId = UUID.randomUUID().toString().replace("-", "");
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
            product.setProductCategoryId(productCategoryId);
            product.setStock(stock);
            product.setDetailTitle(detailTitle);
            product.setCompanyId(companyId);
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
