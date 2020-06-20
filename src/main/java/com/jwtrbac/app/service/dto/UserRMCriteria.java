package com.jwtrbac.app.service.dto;

import com.jwtrbac.app.domain.enumeration.HttpMethod;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Criteria class for the {@link com.jwtrbac.app.domain.UserRM} entity. This class is used
 * in {@link com.jwtrbac.app.web.rest.UserRMResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /configs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UserRMCriteria implements Serializable, Criteria {

    /**
     * Class for filtering HttpMethod
     */
    public static class HttpMethodFilter extends Filter<HttpMethod> {

        public HttpMethodFilter() {
        }

        public HttpMethodFilter(HttpMethodFilter filter) {
            super(filter);
        }

        @Override
        public HttpMethodFilter copy() {
            return new HttpMethodFilter(this);
        }

    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter userId;

    private StringFilter serviceId;

    private StringFilter url;

    private HttpMethodFilter httpMethod;

    private BooleanFilter active;

    private StringFilter combine;

    private StringFilter combineHash;

    public UserRMCriteria() {
    }

    public UserRMCriteria(UserRMCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.serviceId = other.serviceId == null ? null : other.serviceId.copy();
        this.url = other.url == null ? null : other.url.copy();
        this.httpMethod = other.httpMethod == null ? null : other.httpMethod.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.combine = other.combine == null ? null : other.combine.copy();
        this.combineHash = other.combineHash == null ? null : other.combineHash.copy();
    }

    @Override
    public UserRMCriteria copy() {
        return new UserRMCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public StringFilter getServiceId() {
        return serviceId;
    }

    public void setServiceId(StringFilter serviceId) {
        this.serviceId = serviceId;
    }

    public StringFilter getUrl() {
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public HttpMethodFilter getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethodFilter httpMethod) {
        this.httpMethod = httpMethod;
    }

    public BooleanFilter getActive() {
        return active;
    }

    public void setActive(BooleanFilter active) {
        this.active = active;
    }

    public StringFilter getCombine() {
        return combine;
    }

    public void setCombine(StringFilter combine) {
        this.combine = combine;
    }

    public StringFilter getCombineHash() {
        return combineHash;
    }

    public void setCombineHash(StringFilter combineHash) {
        this.combineHash = combineHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserRMCriteria that = (UserRMCriteria) o;

        return new EqualsBuilder()
            .append(id, that.id)
            .append(userId, that.userId)
            .append(serviceId, that.serviceId)
            .append(url, that.url)
            .append(httpMethod, that.httpMethod)
            .append(active, that.active)
            .append(combine, that.combine)
            .append(combineHash, that.combineHash)
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
        return "UserRMCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (serviceId != null ? "serviceId=" + serviceId + ", " : "") +
            (url != null ? "url=" + url + ", " : "") +
            (httpMethod != null ? "httpMethod=" + httpMethod + ", " : "") +
            (active != null ? "active=" + active + ", " : "") +
            (combine != null ? "contentJson=" + combine + ", " : "") +
            (combineHash != null ? "combineHash=" + combineHash + ", " : "") +
            "}";
    }
}
