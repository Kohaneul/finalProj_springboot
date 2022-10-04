package com.deli.project.domain.service;

import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.MemberSort;
import com.deli.project.domain.repository.MemberRepository;
import com.deli.project.domain.repository.MemberSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository repository;

    @Transactional
    public Long saveMember(Member member){
        repository.save(member);
        return member.getId();
    }
    public Member findOne(Long id){
        return repository.findOne(id);
    }
    public List<Member> findAll(MemberSearch memberSearch){
        return repository.findAll(memberSearch);
    }
//
//    public List<Member> findAll(){
//        return repository.findAll();
//    }

    public List<Member> findLoginId(String loginId){
        List<Member> members = repository.findLoginId(loginId).orElse(null);
        log.info("members size={}",members.size());
        return members;
    }

    public List<Member> findNickName(String nickName){
        return repository.findLoginId(nickName).orElse(null);
    }



}
