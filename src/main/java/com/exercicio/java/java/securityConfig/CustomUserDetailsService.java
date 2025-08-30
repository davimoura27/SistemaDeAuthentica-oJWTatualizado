package com.exercicio.java.java.securityConfig;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.exercicio.java.java.entity.Users;
import com.exercicio.java.java.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;    
  
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional <Users> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }

        Users userExistent = user.get();
        
       return org.springframework.security.core.userdetails.User
                .withUsername(userExistent.getEmail())
                .password(userExistent.getPassword())
                .authorities(Collections.emptyList())
                .build();          
    }
}
