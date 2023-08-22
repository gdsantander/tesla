package com.gsantander.tesla.validators;

import com.gsantander.tesla.annotations.TerritoryValidation;
import com.gsantander.tesla.enums.Country;
import com.gsantander.tesla.model.TslTerritory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TerritoryValidator implements ConstraintValidator<TerritoryValidation, TslTerritory> {

    public boolean isValid(TslTerritory tslTerritory,ConstraintValidatorContext cxt) {
        cxt.disableDefaultConstraintViolation();
        switch (tslTerritory.getType()) {
            case PROVINCE -> {
                if(tslTerritory.getCountry()== Country.NONE) {
                    cxt.buildConstraintViolationWithTemplate("{territory.province.country.isEmpty}")
                       .addNode("country").addConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }

}