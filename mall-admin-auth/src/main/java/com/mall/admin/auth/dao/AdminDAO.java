package com.mall.admin.auth.dao;

import com.mall.common.pojo.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * AdminDAO
 *
 * @Author BessCroft
 * @Date 2020/9/20 21:21
 */
public interface AdminDAO {
    public AdminUser getAdminUser(@Param("username") String username,
                                  @Param("password") String password);
}
