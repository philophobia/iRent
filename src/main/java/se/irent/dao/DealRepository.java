package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import se.irent.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, String> {
}
