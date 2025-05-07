package com.payvantage.ethicainternetbanking.repository;

import com.payvantage.ethicainternetbanking.data.model.BvnData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BvnDataRepository extends JpaRepository<BvnData, Long> {
    Optional<BvnData> findByBvn(String bvn);
}
