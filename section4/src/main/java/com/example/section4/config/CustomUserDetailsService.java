package com.example.section4.config;

import com.example.section4.model.Customer;
import com.example.section4.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String password;
        List<GrantedAuthority> authorities = new ArrayList<>();

        List<Customer> customers = repository.findAllByEmail(username);

        if (customers.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for the user:" + username);
        }

        Customer customer = customers.get(0);
        password = customer.getPassword();
        authorities.add(new SimpleGrantedAuthority(customer.getRole().toString()));

        return new User(username, password, authorities);
    }

}
