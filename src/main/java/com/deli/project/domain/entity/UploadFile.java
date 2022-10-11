package com.deli.project.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class UploadFile {
    @Id
    @GeneratedValue
    @Column(name="uploadFile_id")
    private Long id;

    @OneToOne(mappedBy = "uploadFile",fetch = FetchType.LAZY)
    private Member member;

    private String originFileName;
    private String serverFileName;

    public UploadFile(String originFileName,String serverFileName){
        this.originFileName = originFileName;
        this.serverFileName = serverFileName;
    }

}
