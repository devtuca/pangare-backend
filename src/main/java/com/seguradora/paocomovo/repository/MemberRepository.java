package com.seguradora.paocomovo.repository;

import com.seguradora.paocomovo.model.Member;
import com.seguradora.paocomovo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

   Member findMemberById(int id);
}
