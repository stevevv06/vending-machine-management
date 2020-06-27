package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.UnlockAttempService;
import com.avasquez.vendingadmin.service.dto.UnlockAttempDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/unlock-attemps")
    public ResponseEntity<Page<UnlockAttempDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of UnlockAttemps");
        Page<UnlockAttempDTO> page = unlockAttempService.findAll(pageable);
        return new ResponseEntity<Page<UnlockAttempDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/unlock-attemps/{id}")
    public ResponseEntity<UnlockAttempDTO> get(@PathVariable Long id) {
        log.debug("REST request to get UnlockAttemp : {}", id);
        Optional<UnlockAttempDTO> result = unlockAttempService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/unlock-attemps")
    public ResponseEntity<UnlockAttempDTO> create(@RequestBody UnlockAttempDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to save UnlockAttemp : {}", billTypeDTO);
        if (billTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        UnlockAttempDTO result = unlockAttempService.save(billTypeDTO);
        return ResponseEntity.created(new URI("/api/unlock-attemps" + result.getId()))
                .body(result);
    }

    @PutMapping("/unlock-attemps")
    public ResponseEntity<UnlockAttempDTO> update(@RequestBody UnlockAttempDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to update UnlockAttemp : {}", billTypeDTO);
        if (billTypeDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        UnlockAttempDTO result = unlockAttempService.save(billTypeDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/unlock-attemps/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete UnlockAttemp : {}", id);
        unlockAttempService.delete(id);
        return ResponseEntity.ok().build();
    }
}
