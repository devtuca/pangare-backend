package com.seguradora.paocomovo.repository;

import com.seguradora.paocomovo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findOrderById(Integer id);
    List<Order> findAll();
    void deleteById(Integer id);
}
