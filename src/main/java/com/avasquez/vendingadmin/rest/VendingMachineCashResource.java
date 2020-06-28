package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.VendingMachineCashService;
import com.avasquez.vendingadmin.service.dto.VendingMachineCashDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineItemDTO;
import com.avasquez.vendingadmin.service.dto.VendingMachineTotalDTO;
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

    @GetMapping("/vending-machines/cash")
    public ResponseEntity<Page<VendingMachineCashDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of VendingMachineCash");
        Page<VendingMachineCashDTO> page = vendingMachineCashService.findAll(pageable);
        return new ResponseEntity<Page<VendingMachineCashDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/vending-machines/cash/{id}")
    public ResponseEntity<VendingMachineCashDTO> get(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineCash : {}", id);
        Optional<VendingMachineCashDTO> result = vendingMachineCashService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/vending-machines/cash")
    public ResponseEntity<VendingMachineCashDTO> create(@RequestBody VendingMachineCashDTO dto) throws URISyntaxException {
        log.debug("REST request to save VendingMachineCash : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineCashDTO result = vendingMachineCashService.save(dto);
        return ResponseEntity.created(new URI("/api/vending-machines/cash" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/vending-machines/cash")
    public ResponseEntity<VendingMachineCashDTO> update(@RequestBody VendingMachineCashDTO dto) throws URISyntaxException {
        log.debug("REST request to update VendingMachineCash : {}", dto);
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        VendingMachineCashDTO result = vendingMachineCashService.save(dto);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/vending-machines/cash/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineCash : {}", id);
        vendingMachineCashService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vending-machines/{id}/cash")
    public ResponseEntity<Page<VendingMachineCashDTO>> getByVendingMachine(@PathVariable Long id, Pageable pageable) {
        log.debug("REST request to get VendingMachineItemDTO items: {}", id);
        Page<VendingMachineCashDTO> result = vendingMachineCashService.findAllByVendingMachineId(id, pageable);
        return new ResponseEntity<Page<VendingMachineCashDTO>>(result, null, HttpStatus.OK);
    }

    @PostMapping("/vending-machines/{id}/cash")
    public ResponseEntity<List<VendingMachineCashDTO>> createCash(
            @PathVariable Long id, @RequestBody List<VendingMachineCashDTO> dtos) throws URISyntaxException {
        log.debug("REST request to save VendingMachineCash cash: {}", id);
        dtos.forEach(
                i -> i.setVendingMachineId(id)
        );
        List<VendingMachineCashDTO> result = vendingMachineCashService.save(dtos);
        return ResponseEntity.created(new URI("/api/vending-machines/"+"id"+"/cash"))
                .body(result);
    }

    @GetMapping("/vending-machines/{id}/cash/total")
    public ResponseEntity<VendingMachineTotalDTO> getTotalCash(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineTotalDTO total cash: {}", id);
        Optional<VendingMachineTotalDTO> result = vendingMachineCashService.getTotalCashByVendingMachineId(id);
        return result.map(response -> ResponseEntity.ok().body(result.get()))
                .orElse(ResponseEntity.notFound().build());
    }
}
