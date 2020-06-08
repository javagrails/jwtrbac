package com.jwtrbac.app.web.rest.vm;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class HeaderInfo implements Serializable {

    private Long id;

    private String key;

    private String value;

    public HeaderInfo() {
    }

    public HeaderInfo(Long id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        HeaderInfo that = (HeaderInfo) o;

        return new EqualsBuilder()
            .append(id, that.id)
            .append(key, that.key)
            .append(value, that.value)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(id)
            .append(key)
            .append(value)
            .toHashCode();
    }

    @Override
    public String toString() {
        return "HeaderInfo{" +
            "id=" + id +
            ", key='" + key + '\'' +
            ", value='" + value + '\'' +
            '}';
    }
}
