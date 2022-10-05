package com.deli.project.domain.service;

import com.deli.project.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DuplicateService {
    private final MemberRepository repository;

    public int duplicatedLoginId(String loginId){
        return repository.findLoginId(loginId).get().size();
    }


    public int duplicatedNickName(String nickName){
        return repository.findNickName(nickName).get().size();
    }
}
