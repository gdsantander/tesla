package com.gsantander.tesla.repositories;

import com.gsantander.tesla.model.TslDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDocumentRepository extends JpaRepository <TslDocument, Integer> {

    boolean existsByDescription(String description);

    boolean existsByDescriptionAndIdDocumentIsNot(String description, Integer idDocument);

}
