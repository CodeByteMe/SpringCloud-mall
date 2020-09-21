package com.mall.admin.auth.dao;

import com.mall.common.pojo.AdminUser;
import io.swagger.models.auth.In;
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

    /**
     * 根据用户UUID查询自增长id
     * @param adminId
     * @return
     */
    public Integer getId(String adminId);
}
