package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslAdInfo;
import com.gsantander.tesla.model.TslCompany;
import com.gsantander.tesla.repositories.ICompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final ICompanyRepository companyRepository;

    public CompanyService(ICompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public void insertCompany(TslCompany tslCompany) {
        if(tslCompany.getAdInfo()==null)
            tslCompany.setAdInfo(new TslAdInfo());
        if(this.companyRepository.existsByDescription(tslCompany.getDescription()))
            throw new TslFoundException();
        this.companyRepository.save(tslCompany);
    }

    @Transactional
    @Modifying
    public void updateCompany(TslCompany tslCompany) {
        if(!this.companyRepository.existsById(tslCompany.getIdCompany()))
            throw new TslNotFoundException();
        if(this.companyRepository.existsByDescriptionAndIdCompanyIsNot(tslCompany.getDescription(),tslCompany.getIdCompany()))
            throw new TslFoundException();
        this.companyRepository.save(tslCompany);
    }

    @Transactional
    @Modifying
    public void deleteCompany(Integer id) {
        if(!this.companyRepository.existsById(id))
            throw new TslNotFoundException();
        this.companyRepository.deleteById(id);
    }

    @Transactional
    public List<TslCompany> getCompanies() {
        return this.companyRepository.findAll();
    }

    public TslCompany getCompany(Integer id) {
        Optional<TslCompany> optTsl = this.companyRepository.findById(id);
        if(!optTsl.isPresent())
            throw new TslNotFoundException();
        return optTsl.get();
    }

}