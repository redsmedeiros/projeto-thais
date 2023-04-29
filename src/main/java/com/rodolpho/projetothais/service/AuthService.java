package com.rodolpho.projetothais.service;

import com.rodolpho.projetothais.payload.LoginDto;

public interface AuthService {
    
    String login(LoginDto loginDto);
}
