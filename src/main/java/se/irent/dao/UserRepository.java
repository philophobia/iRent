package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import se.irent.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
