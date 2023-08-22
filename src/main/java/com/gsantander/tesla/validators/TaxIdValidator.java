package com.gsantander.tesla.validators;

import com.gsantander.tesla.annotations.TaxIdValidation;
import com.gsantander.tesla.model.TslAdInfo;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TaxIdValidator implements ConstraintValidator<TaxIdValidation, TslAdInfo>
{
    public boolean isValid(TslAdInfo adInfo, ConstraintValidatorContext cxt) {
        switch (adInfo.getTaxIdType()) {
            case CUIT -> {
                if(!StringUtils.isBlank(adInfo.getTaxId())) {
                    String taxId = adInfo.getTaxId().replace("-","");
                    String coef = "5432765432";
                    try {
                        int su = 0;
                        int lCuit = taxId.length();
                        if (lCuit < 9)
                            return false;
                        for(int i = 1; i < 11; i++) {
                            String Cd1 = coef.substring(i-1, i);
                            String Cd2 = taxId.substring(i-1, i);
                            int cf = Integer.parseInt(Cd1);
                            int ct = Integer.parseInt(Cd2);
                            su += (cf * ct);
                        }
                        int md = su / 11;
                        int re = su - (md * 11);
                        if(re > 1)
                            re = 11 - re;
                        String CdDv = taxId.substring(lCuit - 1, lCuit);
                        int dv = Integer.parseInt(CdDv);
                        if(dv == re) {
                            return true;
                        } else {
                            return false;
                        }
                    } catch (Exception e) {
                        return false;
                    }
                }
                break;
            }
        }
        return true;
    }

}