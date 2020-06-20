package com.jwtrbac.app.security.jwt;

import com.jwtrbac.app.web.rest.vm.HeaderInfo;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.security.Principal;

/**
 * {@code GrantedAuthority} which, in addition to the assigned role, holds the principal
 * that an {@link AuthorityGranter} used as a reason to grant this authority.
 *
 * @author Ray Krueger
 * @see AuthorityGranter
 */
public final class CustomGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String    role;
    private final Principal  principal;
    private final HeaderInfo rbac;

    public CustomGrantedAuthority(String role, Principal principal, HeaderInfo rbac) {
        Assert.notNull(role, "role cannot be null");
        Assert.notNull(principal, "principal cannot be null");
        Assert.notNull(rbac, "rbac cannot be null");
        this.role = role;
        this.principal = principal;
        this.rbac = rbac;
    }

    // ~ Methods
    // ========================================================================================================

    public Principal getPrincipal() {
        return principal;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public HeaderInfo getRbac() {
        return rbac;
    }

    @Override
    public String toString() {
        return "CustomGrantedAuthority{" +
            "role='" + role + '\'' +
            ", principal=" + principal +
            ", rbac=" + rbac +
            '}';
    }
}

