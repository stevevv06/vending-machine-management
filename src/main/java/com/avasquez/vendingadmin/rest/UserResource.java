package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.UserService;
import com.avasquez.vendingadmin.service.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{username}/role/{role}")
    public ResponseEntity<UserDTO> update(@PathVariable String username, @PathVariable String role) {
        Optional<UserDTO> result = userService.setUserRole(username, role);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
