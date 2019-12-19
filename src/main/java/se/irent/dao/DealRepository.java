package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.irent.entity.Deal;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, String> {
    @Query(value = "select * from deal where id like ?1 or landlord_id like ?1 or tenant_id like ?1 or house_id like ?1", nativeQuery = true)
    List<Deal> findByIdLike(String param_id);
}
