package com.orbit.transaction.users.controller;

import com.orbit.transaction.users.configure.JwtUtils;
import com.orbit.transaction.users.dto.request.LoginRequest;
import com.orbit.transaction.users.dto.request.SignupRequest;
import com.orbit.transaction.users.dto.response.JwtResponse;
import com.orbit.transaction.users.dto.response.MessageResponse;
//import com.orbit.transaction.users.models.RefreshToken;
import com.orbit.transaction.users.models.RefreshToken;
import com.orbit.transaction.users.models.SecurityUser;
import com.orbit.transaction.users.repository.UserRepository;
//import com.orbit.transaction.users.services.RefreshTokenService;
import com.orbit.transaction.users.services.AuthService;
import com.orbit.transaction.users.services.RefreshTokenService;
import com.orbit.transaction.users.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;


   private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;



    @PostMapping("/addUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        SecurityUser securityUserInfo = modelMapper.map(signUpRequest, SecurityUser.class);
        BeanUtils.copyProperties(signUpRequest, securityUserInfo);
        securityUserInfo.setPassword(passwordEncoder.encode(securityUserInfo.getPassword()));
        userRepository.save(securityUserInfo);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

}
