package com.red.code.onlineshopping.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.red.code.onlineshopping.service.dto.RecaptchaResponse;

@Service
public class CaptchaService {
    private final RestTemplate restTemplate;

    public CaptchaService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Value("${emerging.google.recaptcha.secretKey}")
    public String recaptchaSecret;
    @Value("${emerging.google.recaptcha.verifyUrl}")
    public String recaptchaVerifyUrl;

    public boolean verify(String response) {
        MultiValueMap param= new LinkedMultiValueMap<>();
        param.add("secret", recaptchaSecret);
        param.add("response", response);

        RecaptchaResponse recaptchaResponse = null;
        try {
            recaptchaResponse = this.restTemplate.postForObject(recaptchaVerifyUrl, param, RecaptchaResponse.class);
        }catch(RestClientException e){
            System.out.print(e.getMessage());
        }
        if(recaptchaResponse.isSuccess()){
            return true;
        }else {
            return false;
        }
    }


}
