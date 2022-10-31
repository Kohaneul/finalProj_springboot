package com.deli.project.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 * 회원 정보 테이블
 * */
@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String loginId;
    private String password;
    private String nickName;

    @Enumerated(EnumType.STRING)
    private MemberSort memberSort;

    private String phoneNumber;
    @Embedded
    private Address address;    //city, state, zipCode

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadFile_id")
    private ImageFile uploadFile;


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

    public void setUploadFile(ImageFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public static Member createMember(String loginId, String password, String nickName, String phoneNumber, Address address, ImageFile uploadFile, MemberSort memberSort){
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setUploadFile(uploadFile);
        member.setNickName(nickName);
        member.setPhoneNumber(phoneNumber);
        member.setMemberSort(memberSort);
        member.setAddress(address);
        return member;
    }


}
