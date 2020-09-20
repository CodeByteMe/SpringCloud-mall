package com.mall.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ResultVO
 *
 * @Author BessCroft
 * @Date 2020/9/20 19:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultVO {
    private Integer code;
    private String msg;
    private Object data;
}