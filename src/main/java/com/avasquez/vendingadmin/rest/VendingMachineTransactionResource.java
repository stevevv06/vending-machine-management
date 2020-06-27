package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.VendingMachineTransactionService;
import com.avasquez.vendingadmin.service.dto.VendingMachineTransactionDTO;
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

    @GetMapping("/vending-machine-transactions")
    public ResponseEntity<Page<VendingMachineTransactionDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of VendingMachineTransaction");
        Page<VendingMachineTransactionDTO> page = vendingMachineTransactionService.findAll(pageable);
        return new ResponseEntity<Page<VendingMachineTransactionDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/vending-machine-transactions/{id}")
    public ResponseEntity<VendingMachineTransactionDTO> get(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineTransaction : {}", id);
        Optional<VendingMachineTransactionDTO> result = vendingMachineTransactionService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/vending-machine-transactions")
    public ResponseEntity<VendingMachineTransactionDTO> create(@RequestBody VendingMachineTransactionDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to save VendingMachineTransaction : {}", billTypeDTO);
        if (billTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineTransactionDTO result = vendingMachineTransactionService.save(billTypeDTO);
        return ResponseEntity.created(new URI("/api/vending-machine-transactions" + result.getId()))
                .body(result);
    }

    @PutMapping("/vending-machine-transactions")
    public ResponseEntity<VendingMachineTransactionDTO> update(@RequestBody VendingMachineTransactionDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to update VendingMachineTransaction : {}", billTypeDTO);
        if (billTypeDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineTransactionDTO result = vendingMachineTransactionService.save(billTypeDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/vending-machine-transactions/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineTransaction : {}", id);
        vendingMachineTransactionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
