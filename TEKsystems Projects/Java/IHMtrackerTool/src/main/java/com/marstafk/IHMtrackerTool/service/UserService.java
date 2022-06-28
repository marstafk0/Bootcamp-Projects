package com.marstafk.IHMtrackerTool.service;

import com.marstafk.IHMtrackerTool.models.User;
import com.marstafk.IHMtrackerTool.security.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User save(UserRegistrationDto registration);
}


