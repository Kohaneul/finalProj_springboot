package com.deli.project.domain.repository;

import com.deli.project.domain.entity.PickUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 픽업 장소 저장소
 * */
@Repository
public interface PickUpRepository extends JpaRepository<PickUp,Long> {

}
