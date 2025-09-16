package com.exercicio.java.java.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exercicio.java.java.dto.ResponseUserDTO;
import com.exercicio.java.java.dto.UserDTO;
import com.exercicio.java.java.entity.Users;
import com.exercicio.java.java.exception.EmailExistenteException;
import com.exercicio.java.java.repository.UserRepository;

@Service
public class UserRegisterService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ResponseUserDTO createUser(UserDTO userDTO) {
        Users user = modelMapper.map(userDTO, Users.class);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailExistenteException();
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return modelMapper.map(user, ResponseUserDTO.class);
    }
}
