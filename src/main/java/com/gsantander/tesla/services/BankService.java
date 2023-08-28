package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslBank;
import com.gsantander.tesla.repositories.IBankRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    private final IBankRepository bankRepository;

    public BankService(IBankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Transactional
    public void insertBank(TslBank tslBank) {
        if(this.bankRepository.existsByDescription(tslBank.getDescription()))
            throw new TslFoundException();
        this.bankRepository.save(tslBank);
    }

    @Transactional
    @Modifying
    public void updateBank(TslBank tslBank) {
        if(!this.bankRepository.existsById(tslBank.getIdBank()))
            throw new TslNotFoundException();
        if(this.bankRepository.existsByDescriptionAndIdBankIsNot(tslBank.getDescription(),tslBank.getIdBank()))
            throw new TslFoundException();
        this.bankRepository.save(tslBank);
    }

    @Transactional
    @Modifying
    public void deleteBank(Integer id) {
        if(!this.bankRepository.existsById(id))
            throw new TslNotFoundException();
        this.bankRepository.deleteById(id);
    }

    @Transactional
    public List<TslBank> getBanks() {
        return this.bankRepository.findAll();
    }

    public TslBank getBank(Integer id) {
        Optional<TslBank> optTsl = this.bankRepository.findById(id);
        if(!optTsl.isPresent())
            throw new TslNotFoundException();
        return optTsl.get();
    }

}