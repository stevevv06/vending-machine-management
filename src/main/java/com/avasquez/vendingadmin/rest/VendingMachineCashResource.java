package com.avasquez.web.rest;

import com.avasquez.service.VendingMachineCashService;
import com.avasquez.web.rest.errors.BadRequestAlertException;
import com.avasquez.service.dto.VendingMachineCashDTO;
import com.avasquez.service.dto.VendingMachineCashCriteria;
import com.avasquez.service.VendingMachineCashQueryService;

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
 * REST controller for managing {@link com.avasquez.domain.VendingMachineCash}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineCashResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineCashResource.class);

    private static final String ENTITY_NAME = "vendingMachineCash";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VendingMachineCashService vendingMachineCashService;

    private final VendingMachineCashQueryService vendingMachineCashQueryService;

    public VendingMachineCashResource(VendingMachineCashService vendingMachineCashService, VendingMachineCashQueryService vendingMachineCashQueryService) {
        this.vendingMachineCashService = vendingMachineCashService;
        this.vendingMachineCashQueryService = vendingMachineCashQueryService;
    }

    /**
     * {@code POST  /vending-machine-cashes} : Create a new vendingMachineCash.
     *
     * @param vendingMachineCashDTO the vendingMachineCashDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vendingMachineCashDTO, or with status {@code 400 (Bad Request)} if the vendingMachineCash has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vending-machine-cashes")
    public ResponseEntity<VendingMachineCashDTO> createVendingMachineCash(@RequestBody VendingMachineCashDTO vendingMachineCashDTO) throws URISyntaxException {
        log.debug("REST request to save VendingMachineCash : {}", vendingMachineCashDTO);
        if (vendingMachineCashDTO.getId() != null) {
            throw new BadRequestAlertException("A new vendingMachineCash cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendingMachineCashDTO result = vendingMachineCashService.save(vendingMachineCashDTO);
        return ResponseEntity.created(new URI("/api/vending-machine-cashes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vending-machine-cashes} : Updates an existing vendingMachineCash.
     *
     * @param vendingMachineCashDTO the vendingMachineCashDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendingMachineCashDTO,
     * or with status {@code 400 (Bad Request)} if the vendingMachineCashDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vendingMachineCashDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vending-machine-cashes")
    public ResponseEntity<VendingMachineCashDTO> updateVendingMachineCash(@RequestBody VendingMachineCashDTO vendingMachineCashDTO) throws URISyntaxException {
        log.debug("REST request to update VendingMachineCash : {}", vendingMachineCashDTO);
        if (vendingMachineCashDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendingMachineCashDTO result = vendingMachineCashService.save(vendingMachineCashDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vendingMachineCashDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vending-machine-cashes} : get all the vendingMachineCashes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vendingMachineCashes in body.
     */
    @GetMapping("/vending-machine-cashes")
    public ResponseEntity<List<VendingMachineCashDTO>> getAllVendingMachineCashes(VendingMachineCashCriteria criteria, Pageable pageable) {
        log.debug("REST request to get VendingMachineCashes by criteria: {}", criteria);
        Page<VendingMachineCashDTO> page = vendingMachineCashQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vending-machine-cashes/count} : count all the vendingMachineCashes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/vending-machine-cashes/count")
    public ResponseEntity<Long> countVendingMachineCashes(VendingMachineCashCriteria criteria) {
        log.debug("REST request to count VendingMachineCashes by criteria: {}", criteria);
        return ResponseEntity.ok().body(vendingMachineCashQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /vending-machine-cashes/:id} : get the "id" vendingMachineCash.
     *
     * @param id the id of the vendingMachineCashDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendingMachineCashDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vending-machine-cashes/{id}")
    public ResponseEntity<VendingMachineCashDTO> getVendingMachineCash(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineCash : {}", id);
        Optional<VendingMachineCashDTO> vendingMachineCashDTO = vendingMachineCashService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vendingMachineCashDTO);
    }

    /**
     * {@code DELETE  /vending-machine-cashes/:id} : delete the "id" vendingMachineCash.
     *
     * @param id the id of the vendingMachineCashDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vending-machine-cashes/{id}")
    public ResponseEntity<Void> deleteVendingMachineCash(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineCash : {}", id);
        vendingMachineCashService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
