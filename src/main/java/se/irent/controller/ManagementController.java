package se.irent.controller;

import org.springframework.web.bind.annotation.*;
import se.irent.entity.*;
import se.irent.service.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/management")
public class ManagementController {
    private String admin;
    @Resource
    private DealServiceImpl dealService;
    @Resource
    private AdministratorServiceImpl adminService;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private HouseServiceImpl houseService;
    @Resource
    private ReportServiceImpl reportService;
    @Resource
    private LogServiceImpl logService;

    //后台管理-登录和注销的api
    @RequestMapping(value = "/login")
    public Administrator login(@RequestParam("id") String admin_id, @RequestParam("password") String pwd) {
        if (admin_id == null || pwd == null)
            return null;
        Administrator cur_admin = adminService.findById(admin_id);
        if (pwd.equals(cur_admin.getPassword())) {
            cur_admin.setPassword("***");
            admin = admin_id;

            Log this_log = new Log();
            this_log.setAction("登入");
            this_log.setOperator_id(admin_id);
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this_log.setTime(formatter.format(new Date()));
            logService.addLog(this_log);
            return cur_admin;
        }
        else
            return null;
    }

    @RequestMapping(value = "logout")
    public String logout(){
        Log this_log = new Log();
        this_log.setAction("登入");
        this_log.setOperator_id(admin);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this_log.setTime(formatter.format(new Date()));
        logService.addLog(this_log);
        return "success";
    }

    //后台管理-交易部分的api
    @GetMapping("/deals")
    public List<Deal> getOrder(@RequestParam(value = "id", required = false) String did){
        if (did == null)
            return dealService.findAll();
        else
            return dealService.findByIdLike(did);
    }

    @DeleteMapping("/deals")
    public void deleteOneOrder(@RequestParam("id") String order_id){
        dealService.deleteById(order_id);
    }

    //后台管理-用户部分的api
    @GetMapping("/users")
    public List<User> getUser(@RequestParam(value = "id", required = false) String uid) {
        if (uid == null)
            return userService.findAll();
        else
            return userService.findByIdLike(uid);
    }

    @DeleteMapping("/users")
    public String deleteOneUser(@RequestParam("id") String user_id){
        userService.deleteById(user_id);
        return "succeeded";
    }

    //后台管理-房源部分的api
    @GetMapping("/houses")
    public List<House> getHouse(@RequestParam(value = "id", required = false) String hid) {
        if (hid == null)
            return houseService.findAll();
        else
            return houseService.findByIdLike(hid);
    }

    @DeleteMapping("/houses")
    public String deleteHouse(@RequestParam("id") String hid) {
        houseService.deleteById(hid);
        return "success";
    }

    //后台管理-举报部分的api
    @GetMapping("/reports")
    public List<Report> getReport(@RequestParam(value = "id", required = false) String rid) {
        if (rid == null)
            return reportService.findAll();
        else
            return reportService.findByIdLike(rid);
    }

    //后台管理-日志部分的api
    @GetMapping("/logs")
    public List<Log> getLog(@RequestParam(value = "id", required = false) String lid) {
        if (lid == null)
            return logService.findAll();
        else
            return logService.findByIdLike(lid);
    }
}
