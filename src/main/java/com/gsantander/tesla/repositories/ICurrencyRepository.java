package com.gsantander.tesla.repositories;

import com.gsantander.tesla.model.TslCurrency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICurrencyRepository extends JpaRepository <TslCurrency, Integer> {

    boolean existsByDescription(String description);

    boolean existsByDescriptionAndIdCurrencyIsNot(String description, Integer idCurrency);

}
