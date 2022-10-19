package com.deli.project.domain.service;

import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.repository.MemberRepository;
import com.deli.project.domain.repository.MemberSearchDTO;
import com.deli.project.web.controller.form.MemberUpdateForm;
import com.deli.project.web.controller.form.ImageStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
/**
 * 회원 가입시 중복체크(아이디, 닉네임 중복체크), 가입, 아이디찾기 를 실행하기 위한 서비스 함수.
 *
 * */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository repository;
    private final ImageStore fileStore;

    @Transactional
    public Long saveMember(Member member){
        repository.save(member);
        return member.getId();
    }
    public Member findOne(Long id){
        return repository.findOne(id);
    }
    public Member findLoginId(String loginId){
        return repository.findId(loginId);
    }
    public List<Member> findAll(MemberSearchDTO memberSearch){
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
    @Transactional
    public void updateMember(Long id, MemberUpdateForm memberUpdateForm) throws IOException {
        Member  member = findOne(id);
        member.setMemberSort(memberUpdateForm.getMemberSort());
        member.setPassword(memberUpdateForm.getPassword());
        member.setPhoneNumber(memberUpdateForm.getPhoneNumber());
        member.setAddress(new Address(memberUpdateForm.getCity(), memberUpdateForm.getState(), memberUpdateForm.getZipCode()));
        member.setUploadFile(fileStore.uploadFile(memberUpdateForm.getUploadFile()));
        member.setNickName(memberUpdateForm.getNickName());
    }

    public int duplicatedLoginId(String loginId){
        return repository.findLoginId(loginId).size();
    }


    public int duplicatedNickName(String nickName){
        return repository.findNickName(nickName).size();
    }

}
