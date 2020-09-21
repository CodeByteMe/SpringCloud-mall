package com.mall.product.list.controller;

import com.mall.common.vo.ResultVO;
import com.mall.product.list.service.ProductListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "后台商品列表接口", notes = "需要携带token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageInfo", value = "从第几行显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int")
    })
    public ResultVO adminLogin(@RequestParam int pageInfo, @RequestParam int pageSize) {
        System.out.println(pageInfo);
        System.out.println(pageSize);
        return null;
    }

}
