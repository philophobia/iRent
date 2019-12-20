package se.irent.service;

import se.irent.entity.Log;

import java.util.Date;
import java.util.List;

interface LogService {
    void addLog(Log log);
    List<Log> findAll();
    List<Log> findByIdLike(String param_id);
    List<Log> findByTimeRange(Date startTime, Date endTime);
}
