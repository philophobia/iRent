package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.irent.entity.Report;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    @Query(value = "select * from report where r_id like ?1 or h_id like ?1 or u_id like ?1", nativeQuery = true)
    List<Report> findByIdLike(String param_id);
    List<Report> findByStatus(String status);
}
