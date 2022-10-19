package com.deli.project.web.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 로그인 FORM
 *
 * */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginMember {
    @NotEmpty(message="아이디를 입력해주세요")
    private String loginId;
    @NotEmpty(message="비밀번호를 입력해주세요")
    private String password;
}
