package com.example.section7.config;

import com.example.section7.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf"); //default value

        http
                .securityContext(configurer -> configurer.requireExplicitSave(false))
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .cors(configurer -> configurer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:4200"));
                    config.setAllowedMethods(List.of("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(List.of("*"));
                    config.setMaxAge(3600L);
                    return config;
                }))
                .csrf(configurer -> configurer
                        .csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers(
                                "/contacts/**",
                                "/register/**"
                        )
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests ->
                        requests
//                              .requestMatchers("/accounts/**").hasAuthority("viewaccount")
//                              .requestMatchers("/balance/**").hasAnyAuthority("viewaccount", "viewbalance")
//                              .requestMatchers("/loans/**").hasAuthority("viewloans")
//                              .requestMatchers("/cards/**").hasAuthority("viewcards")
                                .requestMatchers("/accounts/**").hasRole("USER")
                                .requestMatchers("/balance/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/loans/**").hasRole("USER")
                                .requestMatchers("/cards/**").hasRole("USER")
                                .requestMatchers("/user").authenticated()
                                .requestMatchers(
                                        "/contacts/**",
                                        "/notices/**",
                                        "/register/**"
                                ).permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
