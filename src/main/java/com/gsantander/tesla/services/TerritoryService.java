package com.gsantander.tesla.services;

import com.gsantander.tesla.enums.Country;
import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslTerritory;
import com.gsantander.tesla.repositories.ITerritoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerritoryService {

    private final ITerritoryRepository territoryRepository;

    public TerritoryService(ITerritoryRepository territoryRepository) {
        this.territoryRepository = territoryRepository;
    }

    @Transactional
    public void insertTerritory(TslTerritory tslTerritory) {
        if(this.territoryRepository.existsByCountryAndParentAndDescription(tslTerritory.getCountry(),tslTerritory.getParent(),tslTerritory.getDescription()))
            throw new TslFoundException();
        this.territoryRepository.save(tslTerritory);
    }

    @Transactional
    @Modifying
    public void updateTerritory(TslTerritory tslTerritory) {
        if(!this.territoryRepository.existsById(tslTerritory.getIdTerritory()))
            throw new TslNotFoundException();
        if(this.territoryRepository.existsByCountryAndParentAndDescriptionAndIdTerritoryIsNot(tslTerritory.getCountry(),tslTerritory.getParent(),tslTerritory.getDescription(),tslTerritory.getIdTerritory()))
            throw new TslFoundException();
        this.territoryRepository.save(tslTerritory);
    }

    @Transactional
    @Modifying
    public void deleteTerritory(Integer id) {
        if(!this.territoryRepository.existsById(id))
            throw new TslNotFoundException();
        this.territoryRepository.deleteById(id);
    }

    @Transactional
    public List<TslTerritory> getProvinces(Country country) {
        Sort sort = Sort.by("description").ascending();
        return this.territoryRepository.findAllByCountryAndParentIsNull(country,sort);
    }

    public TslTerritory getTerritory(Integer id) {
        Optional<TslTerritory> optTslTerritory = this.territoryRepository.findById(id);
        if(!optTslTerritory.isPresent())
            throw new TslNotFoundException();
        return optTslTerritory.get();
    }

}