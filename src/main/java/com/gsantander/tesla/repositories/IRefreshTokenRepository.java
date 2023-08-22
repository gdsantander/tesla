package com.gsantander.tesla.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsantander.tesla.model.TslRefreshToken;

public interface IRefreshTokenRepository extends JpaRepository <TslRefreshToken, Integer> {

    Optional<TslRefreshToken> findByRefreshToken(String refreshToken);

}
