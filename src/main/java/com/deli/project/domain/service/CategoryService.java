package com.deli.project.domain.service;

import com.deli.project.domain.entity.Category;
import com.deli.project.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
