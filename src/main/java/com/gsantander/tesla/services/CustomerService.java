package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslAdInfo;
import com.gsantander.tesla.model.TslCustomer;
import com.gsantander.tesla.repositories.ICustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void insertCustomer(TslCustomer tslCustomer) {
        if(tslCustomer.getAdInfo()==null)
            tslCustomer.setAdInfo(new TslAdInfo());
        if(this.customerRepository.existsByIdCompanyAndLastNameAndFirstNameAndAndDescription(tslCustomer.getIdCompany(),
                                                                                             tslCustomer.getLastName(),
                                                                                             tslCustomer.getFirstName(),
                                                                                             tslCustomer.getDescription()))
            throw new TslFoundException();
        this.customerRepository.save(tslCustomer);
    }

    @Transactional
    @Modifying
    public void updateCustomer(TslCustomer tslCustomer) {
        if(!this.customerRepository.existsById(tslCustomer.getIdCustomer()))
            throw new TslNotFoundException();
        if(this.customerRepository.existsByIdCompanyAndLastNameAndFirstNameAndAndDescriptionAndIdCustomerIsNot(tslCustomer.getIdCompany(),
                                                                                                               tslCustomer.getLastName(),
                                                                                                               tslCustomer.getFirstName(),
                                                                                                               tslCustomer.getDescription(),
                                                                                                               tslCustomer.getIdCustomer()))
            throw new TslFoundException();
        this.customerRepository.save(tslCustomer);
    }

    @Transactional
    @Modifying
    public void deleteCustomer(Integer id) {
        if(!this.customerRepository.existsById(id))
            throw new TslNotFoundException();
        this.customerRepository.deleteById(id);
    }

    @Transactional
    public List<TslCustomer> getCustomers(Integer idCompany) {
        Sort sort = Sort.by("description").ascending();
        return this.customerRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslCustomer getCustomer(Integer id) {
        Optional<TslCustomer> optTslCustomer = this.customerRepository.findById(id);
        if(!optTslCustomer.isPresent())
            throw new TslNotFoundException();
        return optTslCustomer.get();
    }

}
