package com.gsantander.tesla.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gsantander.tesla.classes.AuthResponse;
import com.gsantander.tesla.classes.LoginRequest;
import com.gsantander.tesla.classes.RegisterRequest;
import com.gsantander.tesla.enums.TokenType;
import com.gsantander.tesla.jwt.JwtService;
import com.gsantander.tesla.model.TslRefreshToken;
import com.gsantander.tesla.model.TslUser;
import com.gsantander.tesla.repositories.IUserRepository;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetails systemAdminUser;

    public AuthService(JwtService jwtService, RefreshTokenService refreshTokenService, IUserRepository userRepository, AuthenticationManager authenticationManager, UserDetails systemAdminUser) {
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.systemAdminUser = systemAdminUser;
    }

    public AuthResponse login(LoginRequest request) {
        UserDetails user = null;
        if(request.getUserName().equals(this.systemAdminUser.getUsername()))
            if(request.getPassword().equals(this.systemAdminUser.getPassword()))
                user = this.systemAdminUser;
        if(user==null) {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(),request.getPassword()));
            user = this.userRepository.findByCredentialsUserName(request.getUserName()).orElseThrow();
        }
        String accessToken = this.jwtService.getToken(user,TokenType.ACCESS_TOKEN);
        TslRefreshToken tslRefreshToken = this.refreshTokenService.createToken(request.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken(accessToken);
        authResponse.setRefreshToken(tslRefreshToken.getRefreshToken());
        authResponse.setExpirationDate(this.jwtService.getExpiration(accessToken));
        return authResponse;
    }

}
