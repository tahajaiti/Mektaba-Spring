package com.kyojin.flos.service;

import com.kyojin.flos.dto.auth.AuthResponse;
import com.kyojin.flos.dto.auth.LoginRequest;
import com.kyojin.flos.dto.auth.RegisterRequest;
import com.kyojin.flos.entity.Role;
import com.kyojin.flos.entity.User;
import com.kyojin.flos.exceptions.NotFoundException;
import com.kyojin.flos.repository.RoleRepository;
import com.kyojin.flos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request){
        Role role = roleRepository.findByName("user")
                .orElseThrow(NotFoundException::new);

        User user = new User(
                null,
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                role
        );
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getName(), user.getEmail(), user.getRole().getName());
    }

    public AuthResponse login (LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(NotFoundException::new);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getName(), user.getEmail(), user.getRole().getName());
    }

}
