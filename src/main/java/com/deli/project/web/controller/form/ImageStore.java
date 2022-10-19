package com.deli.project.web.controller.form;
import com.deli.project.domain.entity.ImageFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
/**
 * 이미지 업로드시 이미지 파일명, 경로 -> 객체로 변환
 *
 * */

@Component
@Slf4j
public class ImageStore {

    @Value("${file.dir}")
    private String fileDirectory;

    public String fullPath(String fileName){
        log.info("filePath={}",fileDirectory);
        return fileDirectory+fileName;
    }

    @Transactional
    public ImageFile uploadFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }
        String originalFileName = multipartFile.getOriginalFilename();
        log.info("originalFileName ={}",originalFileName);
        String serverFileName = setServerName(originalFileName);
        log.info("serverFileName ={}",serverFileName);
        multipartFile.transferTo(new File(fullPath(serverFileName)));
        log.info("server ={}",fullPath(serverFileName));
        return new ImageFile(originalFileName,serverFileName);
        }

    private String setServerName(String originalFileName){
        String uuid = UUID.randomUUID().toString();
        return uuid+"."+originalFileName.split("[.]")[1];
    }


}
