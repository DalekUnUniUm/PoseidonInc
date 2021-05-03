package com.nnk.springboot.service;


import com.nnk.springboot.domain.MyUserPrincipal;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder ;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        User savedUser = userRepository.save(user);
        return savedUser ;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUser(Integer id){
        return userRepository.findById(id);
    }


    public void deleteUser(User user){

        userRepository.delete(user);
    }



}
