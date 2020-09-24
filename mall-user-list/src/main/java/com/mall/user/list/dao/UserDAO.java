package com.mall.user.list.dao;

import com.mall.common.pojo.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {
    public List<AdminUser> selectUserListBycompanyId(@Param("companyId") String companyId);
    public String selectCompanyId(String adminId);//通过登录的id拿到公司id

}
