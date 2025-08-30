package com.exercicio.java.java.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.exercicio.java.java.dto.LoginDTO;
import com.exercicio.java.java.dto.ResponseLoginDTO;
import com.exercicio.java.java.securityConfig.JwtUtil;

@Service
public class LoginService {
  
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseLoginDTO authService(LoginDTO loginDTO) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                loginDTO.getPassword());                
               
        authenticationManager.authenticate(authentication);
               
        String token = jwtUtil.generateToken(loginDTO.getEmail());

        ResponseLoginDTO responseLoginDTO = modelMapper.map(loginDTO, ResponseLoginDTO.class);
        responseLoginDTO.setToken(token);
    
        return responseLoginDTO;       
    }
}
