package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.UnlockAttempService;
import com.avasquez.vendingadmin.service.dto.UnlockAttempDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.UnlockAttemp}.
 */
@RestController
@RequestMapping("/api")
public class UnlockAttempResource {

    private final Logger log = LoggerFactory.getLogger(UnlockAttempResource.class);
    private final UnlockAttempService unlockAttempService;

    public UnlockAttempResource(UnlockAttempService unlockAttempService) {
        this.unlockAttempService = unlockAttempService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/unlock-attemps")
    public ResponseEntity<Page<UnlockAttempDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of UnlockAttemps");
        Page<UnlockAttempDTO> page = unlockAttempService.findAll(pageable);
        return new ResponseEntity<Page<UnlockAttempDTO>>(page, null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/unlock-attemps/{id}")
    public ResponseEntity<UnlockAttempDTO> get(@PathVariable Long id) {
        log.debug("REST request to get UnlockAttemp : {}", id);
        Optional<UnlockAttempDTO> result = unlockAttempService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/unlock-attemps")
    public ResponseEntity<UnlockAttempDTO> create(@RequestBody UnlockAttempDTO dto) throws URISyntaxException {
        log.debug("REST request to save UnlockAttemp : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        UnlockAttempDTO result = unlockAttempService.save(dto);
        return ResponseEntity.created(new URI("/api/unlock-attemps" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/unlock-attemps")
    public ResponseEntity<UnlockAttempDTO> update(@RequestBody UnlockAttempDTO dto) throws URISyntaxException {
        log.debug("REST request to update UnlockAttemp : {}", dto);
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        UnlockAttempDTO result = unlockAttempService.save(dto);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/unlock-attemps/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete UnlockAttemp : {}", id);
        unlockAttempService.delete(id);
        return ResponseEntity.ok().build();
    }
}
