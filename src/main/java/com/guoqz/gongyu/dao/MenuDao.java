package com.guoqz.gongyu.dao;

import com.guoqz.gongyu.bean.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MenuDao {

    /**
     * 根据roleId查询用户的权限
     * @param roleId
     * @return
     */
    public List<Menu> selectMenuListByRoleId(int roleId);

}
