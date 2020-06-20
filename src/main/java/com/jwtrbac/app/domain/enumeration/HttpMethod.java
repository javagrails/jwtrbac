package com.jwtrbac.app.domain.enumeration;


import java.util.HashMap;
import java.util.Map;

/**
 * Java 5 enumeration of HTTP request methods. Intended for use
 * with {@link org.springframework.http.client.ClientHttpRequest}
 * and {@link org.springframework.web.client.RestTemplate}.
 * and {@link org.springframework.http.HttpMethod}.
 *
 * @author Copy From Spring
 * @author SALMAN
 * @since 3.0
 */
public enum HttpMethod {

    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;


    private static final Map<String, HttpMethod> mappings = new HashMap<String, HttpMethod>(8);

    static {
        for (HttpMethod httpMethod : values()) {
            mappings.put(httpMethod.name(), httpMethod);
        }
    }

    private HttpMethod() {
    }

    /**
     * Resolve the given method value to an {@code HttpMethod}.
     *
     * @param method the method value as a String
     * @return the corresponding {@code HttpMethod}, or {@code null} if not found
     * @since 4.2.4
     */
    public static HttpMethod resolve(String method) {
        return (method != null ? mappings.get(method) : null);
    }


    /**
     * Determine whether this {@code HttpMethod} matches the given
     * method value.
     *
     * @param method the method value as a String
     * @return {@code true} if it matches, {@code false} otherwise
     * @since 4.2.4
     */
    public boolean matches(String method) {
        return (this == resolve(method));
    }

}
