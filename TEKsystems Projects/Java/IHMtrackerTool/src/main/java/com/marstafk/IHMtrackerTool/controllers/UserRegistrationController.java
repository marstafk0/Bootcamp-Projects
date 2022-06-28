package com.marstafk.IHMtrackerTool.controllers;

import com.marstafk.IHMtrackerTool.models.User;
import com.marstafk.IHMtrackerTool.security.UserRegistrationDto;
import com.marstafk.IHMtrackerTool.service.UserService;
import com.marstafk.IHMtrackerTool.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup_form")
public class UserRegistrationController {

//    @Autowired
//    private UserService userService;

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "signup_form";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {

        User existing;
        try {
            existing = userService.findByEmail(userDto.getEmail());
            if (existing != null) {
                result.rejectValue("email", null, "There is already an account registered with that email");
            }
        } catch (NullPointerException e) {
        }

        if (result.hasErrors()) {
            return "signup_form";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        userService.save(userDto);
        return "redirect:/signup_form?success";
    }
}

