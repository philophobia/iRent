package se.irent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import se.irent.entity.Log;
import se.irent.entity.Report;
import se.irent.service.LogServiceImpl;
import se.irent.service.ReportServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@Api(tags = "举报类控制器", value = "后台系统举报类控制器")
public class ReportController {
    @Resource
    private ReportServiceImpl reportService = new ReportServiceImpl();
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    @GetMapping("/v1/backstage/reports")
    @ApiOperation(value = "获取举报信息", notes = "获取全部举报信息的列表；若有请求url有参数id则进行模糊搜索，与租客id、房源id匹配，若有参数status则通过状态筛选举报")
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
        else {
            int id = Integer.parseInt(rid);
            return reportService.findByIdLike(id);
        }
    }

    @PutMapping("/v1/backstage/reports")
    @ApiOperation(value = "更改举报状态", notes = "更改单个举报的状态信息")
    public Report updateReport(@ApiParam(name = "id", value = "举报的完整id", required = true) @RequestParam("id") String rid,
                               @ApiParam(name = "status", value = "变更的状态，可选“processing”或“processed”", required = true) @RequestParam("status") String stat,
                               HttpServletRequest httpRequest) throws ParseException {
        int id = Integer.parseInt(rid);
        Report cur_report = reportService.findById(id);
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

}
