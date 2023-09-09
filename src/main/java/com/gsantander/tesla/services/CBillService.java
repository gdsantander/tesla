package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslCBill;
import com.gsantander.tesla.model.TslProfile;
import com.gsantander.tesla.repositories.ICBillRepository;
import com.gsantander.tesla.repositories.ICurrencyRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CBillService {

    private final ICBillRepository cBillRepository;
    private final ICurrencyRepository currencyRepository;

    public CBillService(ICBillRepository cBillRepository, ICurrencyRepository currencyRepository) {
        this.cBillRepository = cBillRepository;
        this.currencyRepository = currencyRepository;
    }

    @Transactional
    public void insertCBill(TslCBill tslCBill) {
        if(this.cBillRepository.existsByIdCompanyAndSalesPointAndDocumentAndLetterAndPreNumberAndNumber(tslCBill.getIdCompany(),
                                                                                                        tslCBill.getSalesPoint(),
                                                                                                        tslCBill.getDocument(),
                                                                                                        tslCBill.getLetter(),
                                                                                                        tslCBill.getPreNumber(),
                                                                                                        tslCBill.getNumber()))
            throw new TslFoundException();
        Optional<TslCurrency> optTslProfile = this.profileRepository.findById(tslProfile.getIdProfile());
        if(optTslProfile.isPresent())
            tslProfiles.add(optTslProfile.get());

        Set<TslProfile> tslProfiles = new HashSet<>();
        for(TslProfile tslProfile:tslUser.getProfiles()) {
            Optional<TslProfile> optTslProfile = this.profileRepository.findById(tslProfile.getIdProfile());
            if(optTslProfile.isPresent())
                tslProfiles.add(optTslProfile.get());
        }

        this.cBillRepository.save(tslCBill);
    }

    @Transactional
    @Modifying
    public void updateCBill(TslCBill tslCBill) {
        if(!this.cBillRepository.existsById(tslCBill.getIdCBill()))
            throw new TslNotFoundException();
        if(this.cBillRepository.existsByIdCompanyAndSalesPointAndDocumentAndLetterAndPreNumberAndNumberAndIdCBillIsNot(tslCBill.getIdCompany(),
                                                                                                                       tslCBill.getSalesPoint(),
                                                                                                                       tslCBill.getDocument(),
                                                                                                                       tslCBill.getLetter(),
                                                                                                                       tslCBill.getPreNumber(),
                                                                                                                       tslCBill.getNumber(),
                                                                                                                       tslCBill.getIdCBill()))
            throw new TslFoundException();
        this.cBillRepository.save(tslCBill);
    }

    @Transactional
    @Modifying
    public void deleteCBill(Integer id) {
        if(!this.cBillRepository.existsById(id))
            throw new TslNotFoundException();
        this.cBillRepository.deleteById(id);
    }

    @Transactional
    public List<TslCBill> getCBills(Integer idCompany) {
        Sort sort = Sort.by("description").ascending();
        return this.cBillRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslCBill getCBill(Integer id) {
        Optional<TslCBill> optTslCBill = this.cBillRepository.findById(id);
        if(!optTslCBill.isPresent())
            throw new TslNotFoundException();
        return optTslCBill.get();
    }

}
