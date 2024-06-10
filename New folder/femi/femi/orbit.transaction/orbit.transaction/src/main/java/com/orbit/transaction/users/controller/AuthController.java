package com.orbit.transaction.users.controller;

import com.orbit.transaction.users.configure.JwtUtils;
import com.orbit.transaction.users.dto.request.LoginRequest;
import com.orbit.transaction.users.dto.request.SignupRequest;
import com.orbit.transaction.users.dto.response.JwtResponse;
import com.orbit.transaction.users.dto.response.MessageResponse;
import com.orbit.transaction.users.models.SecurityUser;
import com.orbit.transaction.users.repository.UserRepository;
import com.orbit.transaction.users.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
   private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final UserDetailsServiceImpl userService;

   private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;

    private final UserDetailsService userDetailsService;

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
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw  new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String userDetail = String.valueOf(userDetailsService.loadUserByUsername(loginRequest.getUsername()));
        String refreshToken = jwtUtils.generateRefreshToken(userDetail);

        final String token = jwtUtils.generateToken(userDetails);
        JwtResponse res = new JwtResponse();
        res.setMessage("Login successful");
        res.setAccessToken(token);
        res.setRefreshToken(refreshToken);
        return ResponseEntity.ok(res);
    }

}
