package com.guoqz.gongyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoqz.gongyu.bean.Admin;
import com.guoqz.gongyu.dao.AdminDao;
import com.guoqz.gongyu.service.AdminService;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getAdmin(String username, String password) {
        return adminDao.selectAdminByUsernameAndPassword(username, password);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminDao.updateAdmin(admin);
    }

    @Override
    public Admin selectAdminById(int id) {
        return adminDao.selectAdminById(id);
    }

    @Override
    public Result getAdminList(Integer page, Integer limit) {

        // 使用pageHelper插件实现分页查询
        // 1、使用PageHelper.start(页码,每页条数);
        PageHelper.startPage(page, limit);

        // 2、
        // 此处虽然是查询所有，但是 pageHelper插件会在sql语句里加上 limit 关键字进行分页
        List<Admin> adminList = adminDao.getAllAdmin();

        // 3、根据查询结果，创建一个PageInfo对象，该对象中有我们查询到的数据
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList);
        //System.out.println("total:" + pageInfo.getTotal());

        return new Result(0, "获取管理员列表成功", pageInfo.getTotal(), adminList);
    }

    @Override
    public Result isAdminExist(String username) {

        Admin admin = adminDao.selectAdminByUsername(username);

        if (null == admin) {
            return Result.ok("用户名可用");
        } else {
            return Result.error(100, "用户名已存在");
        }

    }

    @Override
    public Result addAdmin(Admin admin) {
        int count = adminDao.insertAdmin(admin);

        if (count == 0) {
            // 影响条数为0,添加失败
            return Result.error(100, "管理员添加失败");
        } else {
            return Result.ok("管理员添加成功");
        }

    }

    @Override
    public Result deleteAdminById(Integer id) {
        int count = adminDao.deleteAdminById(id);
        if (0 == count) {
            return Result.error(100, "删除管理员失败");
        } else {
            return Result.ok("删除管理员成功");
        }

    }

    @Override
    public List<Admin> getAdminListByRoleId(Integer roleId) {
        return adminDao.selectAdminListByRoleId(roleId);
    }
}
