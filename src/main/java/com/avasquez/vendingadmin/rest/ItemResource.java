package com.avasquez.vendingadmin.rest;

import com.avasquez.vendingadmin.service.api.ItemService;
import com.avasquez.vendingadmin.service.dto.ItemDTO;
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

    @PostMapping("/items")
    public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to save Item : {}", billTypeDTO);
        if (billTypeDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        ItemDTO result = itemService.save(billTypeDTO);
        return ResponseEntity.created(new URI("/api/items" + result.getId()))
                .body(result);
    }

    @PutMapping("/items")
    public ResponseEntity<ItemDTO> update(@RequestBody ItemDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to update Item : {}", billTypeDTO);
        if (billTypeDTO.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        ItemDTO result = itemService.save(billTypeDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete Item : {}", id);
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }
}
