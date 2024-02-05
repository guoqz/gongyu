package com.guoqz.gongyu.service;

import com.guoqz.gongyu.bean.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 根据角色id获取菜单列表
     * @param roleId
     * @return
     */
    public List<Menu> getMenuListByRoleId(int roleId);

}
