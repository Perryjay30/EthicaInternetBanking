package com.payvantage.ethicainternetbanking.security;


import com.payvantage.ethicainternetbanking.data.model.UserTable;
import com.payvantage.ethicainternetbanking.exception.EthicaInternetBankingException;
import com.payvantage.ethicainternetbanking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FetchUserDetailsFromDbService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Optional<UserTable> existingUser = userRepository.findByEmail(emailAddress);
        return existingUser.map(FetchUserDetailsFromDb::new).orElseThrow(() -> new EthicaInternetBankingException("User not found"));
    }
}
