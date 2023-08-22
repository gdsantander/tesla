package com.gsantander.tesla.repositories;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gsantander.tesla.model.TslProfile;

public interface IProfileRepository extends JpaRepository <TslProfile, Integer> {

    List<TslProfile> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndDescription(Integer idCompany, String description);

    boolean existsByIdCompanyAndDescriptionAndIdProfileIsNot(Integer idCompany, String description, Integer idProfile);

}