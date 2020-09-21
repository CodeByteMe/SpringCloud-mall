package com.mall.user.list.dao;

import com.mall.common.pojo.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {
    public int userCount();
    public List<AdminUser> selectUserListBycompanyid(@Param("companyId") String companyId,
                                                      @Param("start") int start,
                                                      @Param("limit") int limit);
}
