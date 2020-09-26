package com.mall.admin.order.apply.controller;

import com.github.pagehelper.PageInfo;
import com.mall.admin.order.apply.service.ApplyService;
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

/**
 * ApplyController
 *
 * @Author BessCroft
 * @Date 2020/9/26 14:55
 */
@RestController
@CrossOrigin
@RequestMapping("/apply")
@Api(tags = "退货申请处理接口")
public class ApplyController {

    @Resource
    private ApplyService applyService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value = "退货申请信息查询接口" , notes = "查询当前用户有权限看到的所有的退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "从第几页显示",required = true, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少数据",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO getApply(@RequestParam Integer page, @RequestParam Integer pageSize, @RequestHeader(required = true) String token){
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)) {
            PageInfo pageInfo = applyService.listOrderReturn(page, pageSize, adminId);
            if (pageInfo != null) {
                return new ResultVO(0,"success",pageInfo);
            } else {
                return new ResultVO(1,"fail",null);
            }
        } else {
            return new ResultVO(1,"没有权限，请联系管理员",null);
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ApiOperation(value = "订单退货申请信息删除接口" , notes = "根据id删除当前用户有权限删除的退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单退货申请id",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO getApply(@RequestParam Integer id,@RequestHeader(required = true) String token){
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)) {
            boolean b = applyService.deleteOrderReturn(id);
            if (b) {
                return new ResultVO(0,"success");
            } else {
                return new ResultVO(1,"fail");
            }
        } else {
            return new ResultVO(1,"没有权限，请联系管理员",null);
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ApiOperation(value = "订单退货申请信息删除接口" , notes = "根据id删除当前用户有权限删除的退货申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单退货申请id",required = true, dataType = "int"),
            @ApiImplicitParam(name = "status", value = "订单退货状态",required = true, dataType = "int"),
            @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    })
    public ResultVO updateApplyState(@RequestParam Integer id,@RequestParam Integer status,@RequestHeader(required = true) String token){
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String adminId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("admin".equals(issuer)) {
            boolean b = applyService.updateApplyStatus(id, status);
            if (b) {
                return new ResultVO(0,"success");
            } else {
                return new ResultVO(1,"fail");
            }
        } else {
            return new ResultVO(1,"没有权限，请联系管理员",null);
        }
    }
}
