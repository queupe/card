package br.marinha.casnav.kanban.member;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


//@Repository
public interface MemberRepository extends JpaRepository<Member, UUID>{

}
