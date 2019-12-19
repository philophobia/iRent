package se.irent.service;

import org.springframework.stereotype.Service;
import se.irent.dao.LogRepository;
import se.irent.entity.Log;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Resource
    private LogRepository logRepository;

    @Override
    public void addLog(Log log) {
        logRepository.saveAndFlush(log);
    }

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public List<Log> findByIdLike(String param_id) {
        return logRepository.findByIdLike("%" + param_id + "%");
    }
}
