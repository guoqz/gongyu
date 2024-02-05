package com.guoqz.gongyu.dao;

import com.guoqz.gongyu.bean.Flats;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FlatsDao {

    /**
     * 查询所有的公寓（不含人员信息）
     * @return
     */
    public List<Flats> selectAllFlats();


    /**
     * 根据id查询公寓
     * @param id
     * @return
     */
    public Flats selectFlatsById(int id);


    /**
     * 查询所有的公寓（包含人员信息）
     * @return
     */
    public List<Flats> selectAllFlatsIncludePerson  ();


    /**
     * 根据名称查询公寓
     * @param name
     * @return
     */
    public Flats selectFlatsByName(String name);


    /**
     * 添加公寓
     * @param flats
     * @return
     */
    public int insertFlats(Flats flats);


    /**
     * 更新公寓信息
     * @param flats
     * @return
     */
    public int updateFlats(Flats flats);


    /**
     * 根据id删除公寓
     * @param id
     * @return
     */
    public int deleteFlatsById(int id);

}
