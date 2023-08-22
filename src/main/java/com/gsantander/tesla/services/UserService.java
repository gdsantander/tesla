package com.gsantander.tesla.services;

import com.gsantander.tesla.classes.PasswordChangeRequest;
import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.exceptions.TslPasswordChangeException;
import com.gsantander.tesla.model.TslProfile;
import com.gsantander.tesla.model.TslUser;
import com.gsantander.tesla.repositories.IProfileRepository;
import com.gsantander.tesla.repositories.IUserRepository;
import com.gsantander.tesla.tools.TslFunctions;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final IProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUserRepository userRepository, IProfileRepository profileRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void insertUser(TslUser tslUser) {
        if(this.userRepository.existsByIdCompanyAndLastNameAndFirstNameAndEmail(tslUser.getIdCompany(),tslUser.getLastName(),tslUser.getFirstName(),tslUser.getEmail()))
            throw new TslFoundException();
        Set<TslProfile> tslProfiles = new HashSet<>();
        for(TslProfile tslProfile:tslUser.getProfiles()) {
            Optional<TslProfile> optTslProfile = this.profileRepository.findById(tslProfile.getIdProfile());
            if(optTslProfile.isPresent())
                tslProfiles.add(optTslProfile.get());
        }
        tslUser.setProfiles(tslProfiles);
        this.userRepository.save(tslUser);
    }

    @Transactional
    @Modifying
    public void updateUser(TslUser tslUser) {
        if(!this.userRepository.existsById(tslUser.getIdUser()))
            throw new TslNotFoundException();
        if(this.userRepository.existsByIdCompanyAndLastNameAndFirstNameAndEmailAndIdUserIsNot(tslUser.getIdCompany(),tslUser.getLastName(),tslUser.getFirstName(),tslUser.getEmail(),tslUser.getIdUser()))
            throw new TslFoundException();
        Optional<TslUser> optTslUser = this.userRepository.findById(tslUser.getIdUser());
        if(optTslUser.isPresent())
            tslUser.setCredentialsPassword(optTslUser.get().getCredentialsPassword());
        this.userRepository.save(tslUser);
    }

    @Transactional
    @Modifying
    public void deleteUser(Integer id) {
        if(!this.userRepository.existsById(id))
            throw new TslNotFoundException();
        this.userRepository.deleteById(id);
    }

    @Transactional
    public List<TslUser> getUsers(Integer idCompany) {
        Sort sort = Sort.by("lastName").ascending().and(Sort.by("firstName").ascending());
        return this.userRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslUser getUser(Integer id) {
        Optional<TslUser> optTslUser = this.userRepository.findById(id);
        if(!optTslUser.isPresent())
            throw new TslNotFoundException();
        return optTslUser.get();
    }

    @Transactional
    @Modifying
    public void passwordChange(PasswordChangeRequest passwordChangeRequest) {
        Optional<TslUser> optTslUser = this.userRepository.findById(passwordChangeRequest.getUser().getIdUser());
        if(!optTslUser.isPresent())
            throw new TslNotFoundException();
        TslUser tslUser = optTslUser.get();
        if(!passwordChangeRequest.getNewPassword().equals(passwordChangeRequest.getNewPasswordValidation()))
            throw new TslPasswordChangeException(TslFunctions.getValidation("password.mismatch"));
        this.userRepository.passwordChange(tslUser.getIdUser(),this.passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
    }

}
