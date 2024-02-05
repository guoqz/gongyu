package com.guoqz.gongyu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoqz.gongyu.bean.Company;
import com.guoqz.gongyu.dao.CompanyDao;
import com.guoqz.gongyu.service.CompanyService;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> getAllCompany() {
        return companyDao.selectAllCompany();
    }

    @Override
    public Result getCompanyList(int page, int limit) {

        // 开启分页
        PageHelper.startPage(page, limit);

        // 查询所有公司（包含人员）
//        List<Company> companies = companyDao.selectAllCompany();
        List<Company> companies = companyDao.selectAllCompanyIncludePerson();

        PageInfo<Company> pageInfo = new PageInfo<>(companies);

        Result result = new Result(0, "获取公司列表成功！", pageInfo.getTotal(), companies);

        return result;
    }

    @Override
    public Result isCompanyExists(String name) {
        Company company = companyDao.selectCompanyByName(name);

        if (company == null) {
            return Result.ok("该公司名称可以使用");
        } else {
            return Result.error(100, "该公司名称已存在");
        }
    }

    @Override
    public Result addCompany(Company company) {
        int count = companyDao.insertCompany(company);
        if (count > 0) {
            return Result.ok("添加公司成功");
        } else {
            return Result.error(100, "添加公司失败");
        }
    }

    @Override
    public Company getCompanyById(int id) {
        return companyDao.selectCompanyById(id);
    }

    @Override
    public Result updateCompany(Company company) {
        int count = companyDao.updatePerson(company);
        if (count > 0) {
            return Result.ok("公司更新成功");
        } else {
            return Result.error(100, "公司更新失败");
        }
    }

    @Override
    public Result deleteCompanyById(int id) {
        int count = companyDao.deleteCompanyById(id);
        if (count > 0) {
            return Result.ok("删除成功");
        } else {
            return Result.error(101, "删除失败");
        }
    }


}
