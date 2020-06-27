package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.VendingMachineItemService;
import com.avasquez.vendingadmin.service.dto.VendingMachineItemDTO;
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

    @GetMapping("/vending-machine-items")
    public ResponseEntity<Page<VendingMachineItemDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of VendingMachineItem");
        Page<VendingMachineItemDTO> page = vendingMachineItemService.findAll(pageable);
        return new ResponseEntity<Page<VendingMachineItemDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/vending-machine-items/{id}")
    public ResponseEntity<VendingMachineItemDTO> get(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineItem : {}", id);
        Optional<VendingMachineItemDTO> result = vendingMachineItemService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/vending-machine-items")
    public ResponseEntity<VendingMachineItemDTO> create(@RequestBody VendingMachineItemDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to save VendingMachineItem : {}", billTypeDTO);
        if (billTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineItemDTO result = vendingMachineItemService.save(billTypeDTO);
        return ResponseEntity.created(new URI("/api/vending-machine-items" + result.getId()))
                .body(result);
    }

    @PutMapping("/vending-machine-items")
    public ResponseEntity<VendingMachineItemDTO> update(@RequestBody VendingMachineItemDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to update VendingMachineItem : {}", billTypeDTO);
        if (billTypeDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineItemDTO result = vendingMachineItemService.save(billTypeDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/vending-machine-items/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineItem : {}", id);
        vendingMachineItemService.delete(id);
        return ResponseEntity.ok().build();
    }
}
