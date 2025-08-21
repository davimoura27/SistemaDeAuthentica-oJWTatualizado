package com.exercicio.java.java.service;


import java.util.Optional;

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
   

    public ResponseUserDTO createUser(UserDTO userDTO){
       Users newUser = modelMapper.map(userDTO, Users.class);
       Optional<Users> emailExistent = userRepository.findByEmail(newUser.getEmail());
       
       if (emailExistent.isPresent()) {
        throw new EmailExistenteException("Email ja existente!");
        
       }
      
       String encodedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
       newUser.setPassword(encodedPassword);
       userRepository.save(newUser);
       ResponseUserDTO dtoUser = modelMapper.map(newUser, ResponseUserDTO.class);

       return dtoUser;
    }

}
