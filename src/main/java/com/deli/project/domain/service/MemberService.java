package com.deli.project.domain.service;

import com.deli.project.domain.entity.Member;
import com.deli.project.domain.repository.MemberRepository;
import com.deli.project.domain.repository.MemberSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    public Member loginMember(String loginId,String password){
        List<Member> members = repository.findLoginId(loginId);
        if(members!=null){
            Member member = members.stream().filter(m -> m.getPassword().equals(password)).findFirst().orElse(null);
            return member;
        }
        return null;
    }

    public int duplicatedLoginId(String loginId){
        return repository.findLoginId(loginId).size();
    }


    public int duplicatedNickName(String nickName){
        return repository.findNickName(nickName).size();
    }

}
