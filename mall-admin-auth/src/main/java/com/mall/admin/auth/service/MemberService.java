package com.mall.admin.auth.service;

public interface MemberService {
    public MemberUser loginByMemberUser(String username, String password);

    public int insertMemberUser(MemberUser memberUser);
}
