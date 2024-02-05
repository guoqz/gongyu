package com.guoqz.gongyu.controller;

import com.guoqz.gongyu.bean.Flats;
import com.guoqz.gongyu.bean.Person;
import com.guoqz.gongyu.service.FlatsService;
import com.guoqz.gongyu.service.PersonService;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/flats")
public class FlatsController {

    @Autowired
    private FlatsService flatsService;

    @Autowired
    private PersonService personService;

    @RequestMapping("/flatsList")
    public String toFlatsList() {
        return "flats/flatsList";
    }


    @ResponseBody
    @RequestMapping("/getFlatsList")
    public Result getFlatsList(int page, int limit) {
        return flatsService.getFlatsList(page, limit);
    }


    @RequestMapping("/addFlats")
    public String toAddFlats() {
        return "flats/addFlats";
    }


    @ResponseBody
    @RequestMapping("/checkFlatsName/{name}")
    public Result isFlatsExists(@PathVariable("name") String name) {
        return flatsService.isFlatsExists(name);
    }


    @ResponseBody
    @RequestMapping("/singleUpload")
    public Map<String, Object> uploadImage(MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        // 获取上传时的文件名       例如   test.png
        String originalFilename = file.getOriginalFilename();
        System.out.println("原文件名：" + originalFilename);
        // 1)找到文件名中最后一个 . 的下标
        int index = originalFilename.lastIndexOf(".");
        // 2)获取文件名后缀      如  .png
        String suffix = originalFilename.substring(index);
        System.out.println("文件后缀名：" + suffix);

        // 获取文件后缀的目的是为了判断上传的文件是否是你想要的文件
        // 只能上传 .jpg .png格式的图片
        if (suffix.equals(".png") || suffix.equals(".jpg")) {
            // 为避免同名文件被覆盖，使用不重复的名称作为文件名，如系统时间
            // UUID作为文件名
            UUID uuid = UUID.randomUUID();// 生成一个随机的UUID
            String uuidStr = uuid.toString();
            System.out.println(uuidStr);    // 6818f523-7e2d-403c-865c-9462dc444c30
            uuidStr = uuidStr.replace("-", "");
            System.out.println(uuidStr);    // 6818f5237e2d403c865c9462dc444c30

            // 随机文件名
            String fileName = uuidStr + suffix;

            // 通过request对象获取 项目对象
            ServletContext context = request.getServletContext();

            // 找到项目根目录的绝对路径
            String realPath = context.getRealPath("/");
            System.out.println(realPath);   // E:\Code\IDEAProject\gongyu\src\main\webapp\

            // 把用户上传的文件存放到picture文件夹
            File pictureDir = new File(realPath + "picture");
            // 如果文件夹不存在就创建
            if (!pictureDir.exists()) {
                pictureDir.mkdirs();// 创建
            }

            // 创建图片的存储路径
            File savePath = new File(pictureDir, fileName);
            file.transferTo(savePath);// 存储上传的图片    到 \webapp\picture

            // 给前端返回数据
            map.put("code", 0);
            map.put("image", "/picture/" + fileName);

        } else {
            map.put("code", 100);
            map.put("mag", "图片格式有误,请上传png或jpg格式的图片");
        }
        return map;
    }


    @ResponseBody
    @RequestMapping("/insertFlats")
    public Result addFlats(Flats flats) {
        return flatsService.addFlats(flats);
    }


    @RequestMapping("/editFlats/{id}")
    public String toEditFlats(@PathVariable("id") Integer id, HttpServletRequest request) {
        Flats flats = flatsService.getFlatsById(id);
        request.setAttribute("aFlats", flats);
        return "flats/editFlats";
    }


    @ResponseBody
    @RequestMapping("/updateFlats")
    public Result updateFlats(Flats flats) {
        return flatsService.updateFlats(flats);
    }


    @ResponseBody
    @RequestMapping("/deleteFlats/{id}")
    public Result deleteFlatsById(@PathVariable("id") Integer id,HttpServletRequest request) {
        // 公寓里有人，不能删除
        // 先根据公寓id查询人员集合
        List<Person> personList = personService.getPersonsByFlatsId(id);

        if (personList.size() == 0) {
            // 公寓没人，可以删除
            // 1)删公寓对应的图片
            Flats flats = flatsService.getFlatsById(id);
            String flatsPhoto = flats.getFlatsPhoto();
            // 获取当前项目对象
            ServletContext context = request.getServletContext();
            // 获取图片绝对路径，从盘符开始的路径
            String realPath = context.getRealPath(flatsPhoto);
            // 根据图片地址创建一个文件对象，删除文件
            File file = new File(realPath);
            file.delete();

            // 2)删公寓
            return flatsService.deleteFlatsById(id);

        } else {
            return Result.error(101, "公寓中还有人，不能删除哦");
        }

    }

}
