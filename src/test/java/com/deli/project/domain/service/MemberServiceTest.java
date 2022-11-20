package com.deli.project.domain.service;


import com.deli.project.ProjectApplication;
import com.deli.project.TestDataInit;
import com.deli.project.domain.entity.Member;
import com.deli.project.domain.repository.MemberRepository;
import com.deli.project.web.controller.form.ImageStore;
import com.deli.project.web.controller.form.MemberUpdateForm;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
@SpringBootTest
class MemberServiceTest {
    @Autowired MemberService memberService;

    @Test
    @DisplayName("저장 테스트")
    public void saveMemberTest(){

        //Member 객체 저장 Logic
        Member member1 = saveAndGetMemberId();

        Long memberId = member1.getId();
        //멤버 아이디 조회
        Member member = memberService.findOne(memberId);

        //id값 같은지 검증
        Assertions.assertThat(memberId).isSameAs(member.getId());

    }

    private Member saveAndGetMemberId(){
        //1. Member 객체 생성
        Member member1 = new Member();
        member1.setLoginId("memberA");
        member1.setPassword("1111");

        //2. 서비스함수에 객체 저장
       memberService.saveMember(member1);
       return member1;
    }


    @Test
    @DisplayName("로그인 테스트")
    public void loginMemberTest(){

        //Member 객체 저장 Logic
        Member member1 = saveAndGetMemberId();

        //로그인 시도 _ 성공시
        Assertions.assertThat(memberService.loginMember("memberA", "1111")).isNotNull();

        //로그인 시도 _ 실패시
        Assertions.assertThat(memberService.loginMember("memberA", "11111")).isNull();

    }
    @Test
    @DisplayName("아이디 찾기")
    public void findMemberId(){
        //Member 객체 저장 Logic
        Member member1 = saveAndGetMemberId();

        //아이디 찾기 > 성공
        Assertions.assertThat(memberService.findLoginId("memberA")).isNotNull();
        //아이디 찾기 > 실패
        Assertions.assertThat(memberService.findLoginId("memberAB")).isNull();

    }

    @Test
    @DisplayName("수정하기")
    public void updateMember(){

        //Member 객체 저장 Logic
        Member member1 = saveAndGetMemberId();

        //비밀번호 수정
        memberService.revisePassword(member1.getId(),"55555");

        //비밀번호 변경 확인
        Assertions.assertThat( memberService.findOne(member1.getId()).getPassword()).isEqualTo("55555");

    }

}