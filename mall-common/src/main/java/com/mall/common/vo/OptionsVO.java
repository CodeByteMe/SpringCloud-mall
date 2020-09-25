package com.mall.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 *   作者：官宣轩
 *   日期：2020-09-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OptionsVO {

    private Integer value;
    private String label;
    private OptionsVO children;

}
