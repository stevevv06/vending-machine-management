package com.avasquez.vendingadmin.service.impl;

import com.avasquez.vendingadmin.domain.Role;
import com.avasquez.vendingadmin.domain.RoleName;
import com.avasquez.vendingadmin.domain.UnlockAttemp;
import com.avasquez.vendingadmin.domain.User;
import com.avasquez.vendingadmin.repository.RoleRepository;
import com.avasquez.vendingadmin.repository.UserRepository;
import com.avasquez.vendingadmin.security.SecurityUtils;
import com.avasquez.vendingadmin.service.api.UserService;
import com.avasquez.vendingadmin.service.dto.UnlockAttempDTO;
import com.avasquez.vendingadmin.service.dto.UserDTO;
import com.avasquez.vendingadmin.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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

    /**
     * Save a collection of UnlockAttemp.
     *
     * @param dto the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<UserDTO> save(List<UserDTO> dto) {
        List<User> ent = userMapper.toEntity(dto);
        ent = userRepository.saveAll(ent);
        return userMapper.toDto(ent);
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
