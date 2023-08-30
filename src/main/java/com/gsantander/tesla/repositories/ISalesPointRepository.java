package com.gsantander.tesla.repositories;

import com.gsantander.tesla.model.TslSalesPoint;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISalesPointRepository extends JpaRepository <TslSalesPoint, Integer> {

    List<TslSalesPoint> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndDescription(Integer idCompany, String description);

    boolean existsByIdCompanyAndDescriptionAndIdSalesPointIsNot(Integer idCompany, String description, Integer idSalesPoint);

}
