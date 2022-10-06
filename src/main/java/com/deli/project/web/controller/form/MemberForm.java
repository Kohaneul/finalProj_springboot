package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.MemberSort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {

    @NotEmpty
    @Length(min = 5,max = 15)
    private String loginId;

    @NotEmpty
    @Length(min = 3,max = 10)
    private String nickName;

    @NotEmpty
    @Pattern(regexp = "[0-9a-zA-Z]{6,12}",message = "비밀번호는 6자리 이상이며 한개 이상의 대소문자가 포함되어야 합니다.")
    private String password;

    @NotEmpty
    @Pattern(regexp = "[0-9a-zA-Z]{6,12}",message = "비밀번호는 6자리 이상이며 한개 이상의 대소문자가 포함되어야 합니다.")
    private String passwordCheck;

    private MultipartFile attachFile;


    @NotNull
    private MemberSort memberSort;

    @NotEmpty
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",message = " - 을 포함해서 넣어주세요")
    private String phoneNumber;

    @NotEmpty
    private String city,state,zipCode;



}
