package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.irent.entity.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {
}
