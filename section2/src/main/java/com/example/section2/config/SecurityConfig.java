package com.example.section2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(requests ->
                        requests.requestMatchers(
                                        "/accounts/**",
                                        "/balance/**",
                                        "/loans/**",
                                        "/cards/**").authenticated()
                                .requestMatchers(
                                        "/contacts/**",
                                        "/notices/**"
                                ).permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }

}
