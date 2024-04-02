package com.mytravel.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Li Gengrun
 * @date 2024/3/29 18:12
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * 改为添加component注解
     */
//    @Bean
//    public UserDetailsService userDetailsService(){
//        DBUserDetailsManager dbUserDetailsManager=new DBUserDetailsManager();
//        return dbUserDetailsManager;
//    }

    /**
     * 加密算法
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 用于自定义认证逻辑或实现更复杂的安全需求
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }


}
