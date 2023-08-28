package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslBankAccount;
import com.gsantander.tesla.repositories.IBankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    private final IBankAccountRepository bankAccountRepository;

    public BankAccountService(IBankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Transactional
    public void insertBankAccount(TslBankAccount tslBankAccount) {
        if(this.bankAccountRepository.existsByIdCompanyAndBankAndTypeAndCurrencyAndDescription(tslBankAccount.getIdCompany(),
                                                                                               tslBankAccount.getBank(),
                                                                                               tslBankAccount.getType(),
                                                                                               tslBankAccount.getCurrency(),
                                                                                               tslBankAccount.getDescription()))
            throw new TslFoundException();
        this.bankAccountRepository.save(tslBankAccount);
    }

    @Transactional
    @Modifying
    public void updateBankAccount(TslBankAccount tslBankAccount) {
        if(!this.bankAccountRepository.existsById(tslBankAccount.getIdBankAccount()))
            throw new TslNotFoundException();
        if(this.bankAccountRepository.existsByIdCompanyAndBankAndTypeAndCurrencyAndDescriptionAndIdBankAccountIsNot(tslBankAccount.getIdCompany(),
                                                                                                                    tslBankAccount.getBank(),
                                                                                                                    tslBankAccount.getType(),
                                                                                                                    tslBankAccount.getCurrency(),
                                                                                                                    tslBankAccount.getDescription(),
                                                                                                                    tslBankAccount.getIdBankAccount()))
            throw new TslFoundException();
        this.bankAccountRepository.save(tslBankAccount);
    }

    @Transactional
    @Modifying
    public void deleteBankAccount(Integer id) {
        if(!this.bankAccountRepository.existsById(id))
            throw new TslNotFoundException();
        this.bankAccountRepository.deleteById(id);
    }

    @Transactional
    public List<TslBankAccount> getBankAccounts(Integer idCompany) {
        Sort sort = Sort.by("description").ascending();
        return this.bankAccountRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslBankAccount getBankAccount(Integer id) {
        Optional<TslBankAccount> optTslBankAccount = this.bankAccountRepository.findById(id);
        if(!optTslBankAccount.isPresent())
            throw new TslNotFoundException();
        return optTslBankAccount.get();
    }

}
