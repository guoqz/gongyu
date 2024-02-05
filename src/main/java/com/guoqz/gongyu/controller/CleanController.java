package com.guoqz.gongyu.controller;

import com.guoqz.gongyu.bean.Clean;
import com.guoqz.gongyu.bean.Flats;
import com.guoqz.gongyu.bean.Person;
import com.guoqz.gongyu.service.CleanService;
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
@RequestMapping("/clean")
public class CleanController {

    @Autowired
    private FlatsService flatsService;

    @Autowired
    private CleanService cleanService;

    @Autowired
    private PersonService personService;

    @RequestMapping("/cleanList")
    public String toCleanList() {
        return "clean/cleanList";
    }


    @ResponseBody
    @RequestMapping("/getCleanList")
    public Result getCleanList(int page, int limit) {
        return cleanService.getCleanList(page, limit);
    }


    @RequestMapping("/addClean")
    public String toAddClean(HttpServletRequest request) {
        // 跳转之前，查询所有公寓，用于下拉框获取数据
        List<Flats> flats = flatsService.getAllFlats();
        request.setAttribute("flats", flats);

        return "clean/addClean";
    }


    @ResponseBody
    @RequestMapping("/getPersonsByFlatsId/{id}")
    public List<Person> getPersonsByFlatsId(@PathVariable("id") Integer id) {
        return personService.getPersonsByFlatsId(id);
    }


    @ResponseBody
    @RequestMapping("/insertClean")
    public Result addClean(Clean clean) {
        //System.out.println(clean);
        return cleanService.addClean(clean);
    }


    @RequestMapping("/editClean/{id}")
    public String toEditClean(@PathVariable("id") Integer id, HttpServletRequest request) {
        // 进入编辑页面之前先查询要编辑的信息
        // 查询清扫记录
        Clean clean = cleanService.getCleanById(id);
        request.setAttribute("clean", clean);

        // 查询所有公寓
        List<Flats> flats = flatsService.getAllFlats();
        request.setAttribute("flats", flats);

        // 根据公寓id查询人员列表
        List<Person> persons = personService.getPersonsByFlatsId(clean.getFlatsId());
        request.setAttribute("persons", persons);

        return "clean/editClean";
    }


    @ResponseBody
    @RequestMapping("/updateClean")
    public Result updateClean(Clean clean) {
        return cleanService.updateClean(clean);
    }


    @ResponseBody
    @RequestMapping("deleteClean/{id}")
    public Result deleteCleanById(@PathVariable("id") Integer id) {
        return cleanService.deleteCleanById(id);
    }

}
