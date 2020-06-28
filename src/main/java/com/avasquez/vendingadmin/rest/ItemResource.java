package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.ItemService;
import com.avasquez.vendingadmin.service.dto.ItemDTO;
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
import java.util.Set;

/**
 * REST controller for managing {@link com.avasquez.vendingadmin.domain.Item}.
 */
@RestController
@RequestMapping("/api")
public class ItemResource {

    private final Logger log = LoggerFactory.getLogger(ItemResource.class);
    private final ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public ResponseEntity<Page<ItemDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of Items");
        Page<ItemDTO> page = itemService.findAll(pageable);
        return new ResponseEntity<Page<ItemDTO>>(page, null, HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDTO> get(@PathVariable Long id) {
        log.debug("REST request to get Item : {}", id);
        Optional<ItemDTO> result = itemService.find(id);
        return result.map(response -> ResponseEntity.ok().body(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/items")
    public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO dto) throws URISyntaxException {
        log.debug("REST request to save Item : {}", dto);
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        ItemDTO result = itemService.save(dto);
        return ResponseEntity.created(new URI("/api/items" + result.getId()))
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/items/batch")
    public ResponseEntity<List<ItemDTO>> create(@RequestBody List<ItemDTO> dtos) throws URISyntaxException {
        log.debug("REST request to save Item Import: {}");
        for(ItemDTO dto: dtos) {
            if (dto.getId() != null) {
                return ResponseEntity.badRequest().build();
            }
        }
        List<ItemDTO> result = itemService.save(dtos);
        return ResponseEntity.created(new URI("/api/items"))
                .body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/items")
    public ResponseEntity<ItemDTO> update(@RequestBody ItemDTO dto) throws URISyntaxException {
        log.debug("REST request to update Item : {}", dto);
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        ItemDTO result = itemService.save(dto);
        return ResponseEntity.ok().body(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete Item : {}", id);
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }
}
