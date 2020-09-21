package com.mall.role.list.service.Impl;

import com.mall.role.list.dao.RoleDAO;
import com.mall.role.list.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDAO roleDAO;
}
