package com.seguradora.paocomovo.controller;

import com.seguradora.paocomovo.model.Order;
import com.seguradora.paocomovo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public List<Order> all() {
        return service.getAll();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        System.out.println(order);
        return service.save(order);
    }


    @GetMapping("/{id}")
    public Order getOne(@PathVariable Integer id) {
        return service.getByID(id);
    }


    @PutMapping("/{id}")
    public Order update(@PathVariable Integer id, @RequestBody Order order) {
        order.setId(id);
        return service.save(order);
    }

    @GetMapping("/perfil")
    public String getPerfil(@PathVariable String name) {
        return "index";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}

