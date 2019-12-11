package se.irent.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import se.irent.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
}
