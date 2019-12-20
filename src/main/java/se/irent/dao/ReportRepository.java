package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.irent.entity.Log;
import se.irent.entity.Report;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, String> {
    @Query(value = "select * from log where id like ?1 or complainant_id like ?1 or being_reported_id like ?1", nativeQuery = true)
    List<Report> findByIdLike(String param_id);
    List<Report> findByStatus(String status);
}
