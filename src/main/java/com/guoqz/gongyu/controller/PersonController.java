package com.guoqz.gongyu.controller;

import com.guoqz.gongyu.bean.Company;
import com.guoqz.gongyu.bean.Flats;
import com.guoqz.gongyu.bean.Person;
import com.guoqz.gongyu.service.CompanyService;
import com.guoqz.gongyu.service.FlatsService;
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
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private FlatsService flatsService;

    @Autowired
    private PersonService personService;


    /**
     * 跳转人员列表页面
     *
     * @return
     */
    @RequestMapping("/personList")
    public String toPerson(HttpServletRequest request) {
        // 携带数据进入页面     下拉框查询公司列表、公寓列表
        // 查询公司下拉框数据
        List<Company> companies = companyService.getAllCompany();
        request.setAttribute("companies", companies);

        // 查询公司下拉框数据
        List<Flats> flats = flatsService.getAllFlats();
        request.setAttribute("flats", flats);

        return "person/personList";
    }


    /**
     * 根据条件分页查询
     *
     * @param page
     * @param limit
     * @param name
     * @param companyId
     * @param flatsId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllPersons")
    public Result getAllPersons(Integer page, Integer limit, String name, Integer companyId, Integer flatsId) {
//        return personService.getPersonList(page, limit);
        return personService.getPersonList(page, limit, name, companyId, flatsId);
    }


    /**
     * 跳转添加人员页面
     *
     * @return
     */
    @RequestMapping("/addPerson")
    public String toAddPerson(HttpServletRequest request) {

        // 跳转之前把所有公司、所有公寓查出来，用于填充下拉框数据
        List<Company> companies = companyService.getAllCompany();
        request.setAttribute("companies", companies);

        List<Flats> flats = flatsService.getAllFlats();
        request.setAttribute("flats", flats);

        return "person/addPerson";
    }


    /**
     * 检测姓名是否存在（待定）
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkPersonByName/{name}")
    public Result checkPersonByName(@PathVariable("name") String name) {
        return personService.isPersonExist(name);
    }


    /**
     * 添加人员信息
     *
     * @param person
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertPerson")
    public Result addPerson(Person person) {
        //System.out.println(person);
        return personService.addPerson(person);
    }


    @RequestMapping("/editPerson/{id}")
    public String toEditPerson(@PathVariable("id") Integer id, HttpServletRequest request) {

        // 进入编辑页面之前，需要根据id查询被修改人的完整信息
        Person editPerson = personService.getPersonById(id);
        request.setAttribute("editPerson", editPerson);

        // 查询所有的公司，用于获取下拉框数据
        List<Company> companies = companyService.getAllCompany();
        request.setAttribute("companies", companies);

        // ，查询所有公寓，用于获取下拉框数据
        List<Flats> flats = flatsService.getAllFlats();
        request.setAttribute("flats", flats);

        return "person/editPerson";
    }


    /**
     * 更新人员信息
     *
     * @param person
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePerson")
    public Result updatePerson(Person person) {
        //System.out.println(person);
        return personService.updatePerson(person);
    }


    /**
     * 根据id删除人员
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deletePersonById/{id}")
    public Result deletePersonById(@PathVariable("id") Integer id) {
        return personService.deletePersonById(id);
    }


}
