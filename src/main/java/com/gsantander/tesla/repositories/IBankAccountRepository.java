package com.gsantander.tesla.repositories;

import com.gsantander.tesla.enums.BankAccountType;
import com.gsantander.tesla.model.TslBank;
import com.gsantander.tesla.model.TslBankAccount;
import com.gsantander.tesla.model.TslCurrency;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBankAccountRepository extends JpaRepository <TslBankAccount, Integer> {

    List<TslBankAccount> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndBankAndTypeAndCurrencyAndDescription(Integer idCompany, TslBank tslBank, BankAccountType type, TslCurrency tslCurrency, String description);

    boolean existsByIdCompanyAndBankAndTypeAndCurrencyAndDescriptionAndIdBankAccountIsNot(Integer idCompany, TslBank tslBank, BankAccountType type, TslCurrency tslCurrency, String description, Integer idBankAccount);

}
