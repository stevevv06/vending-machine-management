package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.VendingMachineModelService;
import com.avasquez.vendingadmin.service.dto.VendingMachineModelDTO;
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
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.VendingMachineModel}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineModelResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineModelResource.class);
    private final VendingMachineModelService vendingMachineModelService;

    public VendingMachineModelResource(VendingMachineModelService vendingMachineModelService) {
        this.vendingMachineModelService = vendingMachineModelService;
    }

    @GetMapping("/vending-machine-models")
    public ResponseEntity<Page<VendingMachineModelDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of VendingMachineModel");
        Page<VendingMachineModelDTO> page = vendingMachineModelService.findAll(pageable);
        return new ResponseEntity<Page<VendingMachineModelDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/vending-machine-models/{id}")
    public ResponseEntity<VendingMachineModelDTO> get(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineModel : {}", id);
        Optional<VendingMachineModelDTO> result = vendingMachineModelService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/vending-machine-models")
    public ResponseEntity<VendingMachineModelDTO> create(@RequestBody VendingMachineModelDTO dto) throws URISyntaxException {
        log.debug("REST request to save VendingMachineModel : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineModelDTO result = vendingMachineModelService.save(dto);
        return ResponseEntity.created(new URI("/api/vending-machine-models" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/vending-machine-models")
    public ResponseEntity<VendingMachineModelDTO> update(@RequestBody VendingMachineModelDTO dto) throws URISyntaxException {
        log.debug("REST request to update VendingMachineModel : {}", dto);
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineModelDTO result = vendingMachineModelService.save(dto);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/vending-machine-models/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineModel : {}", id);
        vendingMachineModelService.delete(id);
        return ResponseEntity.ok().build();
    }
}
