package com.gsantander.tesla.repositories;

import java.util.List;

import com.gsantander.tesla.enums.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gsantander.tesla.model.TslTerritory;

public interface ITerritoryRepository extends JpaRepository <TslTerritory, Integer> {

    List<TslTerritory> findAllByCountryAndParentIsNull(Country country, Sort sort);

    boolean existsByCountryAndParentAndDescription(Country country, TslTerritory parent, String description);

    boolean existsByCountryAndParentAndDescriptionAndIdTerritoryIsNot(Country country, TslTerritory parent, String description, Integer idTerritory);

}
