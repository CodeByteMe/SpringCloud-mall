package com.mall.admin.auth.service;

import com.mall.common.pojo.MemberUser;

public interface MemberService {
    public MemberUser loginByMemberUser(String username, String password);

    public int insertMemberUser(MemberUser memberUser);
}
