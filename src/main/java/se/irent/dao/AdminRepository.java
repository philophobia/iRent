package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.irent.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
}
