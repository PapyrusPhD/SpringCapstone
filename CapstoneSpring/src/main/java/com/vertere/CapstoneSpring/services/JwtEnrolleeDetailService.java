package com.vertere.CapstoneSpring.services;

import com.vertere.CapstoneSpring.models.Enrollees;
import com.vertere.CapstoneSpring.repositories.EnrolleesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtEnrolleeDetailService implements UserDetailsService {
    @Autowired
    private EnrolleesRepository enrolleesRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Enrollees enrollees = enrolleesRepository.findByUsername(username);

        if(enrollees == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(enrollees.getUsername(), enrollees.getPassword(), new ArrayList<>());
    }
}
