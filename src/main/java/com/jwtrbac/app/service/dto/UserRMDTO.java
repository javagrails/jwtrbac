package com.jwtrbac.app.service.dto;

import com.jwtrbac.app.domain.enumeration.HttpMethod;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.jwtrbac.app.domain.UserRM} entity.
 */
public class UserRMDTO implements Serializable {

    private Long id;

    @NotNull
    private Long userId;

    @Size(max = 50)
    private String serviceId;

    @Size(max = 512)
    private String url;

    @Size(max = 10)
    private HttpMethod httpMethod;

    @NotNull
    private boolean active = false;

    @Size(max = 2000)
    private String combine;

    @Size(max = 3000)
    private String combineHash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getCombine() {
        return combine;
    }

    public void setCombine(String combine) {
        this.combine = combine;
    }

    public String getCombineHash() {
        return combineHash;
    }

    public void setCombineHash(String combineHash) {
        this.combineHash = combineHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserRMDTO userRMDTO = (UserRMDTO) o;

        return new EqualsBuilder()
            .append(id, userRMDTO.id)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "UserRMDTO{" +
            "id=" + id +
            ", userId=" + userId +
            ", serviceId='" + serviceId + '\'' +
            ", url='" + url + '\'' +
            ", httpMethod=" + httpMethod +
            ", active=" + active +
            ", combine='" + combine + '\'' +
            ", combineHash='" + combineHash + '\'' +
            '}';
    }
}
