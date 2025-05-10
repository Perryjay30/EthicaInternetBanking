/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.payvantage.ethicainternetbanking.repository;

import com.payvantage.ethicainternetbanking.data.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 *
 * @author mac
 */
public interface UserRepository extends JpaRepository<UserTable, Long> {
    
    Optional<UserTable> findByPhoneNumber(String phoneNumber);
    Optional<UserTable> findByIdAndBvnVerifiedTrue(Long userId);

    Optional<UserTable> findByIdAndNinVerifiedTrue(Long userId);
    
    Optional<UserTable> findByPhoneNumberAndActiveTrue(String phoneNumber);

    Optional<UserTable> findByEmailAndActiveTrue(String phoneNumber);

    Optional<UserTable> findByEmail(String phoneNumber);

    Optional<UserTable> findByUserUUID(String userUUID);

    //Optional<UserTable> findByPhoneNumberAndPhoneNumberVerifiedTrueAndDeviceId(String phoneNumber, String deviceId);
    
    @Query("select u from UserTable u where (u.phoneNumber =:phoneNumber) and u.active=true and u.phoneNumberVerified=true and u.deviceId=:deviceId")
    Optional<UserTable> findByPhoneNumberAndActiveTrueAndPhoneNumberVerifiedTrueAndDeviceId(String phoneNumber, String deviceId);


    @Query("select u from UserTable u where u.email=:email and u.active=true and u.emailVerified=true and u.deviceId=:deviceId")
    Optional<UserTable> findByEmailAndActiveTrueAndEmailVerifiedTrueAndDeviceId(String email, String deviceId);
    @Query("select u from UserTable u where u.phoneNumber =:phoneNumber and u.phoneNumberVerified=true ")
    Optional<UserTable> findByPhoneNumberAndPhoneNumberVerifiedTrue(String phoneNumber);

    @Query("select u from UserTable u where u.email =:email and u.emailVerified=true ")
    Optional<UserTable> findByEmailAndEmailVerifiedTrue(String email);

    Optional<UserTable> findByEmailAndActiveTrueAndPhoneNumberVerifiedTrueAndDeviceId(String email, String deviceId);

    Optional<UserTable> findByPhoneNumberOrEmail(String phoneNumber,String email);

    Optional<UserTable> findByBvn(String bvn);

    @Query("select u from UserTable u where u.email =:email and u.emailVerified=false ")
    Optional<UserTable> findByEmailAndEmailVerifiedFalse(String email);

    Optional <UserTable> findByNin(String nin);
    
    boolean existsByBvn(String bvn);
    boolean existsByNin(String nin);
    boolean existsByDeviceId(String deviceId);
    //boolean existsByContactPhonenumberOrContactEmail(String contactPhonenumber,String contactEmail);


}
