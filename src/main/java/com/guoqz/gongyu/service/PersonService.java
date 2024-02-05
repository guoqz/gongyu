package com.guoqz.gongyu.service;

import com.guoqz.gongyu.bean.Person;
import com.guoqz.gongyu.util.Result;

import java.util.List;

public interface PersonService {

    /**
     * 分页查询人员
     * @param page
     * @param limit
     * @param name
     * @param companyId
     * @param flatsId
     * @return
     */
    public Result getPersonList(int page, int limit, String name, Integer companyId, Integer flatsId);


    /**
     * 判断用户名是否存在
     * @param name
     * @return
     */
    public Result isPersonExist(String name);


    public Result addPerson(Person person);


    public Person getPersonById(Integer id);

    public Result updatePerson(Person person);

    public Result deletePersonById(Integer id);

    public List<Person> getPersonsByCompanyId(int companyId);

    public List<Person> getPersonsByFlatsId(int flatsId);

}
