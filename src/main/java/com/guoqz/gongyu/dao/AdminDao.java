package com.guoqz.gongyu.dao;

import com.guoqz.gongyu.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminDao {

    /**
     * 根据用户名和密码查询管理员
     *
     * @param username
     * @param password
     * @return
     */
    Admin selectAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    /**
     * 更新管理员信息
     *
     * @param admin
     * @return
     */
    int updateAdmin(Admin admin);

    /**
     * 根据id查询管理员
     * @param id
     * @return
     */
    Admin selectAdminById(int id);

    /**
     * 查询所有管理员
     *
     * @return
     */
    List<Admin> getAllAdmin();


    /**
     * 根据用户名查询管理员
     *
     * @param username
     * @return
     */
    Admin selectAdminByUsername(String username);


    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    int insertAdmin(Admin admin);


    /**
     * 根据id删除管理员
     * @param id
     * @return
     */
    public int deleteAdminById(Integer id);


    public List<Admin> selectAdminListByRoleId(Integer roleId);
}
