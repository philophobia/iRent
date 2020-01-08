package se.irent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import se.irent.entity.Administrator;
import se.irent.entity.Log;
import se.irent.service.AdministratorServiceImpl;
import se.irent.service.LogServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@Api(tags = "登录注销控制器", value = "后台系统管理员的登录注销控制器")
public class ManagementController {
    @Resource
    private AdministratorServiceImpl adminService = new AdministratorServiceImpl();
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    @GetMapping(value = "/v1/backstage/login")
    @ApiOperation(value = "登录", notes = "允许管理员登录进后台系统")
    public Administrator login(@ApiParam(name = "id", value = "管理员输入的账号", required = true) @RequestParam("id") String admin_id,
                               @ApiParam(name = "password", value = "管理员输入的密码", required = true) @RequestParam("password") String pwd,
                               HttpSession httpSession) throws ParseException {
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

    @GetMapping(value = "/v1/backstage/logout")
    @ApiOperation(value = "注销", notes = "允许管理员注销出后台系统")
    public String logout(HttpServletRequest httpRequest) throws ParseException {
        HttpSession httpSession = httpRequest.getSession();
        Log this_log = new Log();
        this_log.setAction("登出");
        String admin_id = (String) httpSession.getAttribute("operator");
        this_log.setOperator_id(admin_id);
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = formatter.parse(formatter.format(new Date()));
        this_log.setTime(time);

        if (this_log.getOperator_id() != null) {
            logService.addLog(this_log);
            httpSession.invalidate();
            return "succeeded";
        }
        else {
            return null;
        }
    }
}
