package com.deli.project.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "member")
    private List<PickUp> pickUpList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private MemberSort memberSort;

    private String phoneNumber;
    @Embedded
    private Address address;    //city, state, zipCode

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "uploadFile_id")
    private UploadFile uploadFile;

    public void setPickUpList(List<PickUp> pickUpList) {
        this.pickUpList = pickUpList;
        for (PickUp pickUp : pickUpList) {
            pickUp.setMember(this);
        }
    }


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

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public static Member createMember(String loginId, String password, String nickName, String phoneNumber, Address address,UploadFile uploadFile,MemberSort memberSort){
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
