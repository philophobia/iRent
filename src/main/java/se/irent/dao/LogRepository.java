package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.irent.entity.Log;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {
    @Query(value = "select * from log where id like ?1 or operator_id like ?1 or target_id like ?1", nativeQuery = true)
    List<Log> findByIdLike(String param_id);
}
