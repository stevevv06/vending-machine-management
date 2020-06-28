package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.VendingMachineCashService;
import com.avasquez.vendingadmin.service.dto.VendingMachineCashDTO;
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
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.VendingMachineCash}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineCashResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineCashResource.class);
    private final VendingMachineCashService vendingMachineCashService;

    public VendingMachineCashResource(VendingMachineCashService vendingMachineCashService) {
        this.vendingMachineCashService = vendingMachineCashService;
    }

    @GetMapping("/vending-machine-cashs")
    public ResponseEntity<Page<VendingMachineCashDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of VendingMachineCash");
        Page<VendingMachineCashDTO> page = vendingMachineCashService.findAll(pageable);
        return new ResponseEntity<Page<VendingMachineCashDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/vending-machine-cashs/{id}")
    public ResponseEntity<VendingMachineCashDTO> get(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineCash : {}", id);
        Optional<VendingMachineCashDTO> result = vendingMachineCashService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/vending-machine-cashs")
    public ResponseEntity<VendingMachineCashDTO> create(@RequestBody VendingMachineCashDTO dto) throws URISyntaxException {
        log.debug("REST request to save VendingMachineCash : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineCashDTO result = vendingMachineCashService.save(dto);
        return ResponseEntity.created(new URI("/api/vending-machine-cashs" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/vending-machine-cashs")
    public ResponseEntity<VendingMachineCashDTO> update(@RequestBody VendingMachineCashDTO dto) throws URISyntaxException {
        log.debug("REST request to update VendingMachineCash : {}", dto);
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineCashDTO result = vendingMachineCashService.save(dto);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/vending-machine-cashs/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineCash : {}", id);
        vendingMachineCashService.delete(id);
        return ResponseEntity.ok().build();
    }
}
