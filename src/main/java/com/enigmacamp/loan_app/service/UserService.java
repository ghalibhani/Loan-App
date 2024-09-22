package com.enigmacamp.loan_app.service;

import com.enigmacamp.loan_app.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
