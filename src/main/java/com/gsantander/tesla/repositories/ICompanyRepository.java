package com.gsantander.tesla.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsantander.tesla.model.TslCompany;

public interface ICompanyRepository extends JpaRepository <TslCompany, Integer> {

    boolean existsByDescription(String description);

    boolean existsByDescriptionAndIdCompanyIsNot(String description, Integer idCompany);

}
