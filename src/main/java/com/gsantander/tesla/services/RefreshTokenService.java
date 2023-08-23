package com.gsantander.tesla.services;

import com.gsantander.tesla.classes.AuthResponse;
import com.gsantander.tesla.config.ApplicationConfig;
import com.gsantander.tesla.enums.RefreshTokenExpirationMode;
import com.gsantander.tesla.enums.TokenType;
import com.gsantander.tesla.exceptions.TslTokenExpiredException;
import com.gsantander.tesla.jwt.JwtService;
import com.gsantander.tesla.model.TslRefreshToken;
import com.gsantander.tesla.model.TslUser;
import com.gsantander.tesla.repositories.IRefreshTokenRepository;
import com.gsantander.tesla.repositories.IUserRepository;
import com.gsantander.tesla.tools.TslConstants;
import com.gsantander.tesla.tools.TslFunctions;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.transaction.Transactional;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private RefreshTokenExpirationMode expirationMode = RefreshTokenExpirationMode.WITHOUT_EXPIRATION;

    private final IRefreshTokenRepository refreshTokenRepository;
    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final ApplicationConfig applicationConfig;

    public RefreshTokenService(IRefreshTokenRepository refreshTokenRepository, IUserRepository userRepository, JwtService jwtService, ApplicationConfig applicationConfig) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.applicationConfig = applicationConfig;
    }

    @Transactional
    public TslRefreshToken createToken(String userName) {
        TslUser tslUser = null;
        if(!userName.equals(TslConstants.SYSTEM_ADMIN_USER_NAME))
            tslUser = this.userRepository.findByCredentialsUserName(userName).get();
        DateTime dtExpirationDate = new DateTime().plusMinutes(TokenType.REFRESH_TOKEN.getExpirationInMinutes());
        TslRefreshToken tslRefreshToken = new TslRefreshToken();
        tslRefreshToken.setUser(tslUser);
        tslRefreshToken.setRefreshToken(UUID.randomUUID().toString());
        tslRefreshToken.setExpirationDate(dtExpirationDate.toDate());
        tslRefreshToken.setSystemAdmin(userName.equals(TslConstants.SYSTEM_ADMIN_USER_NAME));
        return this.refreshTokenRepository.save(tslRefreshToken);
    }

    @Transactional
    @Modifying
    public TslRefreshToken verifyExpiration(TslRefreshToken tslRefreshToken) {
        if(tslRefreshToken.getExpirationDate().before(new Date())) {
            this.refreshTokenRepository.deleteById(tslRefreshToken.getIdToken());
            throw new TslTokenExpiredException();
        }
        return tslRefreshToken;
    }

    @Transactional
    @Modifying
    public TslRefreshToken verifyAndRefreshExpiration(TslRefreshToken tslRefreshToken) {
        if(tslRefreshToken.getExpirationDate().before(new Date())) {
            tslRefreshToken.setExpirationDate(new DateTime().plusMinutes(TokenType.REFRESH_TOKEN.getExpirationInMinutes()).toDate());
            this.refreshTokenRepository.save(tslRefreshToken);
        }
        return tslRefreshToken;
    }

    @Transactional
    public AuthResponse refreshToken(String refreshToken) {
        Optional<TslRefreshToken> optTslRefreshToken = this.refreshTokenRepository.findByRefreshToken(refreshToken);
        if(optTslRefreshToken.isPresent()) {
            TslRefreshToken tslRefreshToken = optTslRefreshToken.get();
            UserDetails userDetails = this.applicationConfig.systemAdminUser();
            if(!tslRefreshToken.isSystemAdmin())
                userDetails = tslRefreshToken.getUser();
            switch (this.expirationMode) {
                case WITH_EXPIRATION -> {
                    tslRefreshToken = this.verifyExpiration(tslRefreshToken);
                    AuthResponse authResponse = new AuthResponse();
                    authResponse.setAccessToken(this.jwtService.getToken(userDetails,TokenType.REFRESH_TOKEN));
                    authResponse.setRefreshToken(refreshToken);
                    authResponse.setExpirationDate(tslRefreshToken.getExpirationDate());
                    return authResponse;
                }
                case WITHOUT_EXPIRATION -> {
                    tslRefreshToken = this.verifyAndRefreshExpiration(tslRefreshToken);
                    AuthResponse authResponse = new AuthResponse();
                    authResponse.setAccessToken(this.jwtService.getToken(userDetails,TokenType.REFRESH_TOKEN));
                    authResponse.setRefreshToken(refreshToken);
                    authResponse.setExpirationDate(tslRefreshToken.getExpirationDate());
                    return authResponse;
                }
            }
        } else {
            throw new RuntimeException(TslFunctions.getMessage("tokenNotFound"));
        }
        return null;
    }

}
