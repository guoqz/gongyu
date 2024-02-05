package com.guoqz.gongyu.service;

import com.guoqz.gongyu.bean.Admin;
import com.guoqz.gongyu.util.Result;

import java.util.List;

public interface AdminService {

    /**
     * 根据用户名和密码查询admin
     * @param username
     * @param password
     * @return
     */
    public Admin getAdmin(String username, String password);


    /**
     * 更新admin
     * @param admin
     * @return
     */
    public int updateAdmin(Admin admin);


    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    public Admin selectAdminById(int id);

    /**
     * 根据页码和每页条数获取信息
     * @param page
     * @param limit
     * @return
     */
    public Result getAdminList(Integer page, Integer limit);


    /**
     * 判断管理员名称是否存在
     * @param username
     * @return
     */
    public Result isAdminExist(String username);


    /**
     * 添加管理员
     * @param admin
     * @return
     */
    public Result addAdmin(Admin admin);


    public Result deleteAdminById(Integer id);


    public List<Admin> getAdminListByRoleId(Integer roleId);
}
