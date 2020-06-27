package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.CollectionAlertService;
import com.avasquez.vendingadmin.service.dto.CollectionAlertDTO;
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
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.CollectionAlert}.
 */
@RestController
@RequestMapping("/api")
public class CollectionAlertResource {

    private final Logger log = LoggerFactory.getLogger(CollectionAlertResource.class);
    private final CollectionAlertService collectionAlertService;

    public CollectionAlertResource(CollectionAlertService collectionAlertService) {
        this.collectionAlertService = collectionAlertService;
    }

    @GetMapping("/collection-alerts")
    public ResponseEntity<Page<CollectionAlertDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of CollectionAlerts");
        Page<CollectionAlertDTO> page = collectionAlertService.findAll(pageable);
        return new ResponseEntity<Page<CollectionAlertDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/collection-alerts/{id}")
    public ResponseEntity<CollectionAlertDTO> get(@PathVariable Long id) {
        log.debug("REST request to get CollectionAlert : {}", id);
        Optional<CollectionAlertDTO> result = collectionAlertService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/collection-alerts")
    public ResponseEntity<CollectionAlertDTO> create(@RequestBody CollectionAlertDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CollectionAlert : {}", billTypeDTO);
        if (billTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        CollectionAlertDTO result = collectionAlertService.save(billTypeDTO);
        return ResponseEntity.created(new URI("/api/collection-alerts" + result.getId()))
                .body(result);
    }

    @PutMapping("/collection-alerts")
    public ResponseEntity<CollectionAlertDTO> update(@RequestBody CollectionAlertDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CollectionAlert : {}", billTypeDTO);
        if (billTypeDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        CollectionAlertDTO result = collectionAlertService.save(billTypeDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/collection-alerts/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete CollectionAlert : {}", id);
        collectionAlertService.delete(id);
        return ResponseEntity.ok().build();
    }
}
