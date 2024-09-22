package com.enigmacamp.loan_app.service.impl;

import com.enigmacamp.loan_app.constant.ERole;
import com.enigmacamp.loan_app.dto.request.AuthRequest;
import com.enigmacamp.loan_app.dto.request.CustomerRequest;
import com.enigmacamp.loan_app.dto.response.LoginResponse;
import com.enigmacamp.loan_app.dto.response.RegisterResponse;
import com.enigmacamp.loan_app.entity.*;
import com.enigmacamp.loan_app.repository.UserRepository;
import com.enigmacamp.loan_app.security.JwtUtil;
import com.enigmacamp.loan_app.service.AdminService;
import com.enigmacamp.loan_app.service.AuthService;
import com.enigmacamp.loan_app.service.CustomerService;
import com.enigmacamp.loan_app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AdminService adminService;
    @Override
    public RegisterResponse registerCustomer(AuthRequest request) {
        try {
            Role role = roleService.getOrSave(Role.builder()
                    .role(ERole.ROLE_CUSTOMER)
                    .build());

            User user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(List.of(role))
                    .build();
            userRepository.saveAndFlush(user);

            Customer customer = Customer.builder()
                    .user(user)
                    .build();
            customerService.createCustomer(customer);

            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .role(getRoleNames(user))
                    .build();
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exist");
        }
    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        try {
            Role roleAdmin = roleService.getOrSave(Role.builder()
                    .role(ERole.ROLE_ADMIN)
                    .build());

            Role roleStaff = roleService.getOrSave(Role.builder()
                    .role(ERole.ROLE_STAFF)
                    .build());

            User user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .roles(List.of(roleAdmin, roleStaff))
                    .build();
            userRepository.saveAndFlush(user);

            Admin admin = Admin.builder()
                    .user(user)
                    .build();
            adminService.createAdmin(admin);

            return RegisterResponse.builder()
                    .email(user.getEmail())
                    .role(getRoleNames(user))
                    .build();
        } catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exist");
        }
    }

    @Override
    public RegisterResponse registerStaff(AuthRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .email(appUser.getUsername())
                .role(getRoleNames(appUser))
                .token(token)
                .build();
    }

    public List<String> getRoleNames(User user){
        List<String> roleNames = new ArrayList<>();
        for (Role role: user.getRoles() ) {
            String name = switch (role.getRole()) {
                case ROLE_ADMIN -> "admin";
                case ROLE_STAFF -> "staff";
                case ROLE_CUSTOMER -> "customer";
                default -> "Unknown";
            };
            roleNames.add(name);
        } return roleNames;
    }

    public List<String> getRoleNames(AppUser user){
        List<String> roleNames = new ArrayList<>();
        for (Role role: user.getRoles() ) {
            String name = switch (role.getRole()) {
                case ROLE_ADMIN -> "admin";
                case ROLE_STAFF -> "staff";
                case ROLE_CUSTOMER -> "customer";
                default -> "Unknown";
            };
            roleNames.add(name);
        } return roleNames;
    }
}
