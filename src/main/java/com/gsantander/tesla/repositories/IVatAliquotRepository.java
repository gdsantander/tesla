package com.gsantander.tesla.repositories;

import com.gsantander.tesla.model.TslVatAliquot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVatAliquotRepository extends JpaRepository <TslVatAliquot, Integer> {

    boolean existsByDescription(String description);

    boolean existsByDescriptionAndIdVatAliquotIsNot(String description, Integer idVatAliquot);

}
