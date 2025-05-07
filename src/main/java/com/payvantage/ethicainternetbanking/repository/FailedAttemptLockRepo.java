/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.payvantage.ethicainternetbanking.repository;


import com.payvantage.ethicainternetbanking.data.model.FailedAttemptLock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 *
 * @author gol
 */
public interface FailedAttemptLockRepo extends CrudRepository<FailedAttemptLock, Long> {
 
    Optional<FailedAttemptLock> findByUsernameAndNextActiveTimeGreaterThan(String username,Long nextActiveTime);
    
    Optional<FailedAttemptLock> findByUsername(String username);
}
