package com.jwtrbac.app.web.rest;

import com.jwtrbac.app.security.jwt.JWTFilter;
import com.jwtrbac.app.security.jwt.TokenProvider;
import com.jwtrbac.app.web.rest.vm.HeaderInfo;
import com.jwtrbac.app.web.rest.vm.LoginVM;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {
        // @todo - Salman
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean     rememberMe  = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        String      jwt         = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }
    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        //private List<HeaderInfo> headerInfos = new ArrayList<>();

        JWTToken(String idToken) {
            this.idToken = idToken;

            /*HeaderInfo headerInfo1 = new HeaderInfo(1l, "key1", "value1");
            HeaderInfo headerInfo2 = new HeaderInfo(2l, "key2", "value2");
            HeaderInfo headerInfo3 = new HeaderInfo(3l, "key3", "value3");
            headerInfos.add(headerInfo1);
            headerInfos.add(headerInfo2);
            headerInfos.add(headerInfo3);*/

        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }

        /*@JsonProperty("header_infos")
        public List<HeaderInfo> getHeaderInfos() {
            return headerInfos;
        }

        public void setHeaderInfos(List<HeaderInfo> headerInfos) {
            this.headerInfos = headerInfos;
        }*/
    }
}
