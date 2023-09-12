package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslProductArticle;
import com.gsantander.tesla.repositories.IProductArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductArticleService {

    private final IProductArticleRepository productArticleRepository;

    public ProductArticleService(IProductArticleRepository productArticleRepository) {
        this.productArticleRepository = productArticleRepository;
    }

    @Transactional
    public void insertProductArticle(TslProductArticle tslProductArticle) {
        if(this.productArticleRepository.existsByIdCompanyAndDescription(tslProductArticle.getIdCompany(),
                                                                         tslProductArticle.getDescription()))
            throw new TslFoundException();
        this.productArticleRepository.save(tslProductArticle);
    }

    @Transactional
    @Modifying
    public void updateProductArticle(TslProductArticle tslProductArticle) {
        if(!this.productArticleRepository.existsById(tslProductArticle.getIdProductArticle()))
            throw new TslNotFoundException();
        if(this.productArticleRepository.existsByIdCompanyAndDescriptionAndIdProductArticleIsNot(tslProductArticle.getIdCompany(),
                                                                                                 tslProductArticle.getDescription(),
                                                                                                 tslProductArticle.getIdProductArticle()))
            throw new TslFoundException();
        this.productArticleRepository.save(tslProductArticle);
    }

    @Transactional
    @Modifying
    public void deleteProductArticle(Integer id) {
        if(!this.productArticleRepository.existsById(id))
            throw new TslNotFoundException();
        this.productArticleRepository.deleteById(id);
    }

    @Transactional
    public List<TslProductArticle> getProductArticles(Integer idCompany) {
        Sort sort = Sort.by("description").ascending();
        return this.productArticleRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslProductArticle getProductArticle(Integer id) {
        Optional<TslProductArticle> optTslProductArticle = this.productArticleRepository.findById(id);
        if(!optTslProductArticle.isPresent())
            throw new TslNotFoundException();
        return optTslProductArticle.get();
    }

}
