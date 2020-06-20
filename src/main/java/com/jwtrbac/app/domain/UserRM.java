package com.jwtrbac.app.domain;

import com.jwtrbac.app.domain.enumeration.HttpMethod;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user_rm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserRM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Size(max = 50)
    @Column(name = "service_id", length = 50)
    private String serviceId;

    @Size(max = 512)
    @Column(name = "url", length = 512)
    private String url;

    @Size(max = 10)
    @Column(name = "http_method", length = 10)
    private HttpMethod httpMethod;

    @NotNull
    @Column(nullable = false)
    private boolean active = false;

    @Size(max = 2000)
    @Column(name = "combine", length = 2000)
    private String combine;

    @Size(max = 3000)
    @Column(name = "combine_hash", length = 3000)
    private String combineHash;

    public UserRM() {
    }

    public UserRM(Long id, Long userId, String url, HttpMethod httpMethod) {
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.httpMethod = httpMethod;
    }

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

        UserRM userRM = (UserRM) o;

        return new EqualsBuilder()
            .append(active, userRM.active)
            .append(id, userRM.id)
            .append(userId, userRM.userId)
            .append(serviceId, userRM.serviceId)
            .append(url, userRM.url)
            .append(httpMethod, userRM.httpMethod)
            .append(combine, userRM.combine)
            .append(combineHash, userRM.combineHash)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(userId)
            .append(serviceId)
            .append(url)
            .append(httpMethod)
            .append(active)
            .append(combine)
            .append(combineHash)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "UserRM{" +
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
