package com.deli.project.web.controller;

import com.deli.project.domain.entity.Address;
import com.deli.project.domain.entity.MemberSort;
import com.deli.project.domain.entity.PickUp;
import com.deli.project.domain.entity.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateForm {
    @NotNull
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordCheck;
    @NotEmpty
    private String nickName;

    @NotNull
    private MemberSort memberSort;

    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    private String city;
    @NotEmpty
    private String state;
    @NotEmpty
    private String zipCode;

    @NotNull
    private MultipartFile attachFile;

}
