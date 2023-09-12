package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslProduct;
import com.gsantander.tesla.repositories.IProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final IProductRepository productRepository;

    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void insertProduct(TslProduct tslProduct) {
        if(this.productRepository.existsByIdCompanyAndFamilyAndArticleAndMeasureAndDescription(tslProduct.getIdCompany(),
                                                                                               tslProduct.getFamily(),
                                                                                               tslProduct.getArticle(),
                                                                                               tslProduct.getMeasure(),
                                                                                               tslProduct.getDescription()))
            throw new TslFoundException();
        this.productRepository.save(tslProduct);
    }

    @Transactional
    @Modifying
    public void updateProduct(TslProduct tslProduct) {
        if(!this.productRepository.existsById(tslProduct.getIdProduct()))
            throw new TslNotFoundException();
        if(this.productRepository.existsByIdCompanyAndFamilyAndArticleAndMeasureAndDescriptionAndIdProductIsNot(tslProduct.getIdCompany(),
                                                                                                                tslProduct.getFamily(),
                                                                                                                tslProduct.getArticle(),
                                                                                                                tslProduct.getMeasure(),
                                                                                                                tslProduct.getDescription(),
                                                                                                                tslProduct.getIdProduct()))
            throw new TslFoundException();
        this.productRepository.save(tslProduct);
    }

    @Transactional
    @Modifying
    public void deleteProduct(Integer id) {
        if(!this.productRepository.existsById(id))
            throw new TslNotFoundException();
        this.productRepository.deleteById(id);
    }

    @Transactional
    public List<TslProduct> getProducts(Integer idCompany) {
        Sort sort = Sort.by("description").ascending();
        return this.productRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslProduct getProduct(Integer id) {
        Optional<TslProduct> optTslProduct = this.productRepository.findById(id);
        if(!optTslProduct.isPresent())
            throw new TslNotFoundException();
        return optTslProduct.get();
    }

}
