package com.deli.project.web.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * 픽업 장소 입력 FORM
 *
 * */

@Getter
@Setter
@AllArgsConstructor
public class PickUpForm {
    private double lat;
    private double lon;
}
