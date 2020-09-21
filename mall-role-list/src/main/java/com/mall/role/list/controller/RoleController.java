package com.mall.role.list.controller;

import com.mall.role.list.service.RoleService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RequestMapping("/role")
@RestController
public class RoleController {
    @Resource
    private RoleService roleService;
}
