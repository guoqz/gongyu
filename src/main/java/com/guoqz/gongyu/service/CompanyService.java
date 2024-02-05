package com.guoqz.gongyu.service;

import com.guoqz.gongyu.bean.Company;
import com.guoqz.gongyu.util.Result;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface CompanyService {

    public List<Company> getAllCompany();

    public Result getCompanyList(int page, int limit);

    public Result isCompanyExists(String name);

    public Result addCompany(Company company);

    public Company getCompanyById(int id);

    public Result updateCompany(Company company);

    public Result deleteCompanyById(int id);

}
