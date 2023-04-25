package com.example.SimpleMarginMgmnt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SimpleMarginMgmnt.entity.StageMarginDataEntity;

@Repository
public interface StageMarginDataRepository extends JpaRepository< StageMarginDataEntity, StageMarginDataEntity.StageMarginDataCompositeKey> {

	List<StageMarginDataEntity> findByFileInstanceId(int fileInstanceId);
//List<StageMarginDataEntity> findByMarginId(Integer fileInstanceId);
}
