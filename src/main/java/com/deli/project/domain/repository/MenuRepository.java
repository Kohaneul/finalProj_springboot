package com.deli.project.domain.repository;

import com.deli.project.domain.entity.Menu;
import com.deli.project.domain.entity.Restaurant;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 메뉴 저장소
 * */
@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuRepository {

    private final EntityManager em;

    @Transactional
    public void save(Menu menu){
        em.persist(menu);
    }

    public Menu findOne(Long id){
       return em.find(Menu.class,id);
    }

    public List<Menu> findMenuList(Long restaurantId){
        return em.createQuery("select m from Menu m where m.restaurant.id = :restaurantId",Menu.class).setParameter("restaurantId",restaurantId).getResultList();
    }


    public List<Menu> findCheckedMenuList(Boolean bool){
        return em.createQuery("select m from Menu m where m.checkBox = :bool",Menu.class).setParameter("bool",false).getResultList();
    }


    public List<Menu> chooseMenus(List<Long> menuId){
       return menuId.stream().map(i->findOne(i)).collect(Collectors.toList());
    }


}
