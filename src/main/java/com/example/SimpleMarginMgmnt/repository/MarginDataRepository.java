package com.example.SimpleMarginMgmnt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SimpleMarginMgmnt.entity.MarginDataEntity;

@Repository
public interface MarginDataRepository extends JpaRepository< MarginDataEntity, Integer> {
List<MarginDataEntity> findByMarginOrder(Integer fileInstanceId);

@Query(value = "SELECT * FROM margin_data where (instruction IN ('*',?1)) and (base_ccy IN ('*',?3)) and"
		+ " (term_ccy IN ('*', ?4)) and (trader_tier IN ( '*',?2)) and "
		+ "(?5 BETWEEN from_amt and to_amt) or "
		+ "((from_amt = to_amt) and (to_amt = 0)) ORDER BY margin_order", nativeQuery = true)
List<MarginDataEntity> findByAmountRange(String instruction,String ttier,String bCy,String tCy,Integer amount);
}

