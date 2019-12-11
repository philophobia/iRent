package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.irent.entity.House;

@Repository
public interface HouseRepository extends JpaRepository<House, String> {
}
