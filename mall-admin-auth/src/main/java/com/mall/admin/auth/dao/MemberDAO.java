package com.mall.admin.auth.dao;

import org.apache.ibatis.annotations.Param;

public interface MemberDAO {
    public MemberUser getMemberUser(@Param("username") String username,
                                     @Param("password") String password);//用户登录验证

    public int insertMemberUser(MemberUser memberUser);//用户注册
}
