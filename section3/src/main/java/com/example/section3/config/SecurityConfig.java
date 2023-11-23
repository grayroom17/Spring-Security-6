package com.example.section3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                                        "/notices/**",
                                        "/register/**"
                                ).permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

//    @Bean
//    InMemoryUserDetailsManager userDetailsManager() {
//        /**
//         * Approach 1 where we use withDefaultPasswordEncoder() method
//         * while creating the user details
//         */
////        UserDetails admin = User.withDefaultPasswordEncoder()
////                .username("admin")
////                .password("password")
////                .authorities("ADMIN")
////                .build();
////        UserDetails user = User.withDefaultPasswordEncoder()
////                .username("user")
////                .password("12345")
////                .authorities("READ")
////                .build();
////        return new InMemoryUserDetailsManager(admin, user);
//
//        /**
//         * Approach 2 where we use NoOpPasswordEncoder Bean
//         * while creating the user details
//         */
//        UserDetails admin = User.withUsername("admin")
//                .password("password")
//                .authorities("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password("12345")
//                .authorities("READ")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//
//
//    }

//    @Bean
//    UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
