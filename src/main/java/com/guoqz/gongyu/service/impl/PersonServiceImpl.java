package com.guoqz.gongyu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoqz.gongyu.bean.Person;
import com.guoqz.gongyu.dao.PersonDao;
import com.guoqz.gongyu.service.PersonService;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Result getPersonList(int page, int limit, String name, Integer companyId, Integer flatsId) {

        // 开启分页
        PageHelper.startPage(page, limit);
        // 获取人员列表集合
//        List<Person> personList = personDao.selectAllPerson();
        List<Person> personList = personDao.selectPersons(name, companyId, flatsId);
        System.out.println(personList);

        PageInfo<Person> pageInfo = new PageInfo<>(personList);

        return new Result(0, "获取人员列表成功", pageInfo.getTotal(), personList);
    }


    @Override
    public Result isPersonExist(String name) {

        Person person = personDao.selectPersonByName(name);

        if (null == person) {
            return Result.ok("人员名称可用");
        } else {
            return Result.error(100, "人员名称已存在");
        }
    }


    @Override
    public Result addPerson(Person person) {

        int count = personDao.insertPerson(person);
        System.out.println(count);

        if (count > 0){
            return Result.ok("添加人员成功");
        }else {
            return Result.error(100,"添加人员失败");
        }
    }

    @Override
    public Person getPersonById(Integer id) {
        return personDao.selectPersonById(id);
    }

    @Override
    public Result updatePerson(Person person) {
        int count = personDao.updatePerson(person);
        if (count > 0) {
            return Result.ok("修改人员信息成功");
        }else {
            return Result.error(100,"修改人员信息失败");
        }
    }

    @Override
    public Result deletePersonById(Integer id) {

        int count = personDao.deletePersonById(id);
        if (count > 0) {
            return Result.ok("删除人员成功");
        }else {
            Result.error(100,"删除人员失败");
        }

        return null;
    }

    @Override
    public List<Person> getPersonsByCompanyId(int companyId) {
        return personDao.selectPersonByCompanyId(companyId);
    }

    @Override
    public List<Person> getPersonsByFlatsId(int flatsId) {
        return personDao.selectPersonByFlatsId(flatsId);
    }

}
