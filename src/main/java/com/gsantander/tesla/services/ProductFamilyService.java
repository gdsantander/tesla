package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslProductFamily;
import com.gsantander.tesla.repositories.IProductFamilyRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductFamilyService {

    private final IProductFamilyRepository productFamilyRepository;

    public ProductFamilyService(IProductFamilyRepository productFamilyRepository) {
        this.productFamilyRepository = productFamilyRepository;
    }

    @Transactional
    public void insertProductFamily(TslProductFamily tslProductFamily) {
        if(this.productFamilyRepository.existsByIdCompanyAndDescription(tslProductFamily.getIdCompany(),
                                                                         tslProductFamily.getDescription()))
            throw new TslFoundException();
        this.productFamilyRepository.save(tslProductFamily);
    }

    @Transactional
    @Modifying
    public void updateProductFamily(TslProductFamily tslProductFamily) {
        if(!this.productFamilyRepository.existsById(tslProductFamily.getIdProductFamily()))
            throw new TslNotFoundException();
        if(this.productFamilyRepository.existsByIdCompanyAndDescriptionAndIdProductFamilyIsNot(tslProductFamily.getIdCompany(),
                                                                                                 tslProductFamily.getDescription(),
                                                                                                 tslProductFamily.getIdProductFamily()))
            throw new TslFoundException();
        this.productFamilyRepository.save(tslProductFamily);
    }

    @Transactional
    @Modifying
    public void deleteProductFamily(Integer id) {
        if(!this.productFamilyRepository.existsById(id))
            throw new TslNotFoundException();
        this.productFamilyRepository.deleteById(id);
    }

    @Transactional
    public List<TslProductFamily> getProductFamilys(Integer idCompany) {
        Sort sort = Sort.by("description").ascending();
        return this.productFamilyRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslProductFamily getProductFamily(Integer id) {
        Optional<TslProductFamily> optTslProductFamily = this.productFamilyRepository.findById(id);
        if(!optTslProductFamily.isPresent())
            throw new TslNotFoundException();
        return optTslProductFamily.get();
    }

}
