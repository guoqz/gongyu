package com.guoqz.gongyu.controller;

import com.guoqz.gongyu.bean.Company;
import com.guoqz.gongyu.bean.Person;
import com.guoqz.gongyu.service.CompanyService;
import com.guoqz.gongyu.service.PersonService;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PersonService personService;


    @RequestMapping("/companyList")
    public String toCompanyList() {
        return "company/companyList";
    }


    @ResponseBody
    @RequestMapping("/getCompanyList")
    public Result getCompanyList(int page, int limit) {
        return companyService.getCompanyList(page, limit);
    }


    @RequestMapping("/addCompany")
    public String toAddCompany(){
        return "company/addCompany";
    }



    @ResponseBody
    @RequestMapping("/checkCompanyName/{name}")
    public Result isCompanyExists(@PathVariable("name") String name){
        return companyService.isCompanyExists(name);
    }


    @ResponseBody
    @RequestMapping("/insCompany")
    public Result addCompany(Company company){
        return companyService.addCompany(company);
    }


    @RequestMapping("/editCompany/{id}")
    public String toEditCompany(@PathVariable("id") Integer id, HttpServletRequest request){
        Company company = companyService.getCompanyById(id);
        request.setAttribute("company",company);
        return "/company/editCompany";
    }


    @ResponseBody
    @RequestMapping("/updCompany")
    public Result updateCompany(Company company){
        return companyService.updateCompany(company);
    }


    @ResponseBody
    @RequestMapping("/delCompany/{id}")
    public Result deleteCompanyById(@PathVariable("id") Integer id){
        List<Person> personList = personService.getPersonsByCompanyId(id);
        if (personList.size() != 0){
            // 公司里有人，不能删除
            return Result.error(100,"公司里有人员，不能删除");
        }else {
            return companyService.deleteCompanyById(id);
        }
    }

}
