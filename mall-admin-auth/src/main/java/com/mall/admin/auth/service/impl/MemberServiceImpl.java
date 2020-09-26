package com.mall.admin.auth.service.impl;

import com.mall.admin.auth.dao.MemberDAO;
import com.mall.admin.auth.service.MemberService;
import com.mall.common.pojo.MemberUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberDAO memberDAO;

    @Override
    public MemberUser loginByMemberUser(String username, String password) {
        return memberDAO.getMemberUser(username, password);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ,propagation = Propagation.REQUIRED)
    public int insertMemberUser(MemberUser memberUser) {
        return memberDAO.insertMemberUser(memberUser);
    }
}
