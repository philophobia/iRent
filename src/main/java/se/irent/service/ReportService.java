package se.irent.service;

import se.irent.entity.Report;

import java.util.List;

public interface ReportService {
    List<Report> findAll();
    List<Report> findByIdLike(String param_id);
    Report findById(String rid);
    Report update(Report report);
    List<Report> findByStatus(String status);
}
