package com.avasquez.movierental.service.impl;

import com.avasquez.movierental.domain.Role;
import com.avasquez.movierental.domain.RoleName;
import com.avasquez.movierental.domain.User;
import com.avasquez.movierental.repository.RoleRepository;
import com.avasquez.movierental.repository.UserRepository;
import com.avasquez.movierental.security.SecurityUtils;
import com.avasquez.movierental.service.api.UserService;
import com.avasquez.movierental.service.dto.UserDTO;
import com.avasquez.movierental.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        Optional<User> u = userRepository.findByUsername(username);
        Optional<UserDTO> dto = Optional.empty();
        if(u.isPresent())
            dto = Optional.ofNullable(userMapper.toDto(u.get()));
        return dto;
    }

    @Override
    public Optional<UserDTO> findCurrentUser() {
        if(!SecurityUtils.isAuthenticated()) {
            return Optional.empty();
        } else {
            String username = SecurityUtils.getCurrentUserLogin().get();
            return findByUsername(username);
        }
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> u = userRepository.findAll(pageable);
        Page<UserDTO> dto = u.map(userMapper::toDto);
        return dto;
    }

    @Override
    public Optional<UserDTO> find(Long id) {
        Optional<User> u = userRepository.findById(id);
        Optional<UserDTO> dto = Optional.empty();
        if(u.isPresent())
            dto = Optional.ofNullable(userMapper.toDto(u.get()));
        return dto;
    }

    @Override
    public UserDTO save(UserDTO o) {
        User mr = userMapper.toEntity(o);
        return userMapper.toDto(userRepository.save(mr));
    }

    public Optional<UserDTO> setUserRole(String username, String role) {
        Optional<User> userE = userRepository.findByUsername(username);
        Optional<Role> roleE = roleRepository.findByName(RoleName.valueOf(role));
        Optional<UserDTO> userDTO = Optional.empty();
        if(userE.isPresent() && roleE.isPresent()) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleE.get());
            User u = userE.get();
            u.setRoles(roles);
            u = userRepository.save(u);
            userDTO = Optional.ofNullable(userMapper.toDto(u));
        }
        return userDTO;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
