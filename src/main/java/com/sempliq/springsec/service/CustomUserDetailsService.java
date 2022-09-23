package com.sempliq.springsec.service;

import com.sempliq.springsec.entities.User;
import com.sempliq.springsec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        System.out.println(user.getRole().getClass());
        System.out.println(user.getRole());
        if(user==null){
            throw new UsernameNotFoundException("user not found");
        }
        System.out.println(new CustomUserDetails(user).getAuthorities());
        return new CustomUserDetails(user);

    }
}
