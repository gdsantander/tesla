package com.gsantander.tesla.validators;

import com.gsantander.tesla.annotations.CustomerValidation;
import com.gsantander.tesla.annotations.ProductValidation;
import com.gsantander.tesla.model.TslCustomer;
import com.gsantander.tesla.model.TslProduct;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class ProductValidator implements ConstraintValidator<ProductValidation, TslProduct> {

    public boolean isValid(TslProduct tslProduct, ConstraintValidatorContext cxt) {
        cxt.disableDefaultConstraintViolation();
        if(tslProduct.usingParts()) {
            if(!this.checkParts(tslProduct,cxt))
                return false;
        }
        if(StringUtils.isBlank(tslProduct.getDescription())) {
            if(!this.checkParts(tslProduct,cxt))
                return false;
        } else {
            if(!tslProduct.usingParts()) {
                if(StringUtils.isBlank(tslProduct.getCode())) {
                    cxt.buildConstraintViolationWithTemplate("{product.code.isEmpty}")
                            .addNode("code").addConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkParts(TslProduct tslProduct, ConstraintValidatorContext cxt) {
        if(tslProduct.getFamily()==null) {
            cxt.buildConstraintViolationWithTemplate("{product.family.isNull}")
                    .addNode("family").addConstraintViolation();
            return false;
        }
        if(tslProduct.getArticle()==null) {
            cxt.buildConstraintViolationWithTemplate("{product.article.isNull}")
                    .addNode("article").addConstraintViolation();
            return false;
        }
        if(tslProduct.getMeasure()==null) {
            cxt.buildConstraintViolationWithTemplate("{product.measure.isNull}")
                    .addNode("measure").addConstraintViolation();
            return false;
        }
        return true;
    }

}