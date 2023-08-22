package com.gsantander.tesla.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class I18nConfig {

    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("lang/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(new Locale("es","AR"));
        return messageSource;
    }

    @Bean("validationSource")
    public MessageSource validationSource() {
        ResourceBundleMessageSource validationSource = new ResourceBundleMessageSource();
        validationSource.setBasenames("lang/validations");
        validationSource.setDefaultEncoding("UTF-8");
        validationSource.setDefaultLocale(new Locale("es","AR"));
        return validationSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(this.validationSource());
        return bean;
    }

}