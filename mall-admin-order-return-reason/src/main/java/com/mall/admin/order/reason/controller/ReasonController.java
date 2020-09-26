package com.mall.admin.order.reason.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.mall.admin.order.reason.service.ReasonService;
import com.mall.common.pojo.FlashPromotionProductRelation;
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

/**
 * ReasonController
 *
 * @Author BessCroft
 * @Date 2020/9/25 16:56
 */
@RestController
@CrossOrigin
@RequestMapping("/reason")
@Api(tags = "退货原因管理接口")
public class ReasonController {

    @Resource
    private ReasonService reasonService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "退货原因信息查询接口" , notes = "查询当前用户有权限看到的所有的退货原因")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "从第几页显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO getOrder(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestHeader(required = true) String token){
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)) {
            PageInfo pageInfo = reasonService.listOrderReturnReason(page, pageSize);
            if (pageInfo != null) {
                return new ResultVO(0,"success",pageInfo);
            } else {
                return new ResultVO(1,"fail",null);
            }
        } else {
            return new ResultVO(1,"没有权限，请联系管理员",null);
        }
    }

}
