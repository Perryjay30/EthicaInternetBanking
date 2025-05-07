package com.payvantage.ethicainternetbanking.repository;

import com.payvantage.ethicainternetbanking.data.model.NinData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NinDataRepository extends JpaRepository<NinData, Long> {
    Optional<NinData> findByNin(String nin);
}
