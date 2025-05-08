package com.payvantage.ethicainternetbanking.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Security Interceptor authenticate and authorize users using any of the
 * following authmodel otp,session,db and ldap. it check user access
 * to a role. Database users are staff and thirdparty, session is maintained as
 * OTP (with time out as configured in AppSetting).
 *
 * @author GOL
 */
@Component
public class SecurityInterceptor implements WebMvcConfigurer {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityInterceptor.class.getName());
    @Autowired
    SecurityHandler securityHandler;

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/v3/api-docs/**",
//                                "/swagger-ui/**",
//                                "/swagger-ui.html"
//                        ).permitAll() // Allow public access
//                        .anyRequest().authenticated()
//                )
//                .csrf().disable();
//
//        return http.build();
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityHandler);

    }

}
