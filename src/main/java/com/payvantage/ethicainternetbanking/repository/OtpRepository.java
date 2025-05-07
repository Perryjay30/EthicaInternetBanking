/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.payvantage.ethicainternetbanking.repository;

import com.payvantage.ethicainternetbanking.data.model.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 *
 * @author mac
 */
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {







    Optional<OtpEntity> findByUsername(String username);
}
