package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.MemberSort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {

    @NotEmpty
    @Length(min = 5,max = 15)
    private String loginId;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{6,12}$",message = "비밀번호는 6자리 이상이며 한개 이상의 대소문자가 포함되어야 합니다.")
    private String password;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{6,12}$",message = "비밀번호는 6자리 이상이며 한개 이상의 대소문자가 포함되어야 합니다.")
    private String passwordCheck;

    @NotEmpty
    @Length(min = 3,max = 10)
    private String nickName;

    @NotNull
    private MemberSort memberSort;

    @NotEmpty
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = " - 을 포함해서 넣어주세요")
    private String phoneNumber;

    @NotEmpty
    private String city,state,zipCode;

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
