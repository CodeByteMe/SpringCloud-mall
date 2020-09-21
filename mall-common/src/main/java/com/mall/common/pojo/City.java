package com.mall.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 *   作者：官宣轩
 *   日期：2020-09-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class City {

    private String cityId;
    private String cityName;
    private String provinceId;

}
