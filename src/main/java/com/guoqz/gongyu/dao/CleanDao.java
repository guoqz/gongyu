package com.guoqz.gongyu.dao;

import com.guoqz.gongyu.bean.Clean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CleanDao {

    /**
     * 获取所有的清扫记录
     *
     * @return
     */
    public List<Clean> selectAllClean();


    /**
     * 添加清扫记录
     *
     * @param clean
     * @return
     */
    public int insertClean(Clean clean);


    /**
     * 根据id查询打扫记录
     *
     * @param id
     * @return
     */
    public Clean selectCleanById(int id);


    /**
     * 更新清扫记录
     *
     * @param clean
     * @return
     */
    public int updateClean(Clean clean);


    /**
     * 根据id删除清扫记录
     *
     * @param id
     * @return
     */
    public int deleteCleanById(int id);

}
