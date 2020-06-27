package com.avasquez.vendingadmin.service.mapper;

import com.avasquez.vendingadmin.domain.User;
import com.avasquez.vendingadmin.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {

    default User fromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}