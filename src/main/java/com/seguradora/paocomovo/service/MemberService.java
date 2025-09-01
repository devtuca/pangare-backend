package com.seguradora.paocomovo.service;

import com.seguradora.paocomovo.model.Member;
import com.seguradora.paocomovo.model.Order;
import com.seguradora.paocomovo.repository.MemberRepository;
import com.seguradora.paocomovo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {


    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Member get(int id) {
        return memberRepository.findMemberById(id);
    }

    public int getClientes(int id) {
        if(memberRepository.findMemberById(id) == null) {
            return 0;
        }

        return memberRepository.findMemberById(id).getTotalClientes();
    }

    public void delete(int id) {
        memberRepository.deleteById(id);
    }

    public int getTotalVendido(int id) {
        if(memberRepository.findMemberById(id) == null) {
            return 0;
        }
        return memberRepository.findMemberById(id).getTotalVendido();
    }

    public List<Member> getAll() {
        for (Member order : memberRepository.findAll()) {
            System.out.println("ID: " + order.getId());
        }
        return memberRepository.findAll();
    }


}
