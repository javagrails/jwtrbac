package com.jwtrbac.app.service.mapper;

import com.jwtrbac.app.domain.UserRM;
import com.jwtrbac.app.service.dto.UserRMDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link UserRM} and its DTO {@link UserRMDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserRMMapper extends EntityMapper<UserRMDTO, UserRM> {


    default UserRM fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserRM config = new UserRM();
        config.setId(id);
        return config;
    }
}
