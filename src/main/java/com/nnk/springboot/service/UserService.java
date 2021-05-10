package com.nnk.springboot.service;


import com.nnk.springboot.domain.MyUserPrincipal;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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

    public String saveUserOrUpdate(Integer id, Model model, User user,BindingResult result){

        /**Testing fields from password**/
        if(!isValidPassword(user.getPassword())){
            Logger.warn("Fields doesn't respect password field 8 character, one upperCase, onelowercaser, one number and one special character");
            model.addAttribute("logError", "logError");
            return "user/add";
        }
        /**If id equal null, so it's a new User**/
        if(id == null){
            if (!result.hasErrors()) {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(user.getPassword()));
                saveUser(user);
                model.addAttribute("users", getUsers());
                Logger.debug("User saved with success");
                return "redirect:/user/list";
            }
        }
        /**If id not null, so it's a Updated User**/
        else {
            if (result.hasErrors()) {
                Logger.warn("User update failed");
                return "user/update";
            }
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            user.setId(id);
            saveUser(user);
            model.addAttribute("users", getUsers());
            Logger.debug("User updated with success");
            return "redirect:/user/list";

        }
        Logger.warn("User save failed");
        return "user/add";
    }

    /**Testing fields of password (8 character, one upperCase, onelowercaser, one number and one special character)**/
    public static boolean isValidPassword(String password){
        boolean isValid = true ;

        if(password.length() < 8){
            Logger.info("Password must have at least 8 character");
            isValid = false ;
        }
        String upperCaseChars = "(.*[A-Z].*)" ;
        if(!password.matches(upperCaseChars)){
            Logger.info("Password must have at least one uppercase character");
            isValid = false ;
        }
        String lowerCaseChars = "(.*[a-z].*)" ;
        if(!password.matches(lowerCaseChars)){
            Logger.info("Password must have at least one lowercase character");
            isValid = false ;
        }
        String numbers = "(.*[0-9].*)" ;
        if(!password.matches(numbers)){
            Logger.info("Password must have at least one number");
            isValid = false ;
        }
        String specialChars = "(.*[@,#,$,%].*)" ;
        if(!password.matches(specialChars)){
            Logger.info("Password must have at least one special character among @,#,$,%");
            isValid = false ;
        }
        return isValid ;
    }
}
