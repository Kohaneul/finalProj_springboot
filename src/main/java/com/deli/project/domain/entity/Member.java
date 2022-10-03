package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;
    private String nickName;


    @Enumerated(EnumType.STRING)
    private MemberSort memberSort;

    @Embedded
    private Address address;    //city, state, zipCode


    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setMemberSort(MemberSort memberSort) {
        this.memberSort = memberSort;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static Member createMember(String loginId, String password, String nickName, MemberSort memberSort, Address address){
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setNickName(nickName);
        member.setMemberSort(memberSort);
        member.setAddress(address);
        return member;
    }


}
