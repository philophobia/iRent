package se.irent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import se.irent.entity.Log;
import se.irent.entity.User;
import se.irent.service.LogServiceImpl;
import se.irent.service.UserServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "用户类控制器", value = "后台系统用户类控制器")
public class UserController {
    @Resource
    private UserServiceImpl userService = new UserServiceImpl();
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    @GetMapping("/v1/backstage/users")
    @ApiOperation(value = "获取用户信息", notes = "获取全部用户信息的列表；若有请求url有参数id则进行模糊搜索")
    public List<User> getUser(@ApiParam(name = "id", value = "用户的部分或完整id", required = false) @RequestParam(value = "id", required = false) String uid) {
        if (uid == null)
            return userService.findAll();
        else {
            int id = Integer.parseInt(uid);
            return userService.findByIdLike(id);
        }
    }

    @DeleteMapping("/v1/backstage/users")
    @ApiOperation(value = "删除用户", notes = "删除单个用户信息")
    public String deleteOneUser(@ApiParam(name = "id", value = "用户的完整id", required = true) @RequestParam("id") String user_id, HttpServletRequest httpRequest) throws ParseException {
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
            int id = Integer.parseInt(user_id);
            userService.deleteById(id);
            logService.addLog(this_log);
            return "succeeded";
        }
        else {
            return null;
        }
    }

    /*@PutMapping("/v1/backstage/users")
    @ApiOperation(value = "更改用户状态", notes = "更改单个用户的状态信息")
    public User putOneUser(@ApiParam(name = "id", value = "用户的完整id", required = true) @RequestParam("id") String uid,
                           @ApiParam(name = "operation", value = "状态的变更，可选“freeze”或“recover”", required = true) @RequestParam("operation") String op,
                           HttpServletRequest httpRequest) throws ParseException {
        int id = Integer.parseInt(uid);
        User cur_user = userService.findById(id);
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
    }*/
}
