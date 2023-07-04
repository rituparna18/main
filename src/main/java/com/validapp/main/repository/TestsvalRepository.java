package com.validapp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.validapp.main.entity.Tests_valEntity;

public interface TestsvalRepository extends JpaRepository<Tests_valEntity, Integer> {
    List<Tests_valEntity> findByTestidAndGenderAndUnit(Integer testid, String gender, String unit);
}
