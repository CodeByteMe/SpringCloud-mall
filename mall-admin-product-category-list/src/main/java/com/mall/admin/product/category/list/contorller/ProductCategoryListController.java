package com.mall.admin.product.category.list.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.mall.admin.product.category.list.service.ProductCategoryListService;
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
 *   日期：2020-09-24
 */
@RestController
@CrossOrigin
@RequestMapping("/productCategory")
@Api(tags = "后台商品分类列表接口")
public class ProductCategoryListController {

    @Resource
    private ProductCategoryListService productCategoryListService;
    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "后台商品分类列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "从第几页显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productCategoryList(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            PageInfo pageInfo = productCategoryListService.productCategoryList(page, pageSize);
            return new ResultVO(0,"success",pageInfo);
        }else {
            return new ResultVO(1,"没有权限,请联系管理员!");
        }

    }

    @RequestMapping(value = "/listByparentId",method = RequestMethod.GET)
    @ApiOperation(value = "后台商品分类子级列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级ID",required = true, dataType = "int"),
            @ApiImplicitParam(name = "page", value = "从第几页显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO productCategoryListByparentId(@RequestParam Integer parentId, @RequestParam Integer page, @RequestParam Integer pageSize, @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
//       String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)){
            PageInfo pageInfo = productCategoryListService.productCategoryListByParentId(parentId,page,pageSize);
            return new ResultVO(0,"success",pageInfo);
        }else {
            return new ResultVO(0,"没有权限,请联系管理员!");
        }

    }
}
