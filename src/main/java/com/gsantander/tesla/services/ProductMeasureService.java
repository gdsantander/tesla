package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslProductMeasure;
import com.gsantander.tesla.repositories.IProductMeasureRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductMeasureService {

    private final IProductMeasureRepository productMeasureRepository;

    public ProductMeasureService(IProductMeasureRepository productMeasureRepository) {
        this.productMeasureRepository = productMeasureRepository;
    }

    @Transactional
    public void insertProductMeasure(TslProductMeasure tslProductMeasure) {
        if(this.productMeasureRepository.existsByIdCompanyAndDescription(tslProductMeasure.getIdCompany(),
                                                                         tslProductMeasure.getDescription()))
            throw new TslFoundException();
        this.productMeasureRepository.save(tslProductMeasure);
    }

    @Transactional
    @Modifying
    public void updateProductMeasure(TslProductMeasure tslProductMeasure) {
        if(!this.productMeasureRepository.existsById(tslProductMeasure.getIdProductMeasure()))
            throw new TslNotFoundException();
        if(this.productMeasureRepository.existsByIdCompanyAndDescriptionAndIdProductMeasureIsNot(tslProductMeasure.getIdCompany(),
                                                                                                 tslProductMeasure.getDescription(),
                                                                                                 tslProductMeasure.getIdProductMeasure()))
            throw new TslFoundException();
        this.productMeasureRepository.save(tslProductMeasure);
    }

    @Transactional
    @Modifying
    public void deleteProductMeasure(Integer id) {
        if(!this.productMeasureRepository.existsById(id))
            throw new TslNotFoundException();
        this.productMeasureRepository.deleteById(id);
    }

    @Transactional
    public List<TslProductMeasure> getProductMeasures(Integer idCompany) {
        Sort sort = Sort.by("description").ascending();
        return this.productMeasureRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslProductMeasure getProductMeasure(Integer id) {
        Optional<TslProductMeasure> optTslProductMeasure = this.productMeasureRepository.findById(id);
        if(!optTslProductMeasure.isPresent())
            throw new TslNotFoundException();
        return optTslProductMeasure.get();
    }

}
