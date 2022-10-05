package com.deli.project.domain.service;

import com.deli.project.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class DuplicateService {
    private final MemberRepository repository;

    public int duplicatedLoginId(String loginId){
        log.info("==============={}",loginId);
        return repository.findLoginId(loginId).size();
    }


    public int duplicatedNickName(String nickName){
        log.info("==============={}",nickName);
        return repository.findNickName(nickName).size();
    }
}
