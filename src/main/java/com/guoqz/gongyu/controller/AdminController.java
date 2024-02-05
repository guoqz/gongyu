package com.guoqz.gongyu.controller;

import com.guoqz.gongyu.bean.Admin;
import com.guoqz.gongyu.bean.Menu;
import com.guoqz.gongyu.bean.Role;
import com.guoqz.gongyu.service.AdminService;
import com.guoqz.gongyu.service.MenuService;
import com.guoqz.gongyu.service.RoleService;
import com.guoqz.gongyu.util.EncryptUtil;
import com.guoqz.gongyu.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;


    /**
     * 登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result doLogin(String username, String password, HttpSession session) {
//        System.out.println("前端接收数据："+username+","+password);

        // 将前端传过来的密码进行加密
        String pwdMd5 = EncryptUtil.encrypt(password);
//        System.out.println(pwdMd5);

        // 查询数据库
        Admin admin = adminService.getAdmin(username, pwdMd5);
//        System.out.println(admin);
        if (null == admin) {
            // 没查到，用户名或密码错误
            // 返回登录失败信息
            return Result.error(1, "用户名或密码错误");
        } else {
            // 查到了

            // 将查到的用户存入到session中，方便其他页面读取信息
            session.setAttribute("admin", admin);

            // 返回登录成功信息
            return Result.ok("登录成功");
        }
    }


    /**
     * 加载主要布局
     *
     * @return
     */
    @RequestMapping("/allmain")
    public String toAllmain() {
        return "allmain";   // 配置springmvc页面跳转的视图前缀和后缀
    }


    /**
     * 跳转主页面
     *
     * @return
     */
    @RequestMapping("/main")
    public String toMain() {
//        return "/WEB-INF/jsp/main.jsp";
        return "main";
    }


    /**
     * 获取侧边菜单栏
     *
     * @param session
     * @return
     */
    @RequestMapping("/getMenus")
    @ResponseBody
    public List<Menu> getMenus(HttpSession session) {

        // 从session获取 登陆时存入的用户信息    取出当前用户
        Admin admin = (Admin) session.getAttribute("admin");

        // 获取角色id
        Integer roleId = admin.getRoleId();

        // 根据roleId获取菜单
        return menuService.getMenuListByRoleId(roleId);
    }


    /**
     * 跳转个人信息页面
     *
     * @return
     */
    @RequestMapping("/personal")
    public String toPerson() {
        return "admin/personalInfo";
    }


    /**
     * 修改个人信息
     *
     * @param admin
     * @param session
     * @return
     */
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public Result updatePersonInfo(Admin admin, HttpSession session) {
        int count = adminService.updateAdmin(admin);
        if (count == 0) {
            // 更新失败
            return Result.error(100, "信息更新失败");
        } else {
            // 更新成功
            // 更新session中的当前用户的对象
            Admin admin1 = adminService.selectAdminById(admin.getId());
            session.setAttribute("admin", admin1);

            return Result.ok("信息更新成功");
        }
    }


    /**
     * 跳转修改密码页面
     *
     * @return
     */
    @RequestMapping("/changePassword")
    public String toChangePassword() {
        return "admin/changePassword";
    }


    /**
     * 修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword1
     * @param newPassword2
     * @return
     */
    @ResponseBody
    @RequestMapping("/changeAdminPassword")
    public Result changePassword(Integer id, String oldPassword, String newPassword1, String newPassword2) {

        // 判断两次新密码是否一致
        if (!newPassword1.equals(newPassword2)) {
            return Result.error(100, "两次密码不一致");
        }

        // 根据id查询用户
        Admin admin = adminService.selectAdminById(id);
        String pwd = admin.getPassword();

        // 对用户传递过来的旧密码进行加密，加密后在比较
        oldPassword = EncryptUtil.encrypt(oldPassword);
        if (!pwd.equals(oldPassword)) {
            return Result.error(101, "原密码不正确");
        } else {
            // 对新密码进行加密
            newPassword1 = EncryptUtil.encrypt(newPassword1);
            Admin admin1 = new Admin();
            admin1.setId(id);
            admin1.setPassword(newPassword1);

            // 更新admin
            adminService.updateAdmin(admin1);
            return Result.ok("密码修改成功");
        }
    }


    /**
     * 跳转管理员列表页面
     *
     * @return
     */
    @RequestMapping("/adminList")
    public String toAdminList() {
        return "admin/adminList";
    }


    /**
     * 分页查询admin
     *
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAdminList")
    public Result getAdminList(Integer page, Integer limit) {
        return adminService.getAdminList(page, limit);
    }


    /**
     * 跳转添加用户
     *
     * @return
     */
    @RequestMapping("/addAdmin")
    public String toAddAdmin(HttpServletRequest request) {

        // 需要先把下拉框所有的角色查询出来
        List<Role> roleList = roleService.getAllRole();

        request.setAttribute("roles", roleList);

        return "admin/addAdmin";
    }


    /**
     * 检查用户名是否存在
     *
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkAdminName/{username}")
    public Result checkAdminName(@PathVariable("username") String username) {
        return adminService.isAdminExist(username);
    }


    /**
     * 添加新的管理员
     *
     * @param admin
     * @return
     */
    @ResponseBody
    @RequestMapping("/insAdmin")
    public Result addAdmin(Admin admin) {
        // 获取用户提交的密码
        String pwd = admin.getPassword();
        // 加密
        pwd = EncryptUtil.encrypt(pwd);
        // 再设置回去
        admin.setPassword(pwd);

        // 给用户设置角色
        // 获取角色id
        Integer roleId = admin.getRoleId();
        // 根据roleId查询角色信息
        Role role = roleService.selectRoleById(roleId);

        // 获取roleName
        String roleName = role.getRoleName();
        // 设置角色名
        admin.setRoleName(roleName);

        return adminService.addAdmin(admin);
    }


    /**
     * 跳转编辑页面
     *
     * @return
     */
    @RequestMapping("/editAdmin/{id}")
    public String toEditAdmin(@PathVariable Integer id, HttpServletRequest request) {
        //System.out.println("要编辑的用户id："+id);

        // 根据id查询用户
        Admin modifyAdmin = adminService.selectAdminById(id);
        // 把该用户存入到request域中，用于前端获取
        request.setAttribute("modifyAdmin", modifyAdmin);

        // 查询所有的角色  用于获取分配角色的下拉框
        List<Role> roles = roleService.getAllRole();
        request.setAttribute("roles", roles);

        return "admin/editAdmin";
    }


    /**
     * 保存编辑过的信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateAdmin2")
    public Result updateAdmin2(Admin admin) {
        // 获取roleId
        Integer roleId = admin.getRoleId();
        // 根据roleId查询角色
        Role role = roleService.selectRoleById(roleId);
        // 获取roleName
        String roleName = role.getRoleName();
        // 给admin设置roleName
        admin.setRoleName(roleName);

        // 更新admin信息
        int count = adminService.updateAdmin(admin);
        if (0 == count) {
            return Result.error(100, "更新管理员信息失败");
        } else {
            return Result.ok("更新管理员信息成功");
        }

    }


    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delAdminById/{id}")
    public Result deleteAdminById(@PathVariable("id") Integer id) {
        System.out.println("要删除的管理员id是" + id);
        return adminService.deleteAdminById(id);
    }


    @RequestMapping("/logOut")
    public String logOut(HttpSession session) {

        // 销毁session    销毁登陆时存储的当前用户对象
        session.invalidate();
        return "redirect:index";
    }


}
