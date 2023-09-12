package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslVatAliquot;
import com.gsantander.tesla.repositories.IVatAliquotRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VatAliquotService {

    private final IVatAliquotRepository vatAliquotRepository;

    public VatAliquotService(IVatAliquotRepository vatAliquotRepository) {
        this.vatAliquotRepository = vatAliquotRepository;
    }

    @Transactional
    public void insertVatAliquot(TslVatAliquot tslVatAliquot) {
        if(this.vatAliquotRepository.existsByDescription(tslVatAliquot.getDescription()))
            throw new TslFoundException();
        this.vatAliquotRepository.save(tslVatAliquot);
    }

    @Transactional
    @Modifying
    public void updateVatAliquot(TslVatAliquot tslVatAliquot) {
        if(!this.vatAliquotRepository.existsById(tslVatAliquot.getIdVatAliquot()))
            throw new TslNotFoundException();
        if(this.vatAliquotRepository.existsByDescriptionAndIdVatAliquotIsNot(tslVatAliquot.getDescription(),tslVatAliquot.getIdVatAliquot()))
            throw new TslFoundException();
        this.vatAliquotRepository.save(tslVatAliquot);
    }

    @Transactional
    @Modifying
    public void deleteVatAliquot(Integer id) {
        if(!this.vatAliquotRepository.existsById(id))
            throw new TslNotFoundException();
        this.vatAliquotRepository.deleteById(id);
    }

    @Transactional
    public List<TslVatAliquot> getVatAliquots() {
        return this.vatAliquotRepository.findAll();
    }

    public TslVatAliquot getVatAliquot(Integer id) {
        Optional<TslVatAliquot> optTsl = this.vatAliquotRepository.findById(id);
        if(!optTsl.isPresent())
            throw new TslNotFoundException();
        return optTsl.get();
    }

}