package com.gsantander.tesla.repositories;

import com.gsantander.tesla.model.TslProductFamily;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductFamilyRepository extends JpaRepository <TslProductFamily, Integer> {

    List<TslProductFamily> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndDescription(Integer idCompany, String description);

    boolean existsByIdCompanyAndDescriptionAndIdProductFamilyIsNot(Integer idCompany, String description, Integer idProductFamily);

}
