package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.VendingMachineService;
import com.avasquez.vendingadmin.service.dto.VendingMachineDTO;
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
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.VendingMachine}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineResource.class);
    private final VendingMachineService vendingMachineService;

    public VendingMachineResource(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    @GetMapping("/vending-machines")
    public ResponseEntity<Page<VendingMachineDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of VendingMachineDTO");
        Page<VendingMachineDTO> page = vendingMachineService.findAll(pageable);
        return new ResponseEntity<Page<VendingMachineDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/vending-machines/{id}")
    public ResponseEntity<VendingMachineDTO> get(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineDTO : {}", id);
        Optional<VendingMachineDTO> result = vendingMachineService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/vending-machines")
    public ResponseEntity<VendingMachineDTO> create(@RequestBody VendingMachineDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to save VendingMachineDTO : {}", billTypeDTO);
        if (billTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineDTO result = vendingMachineService.save(billTypeDTO);
        return ResponseEntity.created(new URI("/api/vending-machines" + result.getId()))
                .body(result);
    }

    @PutMapping("/vending-machines")
    public ResponseEntity<VendingMachineDTO> update(@RequestBody VendingMachineDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to update VendingMachineDTO : {}", billTypeDTO);
        if (billTypeDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineDTO result = vendingMachineService.save(billTypeDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/vending-machines/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineDTO : {}", id);
        vendingMachineService.delete(id);
        return ResponseEntity.ok().build();
    }
}
