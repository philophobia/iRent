package se.irent.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "操作记录类控制器", value = "后台系统操作记录类控制器")
public class LogController {
    @Resource
    private LogServiceImpl logService = new LogServiceImpl();

    @GetMapping("/v1/backstage/logs")
    @ApiOperation(value = "获取操作记录信息", notes = "获取全部操作记录信息的列表；若有请求url有参数id则进行模糊搜索，与记录id、管理员id、对象id匹配，若有参数start和end则按时间段筛选记录")
    public List<Log> getLog(@ApiParam(name = "id", value = "操作记录的部分或完整id", required = false) @RequestParam(value = "id", required = false) String lid,
                            @ApiParam(name = "start", value = "开始时间", required = false) @RequestParam(value = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startTime,
                            @ApiParam(name = "end", value = "结束时间", required = false) @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endTime) {
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
