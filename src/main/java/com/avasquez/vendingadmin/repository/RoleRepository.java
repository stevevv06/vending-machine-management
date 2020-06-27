package com.avasquez.vendingadmin.repository;

import com.avasquez.vendingadmin.domain.Role;
import com.avasquez.vendingadmin.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}