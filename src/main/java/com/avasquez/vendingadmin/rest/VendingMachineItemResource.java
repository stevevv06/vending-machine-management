package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.VendingMachineItemService;
import com.avasquez.vendingadmin.service.dto.VendingMachineItemDTO;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.VendingMachineItem}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineItemResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineItemResource.class);
    private final VendingMachineItemService vendingMachineItemService;

    public VendingMachineItemResource(VendingMachineItemService vendingMachineItemService) {
        this.vendingMachineItemService = vendingMachineItemService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/vending-machines/items")
    public ResponseEntity<Page<VendingMachineItemDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of VendingMachineItem");
        Page<VendingMachineItemDTO> page = vendingMachineItemService.findAll(pageable);
        return new ResponseEntity<Page<VendingMachineItemDTO>>(page, null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/vending-machines/items/{id}")
    public ResponseEntity<VendingMachineItemDTO> get(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineItem : {}", id);
        Optional<VendingMachineItemDTO> result = vendingMachineItemService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/vending-machines/items")
    public ResponseEntity<VendingMachineItemDTO> create(@RequestBody VendingMachineItemDTO dto) throws URISyntaxException {
        log.debug("REST request to save VendingMachineItem : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineItemDTO result = vendingMachineItemService.save(dto);
        return ResponseEntity.created(new URI("/api /vending-machine/items" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/vending-machines/items")
    public ResponseEntity<VendingMachineItemDTO> update(@RequestBody VendingMachineItemDTO dto) throws URISyntaxException {
        log.debug("REST request to update VendingMachineItem : {}", dto);
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineItemDTO result = vendingMachineItemService.save(dto);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/vending-machines/items/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineItem : {}", id);
        vendingMachineItemService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vending-machines/{id}/items")
    public ResponseEntity<Page<VendingMachineItemDTO>> getByVendingMachine(@PathVariable Long id, Pageable pageable) {
        log.debug("REST request to get VendingMachineItemDTO items: {}", id);
        Page<VendingMachineItemDTO> result = vendingMachineItemService.findAllByVendingMachineId(id, pageable);
        return new ResponseEntity<Page<VendingMachineItemDTO>>(result, null, HttpStatus.OK);
    }

    @PostMapping("/vending-machines/{id}/items")
    public ResponseEntity<List<VendingMachineItemDTO>> createItems(
            @PathVariable Long id, @RequestBody List<VendingMachineItemDTO> dtos) throws URISyntaxException {
        log.debug("REST request to get VendingMachineItemDTO items: {}", id);
        dtos.forEach(
                i -> i.setVendingMachineId(id)
        );
        List<VendingMachineItemDTO> result = vendingMachineItemService.save(dtos);
        return ResponseEntity.created(new URI("/api/vending-machines/"+"id"+"/items"))
                .body(result);
    }
}
