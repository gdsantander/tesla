package com.gsantander.tesla.repositories;

import com.gsantander.tesla.enums.Letter;
import com.gsantander.tesla.model.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICBillRepository extends JpaRepository <TslCBill, Integer> {

    List<TslCBill> findAllByIdCompany(Integer idCompany, Sort sort);

    boolean existsByIdCompanyAndSalesPointAndDocumentAndLetterAndPreNumberAndNumber(Integer idCompany, TslSalesPoint tslSalesPoint, TslDocument tslDocument, String letter, Integer preNumber, Integer number);

    boolean existsByIdCompanyAndSalesPointAndDocumentAndLetterAndPreNumberAndNumberAndIdCBillIsNot(Integer idCompany, TslSalesPoint tslSalesPoint, TslDocument tslDocument, String letter, Integer preNumber, Integer number, Integer idCBill);

    @Modifying
    @Query(value = "execute procedure CBILL_NUMBERING(:IdCBill)", nativeQuery = true)
    void numbering(@Param("IdCBill") Integer idCBill);

}
