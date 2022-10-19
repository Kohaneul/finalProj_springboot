package com.deli.project.domain.service;
import com.deli.project.domain.entity.Category;
import com.deli.project.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * 게시글 조회를 하기 위한 DTO
 * 작성자 ID,게시글 제목 : 특정 문자열을 포함하는가
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository repository;

    @Transactional
    public Long saveCategory(Category Category){
        repository.save(Category);
        return Category.getId();
    }

    public Category findOne(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Category> findAll(){
        return repository.findAll();
    }



}
