package com.deli.project.domain.repository;

import com.deli.project.domain.entity.MemberSort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberSearch {
    private String loginId;
    private MemberSort memberSort;
}
