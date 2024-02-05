package com.guoqz.gongyu.service;

import com.guoqz.gongyu.bean.Role;

import java.util.List;

public interface RoleService {

    /**
     * 获取所有的角色
     * @return
     */
    public List<Role> getAllRole();


    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    public Role selectRoleById(int id);

}
