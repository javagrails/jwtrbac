package com.jwtrbac.app.security;

import com.jwtrbac.app.domain.User;
import com.jwtrbac.app.domain.enumeration.HttpMethod;
import com.jwtrbac.app.repository.UserRepository;
import com.jwtrbac.app.security.jwt.OpenAuthority;
import com.jwtrbac.app.domain.UserRM;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {
        log.debug("Authenticating {}", login);

        if (new EmailValidator().isValid(login, null)) {
            return userRepository.findOneWithAuthoritiesByEmailIgnoreCase(login)
                .map(user -> createSpringSecurityUser(login, user))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
        }

        String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
        return userRepository.findOneWithAuthoritiesByLogin(lowercaseLogin)
            .map(user -> createSpringSecurityUser(lowercaseLogin, user))
            .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));

    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!user.getActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        /*List<GrantedAuthority> authorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
            user.getPassword(),
            grantedAuthorities);*/

        /*Principal principal = () -> "admin";
        List<JaasGrantedAuthority> authorities = user.getAuthorities().stream()
            .map(authority -> new JaasGrantedAuthority(authority.getName(), principal))
            .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
            user.getPassword(),
            jaasGrantedAuthorities);*/

        /*Principal  principal  = () -> "admin";
        HeaderInfo headerInfo = new HeaderInfo(1l, "KEY", "VALUE");
        List<OpenAuthority> authorities = user.getAuthorities().stream()
            .map(authority -> new OpenAuthority(authority.getName(), headerInfo))
            .collect(Collectors.toList());*/


        List<UserRM> userRMS = permissionsFromDatabase();
        List<OpenAuthority> authorities = user.getAuthorities().stream()
            .map(authority -> new OpenAuthority(authority.getName(), userRMS))
            .collect(Collectors.toList());

        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(user.getLogin(),
            user.getPassword(),
            authorities);
        return user1;
    }

    private List<UserRM> permissionsFromDatabase() {
        List<UserRM> permissions = new ArrayList<>();
        // Long id, String userId, String url, HttpMethod httpMethod
        UserRM userRM1 = new UserRM(1l, 1l, "api/countries/**", HttpMethod.GET);
        UserRM userRM2 = new UserRM(2l, 2l, "api/countries/**", HttpMethod.POST);
        UserRM userRM3 = new UserRM(3l, 3l, "api/countries/**", HttpMethod.PUT);
        UserRM userRM4 = new UserRM(4l, 4l, "api/countries/**", HttpMethod.DELETE);
        UserRM userRM5 = new UserRM(5l, 5l, "api/countries/**", HttpMethod.OPTIONS);

        permissions.add(userRM1);
        permissions.add(userRM2);
        permissions.add(userRM3);
        permissions.add(userRM4);
        permissions.add(userRM5);

        return permissions;
    }
}
