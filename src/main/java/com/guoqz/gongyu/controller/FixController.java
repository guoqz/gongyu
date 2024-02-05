package com.guoqz.gongyu.controller;

import com.guoqz.gongyu.bean.Admin;
import com.guoqz.gongyu.bean.Fix;
import com.guoqz.gongyu.bean.Flats;
import com.guoqz.gongyu.service.AdminService;
import com.guoqz.gongyu.service.FixService;
import com.guoqz.gongyu.service.FlatsService;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/fix")
public class FixController {

    @Autowired
    private FixService fixService;

    @Autowired
    private FlatsService flatsService;

    @Autowired
    private AdminService adminService;


    @RequestMapping("/fixList")
    public String toFixList() {
        return "fix/fixList";
    }


    @ResponseBody
    @RequestMapping("getFixList")
    public Result getFixList(int page, int limit) {
        return fixService.getFixList(page, limit);
    }


    @RequestMapping("/addFix")
    public String toAddFix(HttpServletRequest request) {
        // 跳转之前查出所有的公寓、和维修人员
        List<Flats> flats = flatsService.getAllFlats();
        request.setAttribute("flats", flats);

        // 查询所有维修人员
        List<Admin> persons = adminService.getAdminListByRoleId(3);
        request.setAttribute("persons", persons);

        return "fix/addFix";
    }


    @ResponseBody
    @RequestMapping("/insertFix")
    public Result addFix(Fix fix) {
        return fixService.addFix(fix);
    }


    @RequestMapping("/editFix/{id}")
    public String toEditFix(@PathVariable("id") Integer id, HttpServletRequest request) {
        // 跳转页面之前，需要根据id查询维修记录，查询所有公寓、查询维修人员列表
        Fix fix = fixService.getFixById(id);
        request.setAttribute("fix", fix);

        List<Flats> flats = flatsService.getAllFlats();
        request.setAttribute("flats", flats);

        List<Admin> persons = adminService.getAdminListByRoleId(3);
        request.setAttribute("persons", persons);

        return "fix/editFix";
    }



    @ResponseBody
    @RequestMapping("/updateFix")
    public Result updateFix(Fix fix){
        return fixService.updateFix(fix);
    }


    @ResponseBody
    @RequestMapping("deleteFix/{id}")
    public Result deleteFixById(@PathVariable("id") Integer id){
        return fixService.deleteFixById(id);
    }

}
