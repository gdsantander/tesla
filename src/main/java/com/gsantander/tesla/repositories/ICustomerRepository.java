package com.gsantander.tesla.repositories;

import com.gsantander.tesla.enums.BankAccountType;
import com.gsantander.tesla.model.TslBank;
import com.gsantander.tesla.model.TslBankAccount;
import com.gsantander.tesla.model.TslCurrency;
import com.gsantander.tesla.model.TslCustomer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICustomerRepository extends JpaRepository <TslCustomer, Integer> {

    List<TslCustomer> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndLastNameAndFirstNameAndAndDescription(Integer idCompany, String lastName, String firstName, String description);

    boolean existsByIdCompanyAndLastNameAndFirstNameAndAndDescriptionAndIdCustomerIsNot(Integer idCompany, String lastName, String firstName, String description, Integer idCustomer);

}
