package com.red.code.onlineshopping.web.rest.security;

import com.red.code.onlineshopping.common.utils.SecurityUtils;
import com.red.code.onlineshopping.config.Constants;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserName();
        userName = (userName != null ? userName : Constants.SYSTEM_ACCOUNT);
        return Optional.of(userName);
    }
}