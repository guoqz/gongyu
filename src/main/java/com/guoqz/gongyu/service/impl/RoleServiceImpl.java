package com.guoqz.gongyu.service.impl;

import com.guoqz.gongyu.bean.Role;
import com.guoqz.gongyu.dao.RoleDao;
import com.guoqz.gongyu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRole() {
        return roleDao.selectAllRole();
    }

    @Override
    public Role selectRoleById(int id) {
        return roleDao.selectRoleById(id);
    }
}
