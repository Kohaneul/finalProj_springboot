package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.MemberSort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * 내 정보 수정 FORM
 * */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateForm {
    @NotNull
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty(message="비밀번호를 입력해주세요")
    private String password;
    @NotEmpty(message="비밀번호(확인)를 입력해주세요")
    private String passwordCheck;
    @NotEmpty
    private String nickName;

    @NotNull
    private MultipartFile uploadFile;

    @NotNull
    private MemberSort memberSort;

    @NotEmpty(message="휴대폰번호를 입력해주세요")
    private String phoneNumber;

    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String zipCode;

}
