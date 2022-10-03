package com.deli.project.domain.service;

import com.deli.project.domain.entity.Member;
import com.deli.project.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;

    public Long saveMember(Member member){
        repository.save(member);
        return member.getId();
    }

    public Member findOne(Long id){
        return repository.findOne(id);
    }

    public List<Member> findAll(){
        return repository.findAll();
    }



}
