package com.guoqz.gongyu.dao;

import com.guoqz.gongyu.bean.Fix;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FixDao {

    /**
     * 查询所有维修记录
     * @return
     */
    public List<Fix> selectAllFix();


    /**
     * 添加维修记录
     * @param fix
     * @return
     */
    public int insertFix(Fix fix);


    /**
     * 根据id查询维修记录
     * @param id
     * @return
     */
    public Fix selectFixById(int id);


    /**
     * 更新维修记录
     * @param fix
     * @return
     */
    public int updateFix(Fix fix);


    /**
     * 根据id删除维修记录
     * @return
     */
    public int deleteFixById(int id);

}
