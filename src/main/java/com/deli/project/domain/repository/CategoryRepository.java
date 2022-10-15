package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Category;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
/**
 * 카테고리 정보 저장소
 */
@Repository
public class CategoryRepository {
    private final EntityManager em;
    private final JPAQueryFactory query;


    public CategoryRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public void save(Category category){
        em.persist(category);
    }

    public Category findOne(Long id){
        return em.find(Category.class, id);
    }

    public List<Category> findAll(){
        return em.createQuery("select c from Category c",Category.class).getResultList();
    }

}
