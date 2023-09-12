package com.gsantander.tesla.validators;

import com.gsantander.tesla.annotations.CBillItemValidation;
import com.gsantander.tesla.enums.OperationalCenter;
import com.gsantander.tesla.model.TslCBillItem;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CBillItemValidator implements ConstraintValidator<CBillItemValidation, TslCBillItem> {

    public boolean isValid(TslCBillItem tslCBillItem, ConstraintValidatorContext cxt) {
        cxt.disableDefaultConstraintViolation();
        if(tslCBillItem.getcBill().getOperationalCenter().equals(OperationalCenter.NONE.getCode())) {
            if(StringUtils.isBlank(tslCBillItem.getComments())) {
                cxt.buildConstraintViolationWithTemplate("{cBillItem.comments.isEmpty}")
                        .addNode("comments").addConstraintViolation();
                return false;
            }
        }
        if(tslCBillItem.getcBill().getOperationalCenter().equals(OperationalCenter.PRODUCTS.getCode())) {
            if(tslCBillItem.getProduct()==null) {
                cxt.buildConstraintViolationWithTemplate("{cBillItem.product.isNull}")
                        .addNode("product").addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}