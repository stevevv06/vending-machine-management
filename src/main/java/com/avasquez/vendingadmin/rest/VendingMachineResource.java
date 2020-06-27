package com.avasquez.web.rest;

import com.avasquez.service.VendingMachineService;
import com.avasquez.web.rest.errors.BadRequestAlertException;
import com.avasquez.service.dto.VendingMachineDTO;
import com.avasquez.service.dto.VendingMachineCriteria;
import com.avasquez.service.VendingMachineQueryService;

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
 * REST controller for managing {@link com.avasquez.domain.VendingMachine}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineResource.class);

    private static final String ENTITY_NAME = "vendingMachine";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VendingMachineService vendingMachineService;

    private final VendingMachineQueryService vendingMachineQueryService;

    public VendingMachineResource(VendingMachineService vendingMachineService, VendingMachineQueryService vendingMachineQueryService) {
        this.vendingMachineService = vendingMachineService;
        this.vendingMachineQueryService = vendingMachineQueryService;
    }

    /**
     * {@code POST  /vending-machines} : Create a new vendingMachine.
     *
     * @param vendingMachineDTO the vendingMachineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vendingMachineDTO, or with status {@code 400 (Bad Request)} if the vendingMachine has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vending-machines")
    public ResponseEntity<VendingMachineDTO> createVendingMachine(@RequestBody VendingMachineDTO vendingMachineDTO) throws URISyntaxException {
        log.debug("REST request to save VendingMachine : {}", vendingMachineDTO);
        if (vendingMachineDTO.getId() != null) {
            throw new BadRequestAlertException("A new vendingMachine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendingMachineDTO result = vendingMachineService.save(vendingMachineDTO);
        return ResponseEntity.created(new URI("/api/vending-machines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vending-machines} : Updates an existing vendingMachine.
     *
     * @param vendingMachineDTO the vendingMachineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendingMachineDTO,
     * or with status {@code 400 (Bad Request)} if the vendingMachineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vendingMachineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vending-machines")
    public ResponseEntity<VendingMachineDTO> updateVendingMachine(@RequestBody VendingMachineDTO vendingMachineDTO) throws URISyntaxException {
        log.debug("REST request to update VendingMachine : {}", vendingMachineDTO);
        if (vendingMachineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendingMachineDTO result = vendingMachineService.save(vendingMachineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vendingMachineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vending-machines} : get all the vendingMachines.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vendingMachines in body.
     */
    @GetMapping("/vending-machines")
    public ResponseEntity<List<VendingMachineDTO>> getAllVendingMachines(VendingMachineCriteria criteria, Pageable pageable) {
        log.debug("REST request to get VendingMachines by criteria: {}", criteria);
        Page<VendingMachineDTO> page = vendingMachineQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vending-machines/count} : count all the vendingMachines.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/vending-machines/count")
    public ResponseEntity<Long> countVendingMachines(VendingMachineCriteria criteria) {
        log.debug("REST request to count VendingMachines by criteria: {}", criteria);
        return ResponseEntity.ok().body(vendingMachineQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /vending-machines/:id} : get the "id" vendingMachine.
     *
     * @param id the id of the vendingMachineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendingMachineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vending-machines/{id}")
    public ResponseEntity<VendingMachineDTO> getVendingMachine(@PathVariable Long id) {
        log.debug("REST request to get VendingMachine : {}", id);
        Optional<VendingMachineDTO> vendingMachineDTO = vendingMachineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vendingMachineDTO);
    }

    /**
     * {@code DELETE  /vending-machines/:id} : delete the "id" vendingMachine.
     *
     * @param id the id of the vendingMachineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vending-machines/{id}")
    public ResponseEntity<Void> deleteVendingMachine(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachine : {}", id);
        vendingMachineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
