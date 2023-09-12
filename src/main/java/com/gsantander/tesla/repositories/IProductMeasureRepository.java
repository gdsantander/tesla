package com.gsantander.tesla.repositories;

import com.gsantander.tesla.model.TslProductMeasure;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductMeasureRepository extends JpaRepository <TslProductMeasure, Integer> {

    List<TslProductMeasure> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndDescription(Integer idCompany, String description);

    boolean existsByIdCompanyAndDescriptionAndIdProductMeasureIsNot(Integer idCompany, String description, Integer idProductMeasure);

}
