package com.mall.address.list.controller;

import com.mall.address.list.service.AddressService;
import com.mall.common.pojo.Address;
import com.mall.common.pojo.City;
import com.mall.common.pojo.Province;
import com.mall.common.pojo.Region;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/address")
@Api(tags = "地址查询接口")
public class AddressController {

    @Resource
    private AddressService addressService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "前台用户地址查询接口", notes = "用户查询自己所有地址的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getAddressList(@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            List<Address> addressList = addressService.getAllListByMemberId(memberId);
            if (addressList != null) {
                return new ResultVO(0, "查询成功", addressList);
            } else {
                return new ResultVO(1, "查询失败", null);
            }
        }else {
            return new ResultVO(1, "权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "前台用户地址删除接口", notes = "用户删除地址的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO deleteAddress(String addressId,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            int i = addressService.deleteByIdAndMemberId(addressId);
            if (i > 0) {
                return new ResultVO(0, "删除成功");
            } else {
                return new ResultVO(1, "删除失败");
            }
        }else {
            return new ResultVO(1, "权限校验未通过");
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ApiOperation(value = "前台用户地址添加接口", notes = "用户新增地址的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO insertAddress(String provinceId,String cityId,String regionId,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            Province province = addressService.getProvince(provinceId);

            int i = addressService.insertAddress(new Address());
            if (i > 0) {
                return new ResultVO(0, "添加成功");
            } else {
                return new ResultVO(1, "添加失败");
            }
        }else {
            return new ResultVO(1, "权限校验未通过");
        }
    }

    @RequestMapping(value = "/province", method = RequestMethod.GET)
    @ApiOperation(value = "省份查询接口", notes = "用户查询所有省份的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getProvince(@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            List<Province> provinces = addressService.listProvince();
            if (provinces != null) {
                return new ResultVO(0, "查询成功", provinces);
            } else {
                return new ResultVO(1, "查询失败", null);
            }
        }else {
            return new ResultVO(1, "权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/city", method = RequestMethod.GET)
    @ApiOperation(value = "城市查询接口", notes = "用户根据省份查询所有该省份下所有城市的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getCity(String provinceId,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            List<City> cities = addressService.listCityByProvince(provinceId);
            if (cities != null) {
                return new ResultVO(0, "查询成功", cities);
            } else {
                return new ResultVO(1, "查询失败", null);
            }
        }else {
            return new ResultVO(1, "权限校验未通过", null);
        }
    }

    @RequestMapping(value = "/region", method = RequestMethod.GET)
    @ApiOperation(value = "市区查询接口", notes = "用户根据城市查询所有该城市下所有区的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO cityId(String cityId,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            List<Region> regions = addressService.listRegionByCity(cityId);
            if (regions != null) {
                return new ResultVO(0, "查询成功", regions);
            } else {
                return new ResultVO(1, "查询失败", null);
            }
        }else {
            return new ResultVO(1, "权限校验未通过", null);
        }
    }
}
