package com.rodolpho.projetothais.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rodolpho.projetothais.entity.User;
import com.rodolpho.projetothais.exception.ResourceNotFoundException;
import com.rodolpho.projetothais.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;
    
    
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    

    @Override
    public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {
        
        User user = userRepository.findByUsernameOrEmail(usernameorEmail, usernameorEmail).orElseThrow(()-> new ResourceNotFoundException("usernameorEmail", "usernameorEmail", null));

        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
    
}
