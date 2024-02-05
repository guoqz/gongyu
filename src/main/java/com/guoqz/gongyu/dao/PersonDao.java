package com.guoqz.gongyu.dao;

import com.guoqz.gongyu.bean.Admin;
import com.guoqz.gongyu.bean.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonDao {

    /**
     * 查询所有人员信息
     *
     * @return
     */
    public List<Person> selectAllPerson();


    /**
     * 根据条件检索
     *
     * @param name
     * @param companyId
     * @param flatsId
     * @return
     */
    public List<Person> selectPersons(@Param("name") String name, @Param("companyId") Integer companyId, @Param("flatsId") Integer flatsId);


    /**
     * 根据名称查询人员
     *
     * @param name
     * @return
     */
    Person selectPersonByName(String name);


    /**
     * 添加人员
     *
     * @param person
     * @return
     */
    public int insertPerson(Person person);


    /**
     * 根基id查询人员信息
     *
     * @param id
     * @return
     */
    public Person selectPersonById(Integer id);


    /**
     * 更新人员信息
     *
     * @param person
     * @return
     */
    public int updatePerson(Person person);


    /**
     * 根据id删除人员
     *
     * @param id
     * @return
     */
    public int deletePersonById(Integer id);


    /**
     * 根据公司id查询人员
     *
     * @param companyId
     * @return
     */
    public List<Person> selectPersonByCompanyId(int companyId);


    /**
     * 根据公寓id查询人员
     * @param flatsId
     * @return
     */
    public List<Person> selectPersonByFlatsId(Integer flatsId);

}
