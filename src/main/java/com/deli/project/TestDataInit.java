package com.deli.project;

import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.entity.MemberSort;
import com.deli.project.domain.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static com.deli.project.domain.entity.MemberSort.ADMIN;
import static com.deli.project.domain.entity.MemberSort.BASIC;

@Component
public class TestDataInit {

    @Autowired  private MemberService memberService;

    @EventListener(ApplicationReadyEvent.class)
    public void dataInit(){
        Member memberA = Member.createMember("memberA","1111","memberA","010-1111-2222", BASIC,new Address("서울특별시","영등포구","123-456"),null);
        Member memberB = Member.createMember("memberB","2222","memberB","010-2222-2222", BASIC,new Address("서울특별시","강남구","789-456"),null);
        Member memberC = Member.createMember("memberC","3333","memberC","010-3333-3333", ADMIN,new Address("서울특별시","송파구","1111-5544"),null);
        memberService.saveMember(memberA);
        memberService.saveMember(memberB);
        memberService.saveMember(memberC);
    }
}
