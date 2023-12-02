package org.codewithrishit.orderservice.repository;

import org.codewithrishit.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
