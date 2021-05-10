package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.service.UserService;
import org.pmw.tinylog.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService ;

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        Logger.info("Receive user list");
        model.addAttribute("users", userService.getUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user) {
        Logger.info("Load user add page");
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {

        /**Testing fields from password**/
        if(!userService.isValidPassword(user.getPassword())){
            Logger.warn("Fields doesn't respect password field 8 character, one upperCase, onelowercaser, one number and one special character");
            model.addAttribute("logError", "logError");
            return "user/add";
        }
        if (!result.hasErrors()){
            userService.saveUserOrUpdate(null,user);
            model.addAttribute("users", userService.getUsers());
            Logger.debug("User saved with success");
            return "redirect:/user/list";
        }
        Logger.warn("User save failed");
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Logger.info("Load user update page");
        User user = userService.getUser(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        /**Testing fields from password**/
        if(!userService.isValidPassword(user.getPassword())){
            Logger.warn("Fields doesn't respect password field 8 character, one upperCase, onelowercaser, one number and one special character");
            model.addAttribute("logError", "logError");
            return "user/add";
        }
        if (result.hasErrors()) {
            Logger.warn("User update failed");
            return "user/update";
        }
        userService.saveUserOrUpdate(id,user);
        model.addAttribute("users", userService.getUsers());
        Logger.debug("User updated with success");
        return "redirect:/user/list";

    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUser(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.deleteUser(user);
        model.addAttribute("users", userService.getUsers());
        Logger.debug("User deleted with success");
        return "redirect:/user/list";
    }
}
