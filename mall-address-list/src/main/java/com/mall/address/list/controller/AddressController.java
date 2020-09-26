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
import java.util.UUID;

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

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "前台用户地址删除接口", notes = "用户删除地址的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO deleteAddress(@RequestParam String addressId,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            int i = addressService.deleteByIdAndMemberId(addressId);
            return new ResultVO(0, "删除成功");
        }else {
            return new ResultVO(1, "权限校验未通过");
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "前台用户地址添加接口", notes = "用户新增地址的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO insertAddress(@RequestParam String province,
                                  @RequestParam String city,
                                  @RequestParam String region,
                                  @RequestParam String name,
                                  @RequestParam String phoneNumber,
                                  @RequestParam String postCode,
                                  @RequestParam String detailAddress,
                                  @RequestParam @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            Province province1 = addressService.getProvince(province);
            String provinceName = province1.getProvinceName();
            City city1 = addressService.getCity(city);
            String cityName = city1.getCityName();
            Region region1 = addressService.getRegion(region);
            String regionName = region1.getRegionName();
            String addressId = UUID.randomUUID().toString().replace("-", "");
            int i = addressService.insertAddress(new Address(addressId,memberId,name,phoneNumber,0,postCode,provinceName,cityName,regionName,detailAddress));
            if (i > 0) {
                return new ResultVO(0, "添加成功");
            } else {
                return new ResultVO(1, "添加失败");
            }
        }else {
            return new ResultVO(1, "权限校验未通过");
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改地址接口", notes = "用户修改地址信息的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO updateAddress(@RequestParam String addressId,
                                  @RequestParam String name,
                                  @RequestParam String phoneNumber,
                                  @RequestParam String postCode,
                                  @RequestParam String province,
                                  @RequestParam String city,
                                  @RequestParam String region,
                                  @RequestParam String detailAddress,
                                  @RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {

            boolean b = addressService.updateAddress(new Address(addressId,memberId,name,phoneNumber,0,postCode,province,city,region,detailAddress));
            if (b) {
                return new ResultVO(0, "修改成功");
            } else {
                return new ResultVO(1, "修改失败");
            }
        }else {
            return new ResultVO(1, "权限校验未通过");
        }
    }

    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ApiOperation(value = "修改默认地址接口", notes = "用户修改默认地址信息的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO updateAddressStatus(String addressId,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            boolean b = addressService.updateAddressStatus(addressId,1,memberId);
            if (b) {
                return new ResultVO(0, "设置默认地址成功");
            } else {
                return new ResultVO(1, "设置默认地址失败");
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

    @RequestMapping(value = "/addressDetail", method = RequestMethod.GET)
    @ApiOperation(value = "地址查询接口", notes = "用户根据地址id查询地址详细信息的接口")
    @ApiImplicitParam(name = "token", value = "token验证信息", required = true, type = "String")
    public ResultVO getAddressDetail(@RequestParam("addressId") String addressId,@RequestHeader(required = true) String token) {
        // 验证token
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        // 获取解析的token中的用户名、id等
        String memberId = jws.getBody().getId();
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)) {
            List<Address> addressByAddressId = addressService.getAddressByAddressId(addressId);
            if (addressByAddressId != null) {
                return new ResultVO(0, "查询成功", addressByAddressId);
            } else {
                return new ResultVO(1, "查询失败", null);
            }
        }else {
            return new ResultVO(1, "权限校验未通过", null);
        }
    }
}
