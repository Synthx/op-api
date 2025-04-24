package com.yezebi.pinpin.op.config;

import com.yezebi.pinpin.op.model.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;

@Slf4j
public class FirebaseAuthorityConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {
        try {
            final Boolean emailVerified = jwt.getClaimAsBoolean("email_verified");
            if (Boolean.FALSE.equals(emailVerified)) {
                return Collections.emptyList();
            }

            final GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + UserRole.EMAIL_VERIFIED.name());

            return Collections.singleton(authority);
        } catch (Exception e) {
            log.error("error while parsing authorities of {}", jwt, e);

            return Collections.emptyList();
        }
    }
}
