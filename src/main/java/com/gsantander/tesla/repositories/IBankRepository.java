package com.gsantander.tesla.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsantander.tesla.model.TslBank;

public interface IBankRepository extends JpaRepository <TslBank, Integer> {

    boolean existsByDescription(String description);

    boolean existsByDescriptionAndIdBankIsNot(String description, Integer idBank);

}
