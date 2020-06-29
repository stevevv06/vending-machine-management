package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.VendingMachineTransactionService;
import com.avasquez.vendingadmin.service.dto.VendingMachineTransactionDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTransactionSaleDTO;
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
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.VendingMachineTransaction}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineTransactionResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineTransactionResource.class);
    private final VendingMachineTransactionService vendingMachineTransactionService;

    public VendingMachineTransactionResource(VendingMachineTransactionService vendingMachineTransactionService) {
        this.vendingMachineTransactionService = vendingMachineTransactionService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/vending-machines/transactions")
    public ResponseEntity<Page<VendingMachineTransactionDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of VendingMachineTransaction");
        Page<VendingMachineTransactionDTO> page = vendingMachineTransactionService.findAll(pageable);
        return new ResponseEntity<Page<VendingMachineTransactionDTO>>(page, null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/vending-machines/transactions/{id}")
    public ResponseEntity<VendingMachineTransactionDTO> get(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineTransaction : {}", id);
        Optional<VendingMachineTransactionDTO> result = vendingMachineTransactionService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/vending-machines/transactions")
    public ResponseEntity<VendingMachineTransactionDTO> create(@RequestBody VendingMachineTransactionDTO dto) throws URISyntaxException {
        log.debug("REST request to save VendingMachineTransaction : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineTransactionDTO result = vendingMachineTransactionService.save(dto);
        return ResponseEntity.created(new URI("/api/vending-machines/transactions/" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/vending-machines/transactions")
    public ResponseEntity<VendingMachineTransactionDTO> update(@RequestBody VendingMachineTransactionDTO dto) throws URISyntaxException {
        log.debug("REST request to update VendingMachineTransaction : {}", dto);
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineTransactionDTO result = vendingMachineTransactionService.save(dto);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/vending-machines/transactions/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineTransaction : {}", id);
        vendingMachineTransactionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping("/vending-machines/transactions/sale")
    public ResponseEntity<VendingMachineTransactionDTO> create(
            @RequestBody VendingMachineTransactionSaleDTO dto) throws URISyntaxException {
        log.debug("REST request to save VendingMachineTransaction Sale: {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineTransactionDTO result = vendingMachineTransactionService.save(dto);
        return ResponseEntity.created(new URI("/api/vending-machines/transactions/" + result.getId()))
                .body(result);
    }
}
