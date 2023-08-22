package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslProfile;
import com.gsantander.tesla.repositories.IProfileRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    private final IProfileRepository profileRepository;

    public ProfileService(IProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional
    public void insertProfile(TslProfile tslProfile) {
        if(this.profileRepository.existsByIdCompanyAndDescription(tslProfile.getIdCompany(),tslProfile.getDescription()))
            throw new TslFoundException();
        this.profileRepository.save(tslProfile);
    }

    @Transactional
    @Modifying
    public void updateProfile(TslProfile tslProfile) {
        if(!this.profileRepository.existsById(tslProfile.getIdProfile()))
            throw new TslNotFoundException();
        if(this.profileRepository.existsByIdCompanyAndDescriptionAndIdProfileIsNot(tslProfile.getIdCompany(),tslProfile.getDescription(),tslProfile.getIdProfile()))
            throw new TslFoundException();
        this.profileRepository.save(tslProfile);
    }

    @Transactional
    @Modifying
    public void deleteProfile(Integer id) {
        if(!this.profileRepository.existsById(id))
            throw new TslNotFoundException();
        this.profileRepository.deleteById(id);
    }

    @Transactional
    public List<TslProfile> getProfiles(Integer idCompany) {
        Sort sort = Sort.by("description").ascending();
        return this.profileRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslProfile getProfile(Integer id) {
        Optional<TslProfile> optTslProfile = this.profileRepository.findById(id);
        if(!optTslProfile.isPresent())
            throw new TslNotFoundException();
        return optTslProfile.get();
    }

}
