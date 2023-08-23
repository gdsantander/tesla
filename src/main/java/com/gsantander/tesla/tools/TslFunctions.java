package com.gsantander.tesla.tools;

import com.gsantander.tesla.enums.TokenType;
import com.gsantander.tesla.interfaces.ITreeEntity;
import com.gsantander.tesla.model.TslTerritory;
import com.gsantander.tesla.records.EnumRecord;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Component
public class TslFunctions {

    @PostConstruct
    public void postConstruct() {
        staticMessageSource = messageSource;
        staticValidationSource = validationSource;
    }

    @Autowired
    public MessageSource messageSource;

    @Autowired
    public MessageSource validationSource;

    public static MessageSource staticMessageSource;
    public static MessageSource staticValidationSource;

    // Messages

    public static String getMessage(String code) {
        if(code.equals(TslConstants.ENUM_NONE))
            return code;
        String message = "";
        try {
            message = staticMessageSource.getMessage(code,null,new Locale("es","AR"));
        } catch (NoSuchMessageException e) {
            message = "";
        }
        if(StringUtils.isBlank(message))
            return "***" + code + "***";
        return message;
    }

    // Validations

    public static String getValidation(String code) {
        if(code.equals(TslConstants.ENUM_NONE))
            return code;
        String validation = "";
        try {
            validation = staticValidationSource.getMessage(code,null,new Locale("es","AR"));
        } catch (NoSuchMessageException e) {
            validation = "";
        }
        if(StringUtils.isBlank(validation))
            return "***" + code + "***";
        return validation;
    }

    // Enums

    public static <E extends Enum<E>> List<EnumRecord> toDTOs(Class<E> clazz){
        List<EnumRecord> items = new ArrayList<>();
        for(E en:EnumSet.allOf(clazz)) {
            items.add(new EnumRecord(en.ordinal(),en.name(),en.toString()));
        }
        return items;
    }

    // Tree

    public static String getHierarchyDescription(ITreeEntity treeEntity) {
        String description = treeEntity.getDescription();
        while(treeEntity.getParent()!=null) {
            description = treeEntity.getParent().getDescription() + " | " + description;
            treeEntity = treeEntity.getParent();
        }
        return description;
    }
    public static int getLevel(ITreeEntity treeEntity) {
        int level = 1;
        while(treeEntity.getParent()!=null) {
            treeEntity = treeEntity.getParent();
            level++;
        }
        return level;
    }

}

