package com.marstafk.IHMtrackerTool.serviceImpl;

import com.marstafk.IHMtrackerTool.models.User;
import com.marstafk.IHMtrackerTool.repositories.UserRepository;
import com.marstafk.IHMtrackerTool.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new MyUserDetails(user);
    }

}

