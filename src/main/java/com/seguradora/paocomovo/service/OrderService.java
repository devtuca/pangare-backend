package com.seguradora.paocomovo.service;

import com.seguradora.paocomovo.model.Order;
import com.seguradora.paocomovo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        for (Order order : orderRepository.findAll()) {
            System.out.println("ID: " + order.getId());
        }
        return orderRepository.findAll();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    public Order getByID(int id) {
        return orderRepository.findOrderById(id);
    }
}
