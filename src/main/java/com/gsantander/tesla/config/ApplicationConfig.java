package com.gsantander.tesla.config;

import com.gsantander.tesla.model.TslCompany;
import com.gsantander.tesla.repositories.ICompanyRepository;
import jakarta.annotation.PostConstruct;
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

import java.util.List;

@Configuration
public class ApplicationConfig {

    private List<TslCompany> tslCompanies;
    private final IUserRepository userRepository;
    private final ICompanyRepository companyRepository;

    public ApplicationConfig(IUserRepository userRepository, ICompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @PostConstruct
    public void onPostConstruct() {
        this.tslCompanies = this.companyRepository.findAll();
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

    public TslCompany getTslCompany(Integer idCompany) {
        return this.tslCompanies.stream()
                            .filter(tslCompany -> tslCompany.getIdCompany()==idCompany)
                            .findFirst()
                            .orElse(null);
    }

}
