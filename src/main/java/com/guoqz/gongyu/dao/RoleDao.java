package com.guoqz.gongyu.dao;

import com.guoqz.gongyu.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleDao {

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> selectAllRole();


    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    public Role selectRoleById(int id);


}
