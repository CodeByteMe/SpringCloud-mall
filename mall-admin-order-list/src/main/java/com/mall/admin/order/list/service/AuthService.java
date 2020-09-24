package com.mall.admin.order.list.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * AuthService
 *
 * @Author BessCroft
 * @Date 2020/9/24 17:57
 */
@FeignClient("mall-admin-auth")
public interface AuthService {
    /**
     * 此处@RequestMapping注解用于声明服务访问的服务
     * @return
     */
    @RequestMapping(value="/auth/getId",method = RequestMethod.POST)
    public String getId(String adminId);
}
