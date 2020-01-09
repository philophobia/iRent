package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.irent.entity.House;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {
    @Query(value = "select * from house where cast(h_id as char) like ?1", nativeQuery = true)
    List<House> findByIdLike(String param_id);
}
