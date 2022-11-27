package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Board;
import com.deli.project.domain.entity.OrderCheck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardRepositoryTest {

    @Autowired private BoardRepository boardRepository;

    @Test
    @DisplayName("카운트")
    void addTest1(){

    }


}