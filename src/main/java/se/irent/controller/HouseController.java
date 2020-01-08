package se.irent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "房源类控制器", value = "后台系统房源类控制器")
public class HouseController {
    @Resource
    private HouseServiceImpl houseService = new HouseServiceImpl();
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    @GetMapping("/v1/backstage/houses")
    @ApiOperation(value = "获取房源信息", notes = "获取全部房源信息的列表；若有请求url有参数id则进行模糊搜索，与房源id、房东id匹配")
    public List<House> getHouse(@ApiParam(name = "id", value = "房源的部分或完整id", required = false) @RequestParam(value = "id", required = false) String hid) {
        if (hid == null)
            return houseService.findAll();
        else
            return houseService.findByIdLike(hid);
    }

    @DeleteMapping("/v1/backstage/houses")
    @ApiOperation(value = "删除房源", notes = "删除单个房源信息")
    public String deleteHouse(@ApiParam(name = "id", value = "房源的完整id", required = true) @RequestParam("id") String hid,
                              HttpServletRequest httpRequest) throws ParseException {
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
