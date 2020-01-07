package se.irent.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import se.irent.entity.*;
import se.irent.service.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/management")
public class ManagementController {
    @Resource
    private DealServiceImpl dealService = new DealServiceImpl();
    @Resource
    private AdministratorServiceImpl adminService = new AdministratorServiceImpl();
    @Resource
    private UserServiceImpl userService = new UserServiceImpl();
    @Resource
    private HouseServiceImpl houseService = new HouseServiceImpl();
    @Resource
    private ReportServiceImpl reportService = new ReportServiceImpl();
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    //后台管理-登录和注销的api
    @RequestMapping(value = "/login")
    public Administrator login(@RequestParam("id") String admin_id, @RequestParam("password") String pwd, HttpSession httpSession) throws ParseException {
        if (admin_id == null || pwd == null)
            return null;
        Administrator cur_admin = adminService.findById(admin_id);
        Administrator re = new Administrator();
        if (pwd.equals(cur_admin.getPassword())) {
            re.setId(cur_admin.getPassword());
            re.setPassword("***");
            httpSession.setAttribute("operator", admin_id);
            //httpSession.setMaxInactiveInterval(900);

            Log this_log = new Log();
            this_log.setAction("登入");
            this_log.setOperator_id(admin_id);
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date time = formatter.parse(formatter.format(new Date()));
            this_log.setTime(time);
            logService.addLog(this_log);
            return re;
        }
        else
            return null;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest httpRequest) throws ParseException {
        HttpSession httpSession = httpRequest.getSession();
        Log this_log = new Log();
        this_log.setAction("登出");
        String admin_id = (String) httpSession.getAttribute("operator");
        this_log.setOperator_id(admin_id);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = formatter.parse(formatter.format(new Date()));
        this_log.setTime(time);
        logService.addLog(this_log);
        httpSession.invalidate();
        return "success";
    }

    //后台管理-交易部分的api
    @GetMapping("/deals")
    public List<Deal> getDeal(@RequestParam(value = "id", required = false) String did){
        if (did == null)
            return dealService.findAll();
        else
            return dealService.findByIdLike(did);
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
    public String deleteOneUser(@RequestParam("id") String user_id, HttpServletRequest httpRequest) throws ParseException {
        HttpSession httpSession = httpRequest.getSession();
        Log this_log = new Log();
        this_log.setAction("删除");
        String admin_id = (String) httpSession.getAttribute("operator");
        this_log.setOperator_id(admin_id);
        this_log.setTarget_kind("用户");
        this_log.setTarget_id(user_id);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = formatter.parse(formatter.format(new Date()));
        this_log.setTime(time);

        if (this_log.getOperator_id() != null) {
            userService.deleteById(user_id);
            logService.addLog(this_log);
            return "succeeded";
        }
        else
            return "failed";
    }

    @PutMapping("/users")
    public User putOneUser(@RequestParam("id") String uid, @RequestParam("operation") String op, HttpServletRequest httpRequest) throws ParseException {
        User cur_user = userService.findById(uid);
        if (cur_user == null)
            return null;
        String p = "";
        if (op.equals("freeze")) {
            op = "冻结";
            p = "封禁";
        }
        else if (op.equals("recover")) {
            op = "正常";
            p = "恢复";
        }

        HttpSession httpSession = httpRequest.getSession();
        Log this_log = new Log();
        this_log.setAction(p);
        String admin_id = (String) httpSession.getAttribute("operator");
        this_log.setOperator_id(admin_id);
        this_log.setTarget_kind("用户");
        this_log.setTarget_id(uid);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = formatter.parse(formatter.format(new Date()));
        this_log.setTime(time);

        if (this_log.getOperator_id() != null) {
            cur_user.setAccount_status(op);
            cur_user = userService.update(cur_user);
            logService.addLog(this_log);
            return cur_user;
        }
        else {
            return null;
        }
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
    public String deleteHouse(@RequestParam("id") String hid, HttpServletRequest httpRequest) throws ParseException {
        HttpSession httpSession = httpRequest.getSession();
        Log this_log = new Log();
        this_log.setAction("删除");
        String admin_id = (String) httpSession.getAttribute("operator");
        this_log.setOperator_id(admin_id);
        this_log.setTarget_kind("房源");
        this_log.setTarget_id(hid);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = formatter.parse(formatter.format(new Date()));
        this_log.setTime(time);

        if (this_log.getOperator_id() != null) {
            houseService.deleteById(hid);
            logService.addLog(this_log);
            return "succeeded";
        }
        else {
            return "failed";
        }
    }

    //后台管理-举报部分的api
    @GetMapping("/reports")
    public List<Report> getReport(@RequestParam(value = "id", required = false) String rid, @RequestParam(value = "status", required = false) String stat) {
        if (rid == null)
            if (stat == null)
                return reportService.findAll();
            else {
                switch (stat) {
                    case "pending":
                        stat = "待处理";
                        break;
                    case "processing":
                        stat = "正在处理";
                        break;
                    case "processed":
                        stat = "处理完成";
                        break;
                }
                return reportService.findByStatus(stat);
            }
        else
            return reportService.findByIdLike(rid);
    }

    @PutMapping("/reports")
    public Report updateReport(@RequestParam("id") String rid, @RequestParam("status") String stat, HttpServletRequest httpRequest) throws ParseException {
        Report cur_report = reportService.findById(rid);
        if (cur_report == null)
            return null;
        switch (stat) {
            case "processing":
                stat = "正在处理";
                break;
            case "processed":
                stat = "处理完成";
                break;
        }

        HttpSession httpSession = httpRequest.getSession();
        Log this_log = new Log();
        this_log.setAction("更改状态");
        String admin_id = (String) httpSession.getAttribute("operator");
        this_log.setOperator_id(admin_id);
        this_log.setTarget_kind("举报信");
        this_log.setTarget_id(rid);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = formatter.parse(formatter.format(new Date()));
        this_log.setTime(time);

        if (this_log.getOperator_id() != null) {
            cur_report.setStatus(stat);
            cur_report = reportService.update(cur_report);
            logService.addLog(this_log);
            return cur_report;
        }
        else {
            return null;
        }
    }

    //后台管理-日志部分的api
    @GetMapping("/logs")
    public List<Log> getLog(@RequestParam(value = "id", required = false) String lid,
                            @RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                            @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
        if (lid == null) {
            if (startTime == null && endTime == null)
                return logService.findAll();
            else
                return logService.findByTimeRange(startTime, endTime);
        }
        else
            return logService.findByIdLike(lid);
    }
}
