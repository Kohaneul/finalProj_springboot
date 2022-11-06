package com.deli.project.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/**
 * 업로드할 이미지파일 테이블
 * */
@Getter
@NoArgsConstructor
@Entity
public class ImageFile {
    @Id
    @GeneratedValue
    @Column(name="uploadFile_id")
    private Long id;

    private String originFileName;  //실제 파일이름
    private String serverFileName;  //서버에 저장되는 파일이름 => 중복을 우려하여 uuid로 지정

    public ImageFile(String originFileName, String serverFileName){
        this.originFileName = originFileName;
        this.serverFileName = serverFileName;
    }

}
