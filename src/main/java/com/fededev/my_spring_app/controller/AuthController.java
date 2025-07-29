package com.fededev.my_spring_app.controller;

import com.fededev.my_spring_app.config.JwtUtil;
import com.fededev.my_spring_app.dto.request.CreateUserDTO;
import com.fededev.my_spring_app.dto.request.LoginRequest;
import com.fededev.my_spring_app.dto.response.JwtResponse;
import com.fededev.my_spring_app.model.User;
import com.fededev.my_spring_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        String jwt = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new JwtResponse(jwt, user.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody CreateUserDTO createUserDTO) {
        User user = userService.createUser(createUserDTO);
        String jwt = jwtUtil.generateToken(user);

        return ResponseEntity.ok(new JwtResponse(jwt, user.getUsername()));
    }
}