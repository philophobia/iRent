package se.irent.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.irent.entity.Log;
import se.irent.service.LogServiceImpl;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class LogController {
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    @GetMapping("/v1/backstage/logs")
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
