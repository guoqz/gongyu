package com.guoqz.gongyu.service.impl;

import com.guoqz.gongyu.bean.Menu;
import com.guoqz.gongyu.dao.MenuDao;
import com.guoqz.gongyu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getMenuListByRoleId(int roleId) {

        // dao层查询的菜单没有分一二级菜单
        // 需要对查询到的菜单进行处理，将二级菜单放到一级菜单下
        List<Menu> menuList = menuDao.selectMenuListByRoleId(roleId);

        // 遍历菜单列表，将所有的一级菜单放到单独的list集合中
        List<Menu> retMenu = new ArrayList<>();

        for (Menu menu : menuList) {
            // 拿到菜单的父级菜单id
            Integer parentId = menu.getParentId();
            if (0 == parentId) {
                retMenu.add(menu);
            }
        }

        // 再次遍历，将二级菜单添加一级菜单里面
        for (Menu menu : menuList) {
            // 拿到菜单的父级菜单id
            Integer parentId = menu.getParentId();
            if (0 == parentId) {
                // 说明是一级菜单，直接跳过
                continue;
            }

            // 如果是二级菜单，判断该二级菜单属于那个一级菜单的子菜单
            // 遍历一级菜单
            for (Menu parentMenu : retMenu) {
                // 首先获取改一级菜单的id值
                Integer id = parentMenu.getId();

                // 判断二级菜单的parentId值是否等于一级菜单的id，如果等于说明是该一级菜单的子菜单
                if (id == parentId) {
                    // 获取 parentMenu 的 children（所有子菜单的集合） 属性
                    List<Menu> children = parentMenu.getChildren();

                    // 该属性只是定义了，并未创建，所以判断非空
                    if (null == children) {
                        // 如果为空，就创建该对象
                        children = new ArrayList<>();
                        // 将该子菜单加入到集合中
                        children.add(menu);
                        parentMenu.setChildren(children);
                    } else {
                        // 不为空，直接添加
                        children.add(menu);
                    }
                }
            }

        }
        // 返回所有的一级菜单（包含子菜单）
        return retMenu;
    }
}
