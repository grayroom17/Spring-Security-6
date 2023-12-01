package com.example.section9.config;

import com.example.section9.model.Authority;
import com.example.section9.model.Customer;
import com.example.section9.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    CustomerRepository repository;
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<Customer> customers = repository.findAllByEmailIgnoreCase(username);
        if (!customers.isEmpty()) {
            Customer customer = customers.get(0);
            if (passwordEncoder.matches(password, customer.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(customer.getAuthorities()));
            }
            throw new BadCredentialsException("Invalid password!");
        }
        throw new BadCredentialsException("No user register with this details!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> (GrantedAuthority) new SimpleGrantedAuthority(authority.getName()))
                .toList();
    }

}
