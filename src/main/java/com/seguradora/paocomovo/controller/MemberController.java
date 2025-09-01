package com.seguradora.paocomovo.controller;

import com.seguradora.paocomovo.model.Member;
import com.seguradora.paocomovo.model.Order;
import com.seguradora.paocomovo.repository.OrderRepository;
import com.seguradora.paocomovo.service.MemberService;
import com.seguradora.paocomovo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping
    public List<Member> all() {
        return memberService.getAll();
    }

    @GetMapping("/{id}")
    public Member getMemberDetail(@PathVariable int id) {
        return memberService.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable int id) {
        memberService.delete(id);
    }
}
