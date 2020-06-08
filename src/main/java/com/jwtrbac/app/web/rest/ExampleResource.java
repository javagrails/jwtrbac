package com.jwtrbac.app.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * REST controller for managing the current user's account.
 * https://www.baeldung.com/spring-rest-http-headers
 * https://github.com/eugenp/tutorials/tree/master/spring-mvc-basics-3
 * https://github.com/eugenp/tutorials/blob/master/spring-mvc-basics-3/src/main/java/com/baeldung/spring/headers/controller/ReadHeaderRestController.java
 * https://github.com/eugenp/tutorials/blob/master/spring-mvc-basics-3/src/test/java/com/baeldung/headers/controller/ReadHeaderRestControllerIntegrationTest.java
 * *** https://github.com/eugenp/tutorials/tree/master/spring-mvc-basics-3/src/main/java/com/baeldung/exclude_urls_filter/filter
 * *** https://github.com/eugenp/tutorials/tree/master/spring-mvc-basics-3/src/main/java/com/baeldung/exclude_urls_filter
 */
@RestController
@RequestMapping("/api/examples")
public class ExampleResource {

    private final Logger log = LoggerFactory.getLogger(ExampleResource.class);

    public ExampleResource() {

    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestHeader(value = "accept-language") String language) {
        String greeting      = "";
        String firstLanguage = (language.length() > 1 ? language.substring(0, 2) : language);
        System.out.println("___");
        switch (firstLanguage) {
            case "es":
                greeting = "Hola!";
                break;
            case "de":
                greeting = "Hallo!";
                break;
            case "fr":
                greeting = "Bonjour!";
                break;
            case "bn":
                greeting = "আসসালামুয়ালাইকুম!";
                break;
            case "en":
            default:
                greeting = "Hello!";
                break;
        }

        return new ResponseEntity<String>(greeting, HttpStatus.OK);
    }

    @GetMapping("/double")
    public ResponseEntity<String> doubleNumber(@RequestHeader("my-number") int myNumber) {
        System.out.println("___");
        return new ResponseEntity<String>(String.format("%d * 2 = %d", myNumber, (myNumber * 2)),HttpStatus.OK);
    }

    @GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(@RequestHeader Map<String, String> headers) {
        System.out.println("___");
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value));
        });

        return new ResponseEntity<String>(String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

    @GetMapping("/multiValue")
    public ResponseEntity<String> multiValue(@RequestHeader MultiValueMap<String, String> headers) {
        System.out.println("___");
        headers.forEach((key, value) -> {
            log.info(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });

        return new ResponseEntity<String>(String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

    @GetMapping("/multiple-headers")
    public ResponseEntity<String> multipleHeaders(@RequestHeader MultiValueMap<String, String> headers) {
        System.out.println("___");
        System.out.println("======================multiple-headers======================");
        headers.forEach((key, value) -> {
            //log.info(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
            System.out.println(String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
        });
        System.out.println("======================multiple-headers======================");
        return new ResponseEntity<String>(String.format("Listed %d headers", headers.size()), HttpStatus.OK);
    }

    @GetMapping("/getBaseUrl")
    public ResponseEntity<String> getBaseUrl(@RequestHeader HttpHeaders headers) {
        System.out.println("___");
        InetSocketAddress host = headers.getHost();
        String            url  = "http://" + host.getHostName() + ":" + host.getPort();
        return new ResponseEntity<String>(String.format("Base URL = %s", url), HttpStatus.OK);
    }

    @GetMapping("/nonRequiredHeader")
    public ResponseEntity<String> evaluateNonRequiredHeader(@RequestHeader(value = "optional-header", required = false) String optionalHeader) {
        System.out.println("___");
        return new ResponseEntity<String>(
            String.format("Was the optional header present? %s!", (optionalHeader == null ? "No" : "Yes")),
            HttpStatus.OK);
    }

    @GetMapping("/default")
    public ResponseEntity<String> evaluateDefaultHeaderValue(@RequestHeader(value = "optional-header", defaultValue = "3600") int optionalHeader) {
        System.out.println("___");
        return new ResponseEntity<String>(String.format("Optional Header is %d", optionalHeader), HttpStatus.OK);
    }
}
