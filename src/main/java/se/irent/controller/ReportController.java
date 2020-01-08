package se.irent.controller;

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
public class ReportController {
    @Resource
    private ReportServiceImpl reportService = new ReportServiceImpl();
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    @GetMapping("/v1/backstage/reports")
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

    @PutMapping("/v1/backstage/reports")
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

}
