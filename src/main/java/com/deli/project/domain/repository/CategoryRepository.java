package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 카테고리 정보 저장소
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
