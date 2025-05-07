package com.my.pharmacy.repository;

import com.my.pharmacy.infrastructure.entity.DrugEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugRepository extends JpaRepository<DrugEntity, Long> {
}