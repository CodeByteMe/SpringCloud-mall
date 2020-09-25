package com.mall.common.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "收获地址对象", description = "包含收获地址信息的对象")
public class Address {

    @ApiModelProperty(value = "订单退货申请ID" ,dataType = "integer", required = true)
    private Integer id;
    @ApiModelProperty(value = "地址UUID" ,dataType = "string", required = true)
    private String addressId;
    @ApiModelProperty(value = "用户UUID" ,dataType = "string", required = true)
    private String memberId;
    private String name;
    private String phoneNumber;
    @ApiModelProperty(value = "是否默认" ,dataType = "integer", required = true)
    private Integer defaultStatus;
    @ApiModelProperty(value = "邮编" ,dataType = "string", required = true)
    private String postCode;
    private String province;
    private String city;
    private String region;
    private String detailAddress;

    public Address(String addressId, String memberId, String name, String phoneNumber, Integer defaultStatus, String postCode, String province, String city, String region, String detailAddress) {
        this.addressId = addressId;
        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.defaultStatus = defaultStatus;
        this.postCode = postCode;
        this.province = province;
        this.city = city;
        this.region = region;
        this.detailAddress = detailAddress;
    }
}
