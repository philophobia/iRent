package se.irent.controller;

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
public class UserController {
    @Resource
    private UserServiceImpl userService = new UserServiceImpl();
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    @GetMapping("/v1/backstage/users")
    public List<User> getUser(@RequestParam(value = "id", required = false) String uid) {
        if (uid == null)
            return userService.findAll();
        else
            return userService.findByIdLike(uid);
    }

    @DeleteMapping("/v1/backstage/users")
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
        else {
            return null;
        }
    }

    @PutMapping("/v1/backstage/users")
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
}
