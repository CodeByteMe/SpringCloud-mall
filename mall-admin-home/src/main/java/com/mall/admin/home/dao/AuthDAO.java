package com.mall.admin.home.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * AuthDAO
 *
 * @Author BessCroft
 * @Date 2020/9/21 23:29
 */
@FeignClient("mall-admin-auth")
public interface AuthDAO {
    /**
     * 此处@RequestMapping注解用于声明服务访问的服务
     * @return
     */
    @RequestMapping(value="/auth/auth/getId",method = RequestMethod.POST)
    public String getId(String adminId);
}
