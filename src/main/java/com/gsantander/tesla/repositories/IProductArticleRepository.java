package com.gsantander.tesla.repositories;

import com.gsantander.tesla.model.TslProductArticle;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductArticleRepository extends JpaRepository <TslProductArticle, Integer> {

    List<TslProductArticle> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndDescription(Integer idCompany, String description);

    boolean existsByIdCompanyAndDescriptionAndIdProductArticleIsNot(Integer idCompany, String description, Integer idProductArticle);

}
