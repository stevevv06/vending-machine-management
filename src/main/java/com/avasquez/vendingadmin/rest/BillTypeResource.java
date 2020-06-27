package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.BillTypeService;
import com.avasquez.vendingadmin.service.dto.BillTypeDTO;
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
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.BillType}.
 */
@RestController
@RequestMapping("/api")
public class BillTypeResource {

    private final Logger log = LoggerFactory.getLogger(BillTypeResource.class);
    private final BillTypeService billTypeService;

    public BillTypeResource(BillTypeService billTypeService) {
        this.billTypeService = billTypeService;
    }

    @GetMapping("/bill-types")
    public ResponseEntity<Page<BillTypeDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of BillTypes");
        Page<BillTypeDTO> page = billTypeService.findAll(pageable);
        return new ResponseEntity<Page<BillTypeDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/bill-types/{id}")
    public ResponseEntity<BillTypeDTO> get(@PathVariable Long id) {
        log.debug("REST request to get BillType : {}", id);
        Optional<BillTypeDTO> result = billTypeService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/bill-types")
    public ResponseEntity<BillTypeDTO> create(@RequestBody BillTypeDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to save BillType : {}", billTypeDTO);
        if (billTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        BillTypeDTO result = billTypeService.save(billTypeDTO);
        return ResponseEntity.created(new URI("/api/bill-types" + result.getId()))
                .body(result);
    }

    @PutMapping("/bill-types")
    public ResponseEntity<BillTypeDTO> update(@RequestBody BillTypeDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to update BillType : {}", billTypeDTO);
        if (billTypeDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        BillTypeDTO result = billTypeService.save(billTypeDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/bill-types/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete BillType : {}", id);
        billTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
