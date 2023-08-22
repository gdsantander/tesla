package com.gsantander.tesla.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gsantander.tesla.repositories.IUserRepository;
import com.gsantander.tesla.tools.TslConstants;

@Configuration
public class ApplicationConfig {

    private final IUserRepository userRepository;

    public ApplicationConfig(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider AuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.userDetailService());
        authenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailService() {
        UserDetailsService uds = (username) -> {
            if(username.equals(TslConstants.SYSTEM_ADMIN_USER_NAME))
                return systemAdminUser();
            return this.userRepository.findByCredentialsUserName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User NOT FOUND"));
        };
        return uds;
    }

    @Bean
    public UserDetails systemAdminUser() {
        return User.withUsername(TslConstants.SYSTEM_ADMIN_USER_NAME)
                .password(TslConstants.SYSTEM_ADMIN_PASSWORD)
                .authorities(TslConstants.AUTHORITY_READ,TslConstants.AUTHORITY_WRITE,TslConstants.AUTHORITY_SYSTEM_ADMIN)
                .build();
    }

}
