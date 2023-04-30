package com.rodolpho.projetothais.service;

import com.rodolpho.projetothais.payload.LoginDto;
import com.rodolpho.projetothais.payload.RegisterDto;

public interface AuthService {
    
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
