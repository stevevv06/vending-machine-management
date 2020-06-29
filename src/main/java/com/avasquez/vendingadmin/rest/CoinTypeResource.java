package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.CoinTypeService;
import com.avasquez.vendingadmin.service.dto.CoinTypeDTO;
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
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.CoinType}.
 */
@RestController
@RequestMapping("/api")
public class CoinTypeResource {

    private final Logger log = LoggerFactory.getLogger(CoinTypeResource.class);
    private final CoinTypeService coinTypeService;

    public CoinTypeResource(CoinTypeService coinTypeService) {
        this.coinTypeService = coinTypeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/coin-types")
    public ResponseEntity<Page<CoinTypeDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of CoinTypes");
        Page<CoinTypeDTO> page = coinTypeService.findAll(pageable);
        return new ResponseEntity<Page<CoinTypeDTO>>(page, null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/coin-types/{id}")
    public ResponseEntity<CoinTypeDTO> get(@PathVariable Long id) {
        log.debug("REST request to get CoinType : {}", id);
        Optional<CoinTypeDTO> result = coinTypeService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/coin-types")
    public ResponseEntity<CoinTypeDTO> create(@RequestBody CoinTypeDTO dto) throws URISyntaxException {
        log.debug("REST request to save CoinType : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        CoinTypeDTO result = coinTypeService.save(dto);
        return ResponseEntity.created(new URI("/api/coin-types" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/coin-types")
    public ResponseEntity<CoinTypeDTO> update(@RequestBody CoinTypeDTO dto) throws URISyntaxException {
        log.debug("REST request to update CoinType : {}", dto);
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        CoinTypeDTO result = coinTypeService.save(dto);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/coin-types/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete CoinType : {}", id);
        coinTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
