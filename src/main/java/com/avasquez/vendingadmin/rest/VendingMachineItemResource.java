package com.avasquez.web.rest;

import com.avasquez.service.VendingMachineItemService;
import com.avasquez.web.rest.errors.BadRequestAlertException;
import com.avasquez.service.dto.VendingMachineItemDTO;
import com.avasquez.service.dto.VendingMachineItemCriteria;
import com.avasquez.service.VendingMachineItemQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.avasquez.domain.VendingMachineItem}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineItemResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineItemResource.class);

    private static final String ENTITY_NAME = "vendingMachineItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VendingMachineItemService vendingMachineItemService;

    private final VendingMachineItemQueryService vendingMachineItemQueryService;

    public VendingMachineItemResource(VendingMachineItemService vendingMachineItemService, VendingMachineItemQueryService vendingMachineItemQueryService) {
        this.vendingMachineItemService = vendingMachineItemService;
        this.vendingMachineItemQueryService = vendingMachineItemQueryService;
    }

    /**
     * {@code POST  /vending-machine-items} : Create a new vendingMachineItem.
     *
     * @param vendingMachineItemDTO the vendingMachineItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vendingMachineItemDTO, or with status {@code 400 (Bad Request)} if the vendingMachineItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vending-machine-items")
    public ResponseEntity<VendingMachineItemDTO> createVendingMachineItem(@RequestBody VendingMachineItemDTO vendingMachineItemDTO) throws URISyntaxException {
        log.debug("REST request to save VendingMachineItem : {}", vendingMachineItemDTO);
        if (vendingMachineItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new vendingMachineItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendingMachineItemDTO result = vendingMachineItemService.save(vendingMachineItemDTO);
        return ResponseEntity.created(new URI("/api/vending-machine-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vending-machine-items} : Updates an existing vendingMachineItem.
     *
     * @param vendingMachineItemDTO the vendingMachineItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendingMachineItemDTO,
     * or with status {@code 400 (Bad Request)} if the vendingMachineItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vendingMachineItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vending-machine-items")
    public ResponseEntity<VendingMachineItemDTO> updateVendingMachineItem(@RequestBody VendingMachineItemDTO vendingMachineItemDTO) throws URISyntaxException {
        log.debug("REST request to update VendingMachineItem : {}", vendingMachineItemDTO);
        if (vendingMachineItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendingMachineItemDTO result = vendingMachineItemService.save(vendingMachineItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vendingMachineItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vending-machine-items} : get all the vendingMachineItems.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vendingMachineItems in body.
     */
    @GetMapping("/vending-machine-items")
    public ResponseEntity<List<VendingMachineItemDTO>> getAllVendingMachineItems(VendingMachineItemCriteria criteria, Pageable pageable) {
        log.debug("REST request to get VendingMachineItems by criteria: {}", criteria);
        Page<VendingMachineItemDTO> page = vendingMachineItemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vending-machine-items/count} : count all the vendingMachineItems.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/vending-machine-items/count")
    public ResponseEntity<Long> countVendingMachineItems(VendingMachineItemCriteria criteria) {
        log.debug("REST request to count VendingMachineItems by criteria: {}", criteria);
        return ResponseEntity.ok().body(vendingMachineItemQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /vending-machine-items/:id} : get the "id" vendingMachineItem.
     *
     * @param id the id of the vendingMachineItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendingMachineItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vending-machine-items/{id}")
    public ResponseEntity<VendingMachineItemDTO> getVendingMachineItem(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineItem : {}", id);
        Optional<VendingMachineItemDTO> vendingMachineItemDTO = vendingMachineItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vendingMachineItemDTO);
    }

    /**
     * {@code DELETE  /vending-machine-items/:id} : delete the "id" vendingMachineItem.
     *
     * @param id the id of the vendingMachineItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vending-machine-items/{id}")
    public ResponseEntity<Void> deleteVendingMachineItem(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineItem : {}", id);
        vendingMachineItemService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
