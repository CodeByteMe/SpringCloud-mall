package com.mall.user.action.dao;

import com.mall.common.pojo.AdminUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDAO {
    public int userCount();//总条数
    public List<AdminUser> selectUserListBycompanyid(@Param("companyId") String companyId,//分页查询
                                                     @Param("start") int start,
                                                     @Param("limit") int limit);
    public int addUser(AdminUser adminUser);//添加用户
    public String getCompanyId(String adminId);//通过登录的id拿到公司id

}
