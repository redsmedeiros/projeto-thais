package com.rodolpho.projetothais.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rodolpho.projetothais.entity.Role;
import com.rodolpho.projetothais.entity.User;
import com.rodolpho.projetothais.exception.NurseAPIException;
import com.rodolpho.projetothais.exception.ResourceNotFoundException;
import com.rodolpho.projetothais.payload.LoginDto;
import com.rodolpho.projetothais.payload.RegisterDto;
import com.rodolpho.projetothais.repository.RoleRepository;
import com.rodolpho.projetothais.repository.UserRepository;
import com.rodolpho.projetothais.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder ){

        this.authenticationManager = authenticationManager;

        this.userRepository = userRepository;

        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return "User logged-in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new NurseAPIException(HttpStatus.BAD_REQUEST, "Já cadastrado");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new NurseAPIException(HttpStatus.BAD_REQUEST, "Já cadastrado");
        }

        User user = new User();

        user.setName(registerDto.getName());
        user.setEmail(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName("ROLE_USER").get();

        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);
       
        return "Usuário registrado";
    }
    
}
