package com.jwtrbac.app.security.jwt;

import com.jwtrbac.app.domain.UserRM;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.util.Assert;

import java.util.List;

/**
 * {@code GrantedAuthority} which, in addition to the assigned role, holds the principal
 * that an {@link AuthorityGranter} used as a reason to grant this authority.
 *
 * @author Ray Krueger
 * @see AuthorityGranter
 */
public final class OpenAuthority implements GrantedAuthority {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final String       role;
    private final List<UserRM> rbac;

    public OpenAuthority(String role, List<UserRM> rbac) {
        Assert.notNull(role, "role cannot be null");
        Assert.notNull(rbac, "rbac cannot be null");
        this.role = role;
        this.rbac = rbac;
    }

    // ~ Methods
    // ========================================================================================================

    @Override
    public String getAuthority() {
        return role;
    }

    public List<UserRM> getRbac() {
        return rbac;
    }

    @Override
    public String toString() {
        return "OpenAuthority{" +
            "role='" + role + '\'' +
            ", rbac=" + rbac +
            '}';
    }
}

