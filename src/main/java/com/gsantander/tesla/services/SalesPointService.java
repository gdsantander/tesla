package com.gsantander.tesla.services;

import com.gsantander.tesla.exceptions.TslFoundException;
import com.gsantander.tesla.exceptions.TslNotFoundException;
import com.gsantander.tesla.model.TslSalesPoint;
import com.gsantander.tesla.repositories.ISalesPointRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesPointService {

    private final ISalesPointRepository salesPointRepository;

    public SalesPointService(ISalesPointRepository salesPointRepository) {
        this.salesPointRepository = salesPointRepository;
    }

    @Transactional
    public void insertSalesPoint(TslSalesPoint tslSalesPoint) {
        if(this.salesPointRepository.existsByIdCompanyAndDescription(tslSalesPoint.getIdCompany(),
                                                                     tslSalesPoint.getDescription()))
            throw new TslFoundException();
        this.salesPointRepository.save(tslSalesPoint);
    }

    @Transactional
    @Modifying
    public void updateSalesPoint(TslSalesPoint tslSalesPoint) {
        if(!this.salesPointRepository.existsById(tslSalesPoint.getIdSalesPoint()))
            throw new TslNotFoundException();
        if(this.salesPointRepository.existsByIdCompanyAndDescriptionAndIdSalesPointIsNot(tslSalesPoint.getIdCompany(),
                                                                                         tslSalesPoint.getDescription(),
                                                                                         tslSalesPoint.getIdSalesPoint()))
            throw new TslFoundException();
        this.salesPointRepository.save(tslSalesPoint);
    }

    @Transactional
    @Modifying
    public void deleteSalesPoint(Integer id) {
        if(!this.salesPointRepository.existsById(id))
            throw new TslNotFoundException();
        this.salesPointRepository.deleteById(id);
    }

    @Transactional
    public List<TslSalesPoint> getSalesPoints(Integer idCompany) {
        Sort sort = Sort.by("description").ascending();
        return this.salesPointRepository.findAllByIdCompany(idCompany,sort);
    }

    public TslSalesPoint getSalesPoint(Integer id) {
        Optional<TslSalesPoint> optTslSalesPoint = this.salesPointRepository.findById(id);
        if(!optTslSalesPoint.isPresent())
            throw new TslNotFoundException();
        return optTslSalesPoint.get();
    }

}
