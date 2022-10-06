package com.deli.project.web.controller.form;

import com.deli.project.domain.entity.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j

public class FileStore {

    @Value("${file.dir}")
    private String fileDirectory;

    public String fullPath(String fileName){
        log.info("/////////////////////////////filePAth={}",fileDirectory);
        return fileDirectory+fileName;
    }

    @Transactional
    public UploadFile uploadFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }
        String originalFileName = multipartFile.getOriginalFilename();
        log.info("************** originalFileName ={}",originalFileName);
        String serverFileName = setServerName(originalFileName);
        log.info("************** server ={}",serverFileName);
        multipartFile.transferTo(new File(fullPath(serverFileName)));
        log.info("************** server ={}",fullPath(serverFileName));
        return  new UploadFile(originalFileName,serverFileName);
        }

    private String setServerName(String originalFileName){
        String uuid = UUID.randomUUID().toString();
        return uuid+"."+originalFileName.split("[.]")[1];
    }


}
