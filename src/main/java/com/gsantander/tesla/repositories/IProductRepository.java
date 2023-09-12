package com.gsantander.tesla.repositories;

import com.gsantander.tesla.model.TslProduct;
import com.gsantander.tesla.model.TslProductArticle;
import com.gsantander.tesla.model.TslProductFamily;
import com.gsantander.tesla.model.TslProductMeasure;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository <TslProduct, Integer> {

    List<TslProduct> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndFamilyAndArticleAndMeasureAndDescription(Integer idCompany, TslProductFamily family, TslProductArticle article, TslProductMeasure measure, String description);

    boolean existsByIdCompanyAndFamilyAndArticleAndMeasureAndDescriptionAndIdProductIsNot(Integer idCompany, TslProductFamily family, TslProductArticle article, TslProductMeasure measure, String description, Integer idProduct);

}
