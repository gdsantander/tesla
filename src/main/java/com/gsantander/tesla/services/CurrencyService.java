package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslCurrency;
import com.gsantander.tesla.repositories.ICurrencyRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    private final ICurrencyRepository currencyRepository;

    public CurrencyService(ICurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Transactional
    public void insertCurrency(TslCurrency tslCurrency) {
        if(this.currencyRepository.existsByDescription(tslCurrency.getDescription()))
            throw new TslFoundException();
        this.currencyRepository.save(tslCurrency);
    }

    @Transactional
    @Modifying
    public void updateCurrency(TslCurrency tslCurrency) {
        if(!this.currencyRepository.existsById(tslCurrency.getIdCurrency()))
            throw new TslNotFoundException();
        if(this.currencyRepository.existsByDescriptionAndIdCurrencyIsNot(tslCurrency.getDescription(),tslCurrency.getIdCurrency()))
            throw new TslFoundException();
        this.currencyRepository.save(tslCurrency);
    }

    @Transactional
    @Modifying
    public void deleteCurrency(Integer id) {
        if(!this.currencyRepository.existsById(id))
            throw new TslNotFoundException();
        this.currencyRepository.deleteById(id);
    }

    @Transactional
    public List<TslCurrency> getCurrencies() {
        return this.currencyRepository.findAll();
    }

    public TslCurrency getCurrency(Integer id) {
        Optional<TslCurrency> optTsl = this.currencyRepository.findById(id);
        if(!optTsl.isPresent())
            throw new TslNotFoundException();
        return optTsl.get();
    }

}