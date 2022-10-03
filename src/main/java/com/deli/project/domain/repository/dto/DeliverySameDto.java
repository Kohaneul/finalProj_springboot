package com.deli.project.domain.repository.dto;

import com.deli.project.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DeliverySameDto {

    private Long id;
    private Long OrderId;
}
