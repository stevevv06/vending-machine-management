package com.avasquez.movierental.service.api;

import com.avasquez.movierental.service.dto.UserDTO;

import java.util.Optional;

public interface UserService extends CrudService<UserDTO, Long> {
    public Optional<UserDTO> findByUsername(String username);
    public Optional<UserDTO> findCurrentUser();
    public Optional<UserDTO> setUserRole(String username, String role);
}
