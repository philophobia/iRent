package se.irent.controller;

import org.springframework.web.bind.annotation.*;
import se.irent.entity.House;
import se.irent.entity.Log;
import se.irent.service.HouseServiceImpl;
import se.irent.service.LogServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class HouseController {
    @Resource
    private HouseServiceImpl houseService = new HouseServiceImpl();
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    @GetMapping("/v1/backstage/houses")
    public List<House> getHouse(@RequestParam(value = "id", required = false) String hid) {
        if (hid == null)
            return houseService.findAll();
        else
            return houseService.findByIdLike(hid);
    }

    @DeleteMapping("/v1/backstage/houses")
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
            return null;
        }
    }
}
