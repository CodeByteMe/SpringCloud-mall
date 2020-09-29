package com.mall.admin.home.rallback;

import com.mall.admin.home.service.AuthService;

/**
 * FeignServiceFailure
 *
 * @Author BessCroft
 * @Date 2020/9/28 17:44
 */
public class AuthServiceFallBack implements AuthService {

    @Override
    public String getId(String adminId) {
        return "网络繁忙，请稍后再试！";
    }
}
