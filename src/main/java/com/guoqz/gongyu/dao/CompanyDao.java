package com.guoqz.gongyu.dao;

import com.guoqz.gongyu.bean.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompanyDao {

    /**
     * 查询所有的公司（不含人员信息）
     * @return
     */
    public List<Company> selectAllCompany();


    /**
     * 根据id查询公司
     * @param id
     * @return
     */
    public Company selectCompanyById(int id);


    /**
     * 查询所有的公司（包含人员信息）
     * @return
     */
    public List<Company> selectAllCompanyIncludePerson();


    /**
     * 根据公司名称查询公司
     * @param name
     * @return
     */
    public Company selectCompanyByName(String name);


    /**
     * 添加公司
     * @param company
     * @return
     */
    public int insertCompany(Company company);


    /**
     * 更新公司信息
     * @param company
     * @return
     */
    public int updatePerson(Company company);


    /**
     * 根据id删除公司
     * @param id
     * @return
     */
    public int deleteCompanyById(int id);

}
