package com.gsantander.tesla.services;

import com.gsantander.tesla.enums.CurrentAccountEntryEffect;
import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.*;
import com.gsantander.tesla.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CBillService {

    private final ICBillRepository cBillRepository;
    private final ICompanyRepository companyRepository;
    private final ISalesPointRepository salesPointRepository;
    private final ICurrencyRepository currencyRepository;
    private final IDocumentRepository documentRepository;
    private final ICustomerRepository customerRepository;
    private final IVatAliquotRepository vatAliquotRepository;

    public CBillService(ICBillRepository cBillRepository, ICompanyRepository companyRepository, ISalesPointRepository salesPointRepository, ICurrencyRepository currencyRepository, IDocumentRepository documentRepository, ICustomerRepository customerRepository, IVatAliquotRepository vatAliquotRepository) {
        this.cBillRepository = cBillRepository;
        this.companyRepository = companyRepository;
        this.salesPointRepository = salesPointRepository;
        this.currencyRepository = currencyRepository;
        this.documentRepository = documentRepository;
        this.customerRepository = customerRepository;
        this.vatAliquotRepository = vatAliquotRepository;
    }

    @Transactional
    public TslCBill insertCBill(TslCBill tslCBill) {
        if(this.cBillRepository.existsByIdCompanyAndSalesPointAndDocumentAndLetterAndPreNumberAndNumber(tslCBill.getIdCompany(),
                                                                                                        tslCBill.getSalesPoint(),
                                                                                                        tslCBill.getDocument(),
                                                                                                        tslCBill.getLetter(),
                                                                                                        tslCBill.getPreNumber(),
                                                                                                        tslCBill.getNumber()))
            throw new TslFoundException();
        for(TslCBillItem tslCBillItem:tslCBill.getItems()) {
            if(tslCBillItem.getVatAliquot()!=null) {
                Optional<TslVatAliquot> optTslVatAliquot = this.vatAliquotRepository.findById(tslCBillItem.getVatAliquot().getIdVatAliquot());
                if(optTslVatAliquot.isPresent())
                    tslCBillItem.setVatAliquot(optTslVatAliquot.get());
            }
        }
        Optional<TslSalesPoint> optTslSalesPoint = this.salesPointRepository.findById(tslCBill.getSalesPoint().getIdSalesPoint());
        if(optTslSalesPoint.isPresent())
            tslCBill.setPreNumber(optTslSalesPoint.get().getPreNumber());
        if(tslCBill.getDocument().getEffect()== CurrentAccountEntryEffect.POSITIVE) {
            if(tslCBill.getDueDates().isEmpty()) {
                TslCBillDueDate tslCBillDueDate = new TslCBillDueDate();
                tslCBillDueDate.setcBill(tslCBill);
                tslCBillDueDate.setAmount(tslCBill.getAmount());
                tslCBill.getDueDates().add(tslCBillDueDate);
            }
        }
        return this.cBillRepository.save(tslCBill);
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
    public void annullCBill(int id) {
        Optional<TslCBill> optTslCBill = this.cBillRepository.findById(id);
        if(optTslCBill.isPresent()) {
            TslCBill tslCBill = optTslCBill.get();
            tslCBill.setAnnulled(true);
            this.cBillRepository.save(tslCBill);
        } else {
            throw new TslNotFoundException();
        }
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
        Sort sort = Sort.by("creditDate").ascending();
        return this.cBillRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslCBill getCBill(Integer id) {
        Optional<TslCBill> optTslCBill = this.cBillRepository.findById(id);
        if(!optTslCBill.isPresent())
            throw new TslNotFoundException();
        return optTslCBill.get();
    }

    @Transactional
    public void numbering(Integer id) {
        this.cBillRepository.numbering(id);
    }

}
