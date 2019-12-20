package se.irent.service;

import org.springframework.stereotype.Service;
import se.irent.dao.ReportRepository;
import se.irent.entity.Report;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    @Resource
    private ReportRepository reportRepository;

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> findByIdLike(String param_id) {
        return reportRepository.findByIdLike("%" + param_id + "%");
    }

    @Override
    public Report findById(String rid) {
        Optional<Report> cur = reportRepository.findById(rid);
        return cur.orElse(null);
    }

    @Override
    public Report update(Report report) {
        return reportRepository.saveAndFlush(report);
    }

    @Override
    public List<Report> findByStatus(String status) {
        return reportRepository.findByStatus(status);
    }
}
