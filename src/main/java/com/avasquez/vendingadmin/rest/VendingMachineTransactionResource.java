package com.avasquez.web.rest;

import com.avasquez.service.VendingMachineTransactionService;
import com.avasquez.web.rest.errors.BadRequestAlertException;
import com.avasquez.service.dto.VendingMachineTransactionDTO;
import com.avasquez.service.dto.VendingMachineTransactionCriteria;
import com.avasquez.service.VendingMachineTransactionQueryService;

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
 * REST controller for managing {@link com.avasquez.domain.VendingMachineTransaction}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineTransactionResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineTransactionResource.class);

    private static final String ENTITY_NAME = "vendingMachineTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VendingMachineTransactionService vendingMachineTransactionService;

    private final VendingMachineTransactionQueryService vendingMachineTransactionQueryService;

    public VendingMachineTransactionResource(VendingMachineTransactionService vendingMachineTransactionService, VendingMachineTransactionQueryService vendingMachineTransactionQueryService) {
        this.vendingMachineTransactionService = vendingMachineTransactionService;
        this.vendingMachineTransactionQueryService = vendingMachineTransactionQueryService;
    }

    /**
     * {@code POST  /vending-machine-transactions} : Create a new vendingMachineTransaction.
     *
     * @param vendingMachineTransactionDTO the vendingMachineTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vendingMachineTransactionDTO, or with status {@code 400 (Bad Request)} if the vendingMachineTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vending-machine-transactions")
    public ResponseEntity<VendingMachineTransactionDTO> createVendingMachineTransaction(@RequestBody VendingMachineTransactionDTO vendingMachineTransactionDTO) throws URISyntaxException {
        log.debug("REST request to save VendingMachineTransaction : {}", vendingMachineTransactionDTO);
        if (vendingMachineTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new vendingMachineTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendingMachineTransactionDTO result = vendingMachineTransactionService.save(vendingMachineTransactionDTO);
        return ResponseEntity.created(new URI("/api/vending-machine-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vending-machine-transactions} : Updates an existing vendingMachineTransaction.
     *
     * @param vendingMachineTransactionDTO the vendingMachineTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendingMachineTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the vendingMachineTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vendingMachineTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vending-machine-transactions")
    public ResponseEntity<VendingMachineTransactionDTO> updateVendingMachineTransaction(@RequestBody VendingMachineTransactionDTO vendingMachineTransactionDTO) throws URISyntaxException {
        log.debug("REST request to update VendingMachineTransaction : {}", vendingMachineTransactionDTO);
        if (vendingMachineTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendingMachineTransactionDTO result = vendingMachineTransactionService.save(vendingMachineTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vendingMachineTransactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vending-machine-transactions} : get all the vendingMachineTransactions.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vendingMachineTransactions in body.
     */
    @GetMapping("/vending-machine-transactions")
    public ResponseEntity<List<VendingMachineTransactionDTO>> getAllVendingMachineTransactions(VendingMachineTransactionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get VendingMachineTransactions by criteria: {}", criteria);
        Page<VendingMachineTransactionDTO> page = vendingMachineTransactionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vending-machine-transactions/count} : count all the vendingMachineTransactions.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/vending-machine-transactions/count")
    public ResponseEntity<Long> countVendingMachineTransactions(VendingMachineTransactionCriteria criteria) {
        log.debug("REST request to count VendingMachineTransactions by criteria: {}", criteria);
        return ResponseEntity.ok().body(vendingMachineTransactionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /vending-machine-transactions/:id} : get the "id" vendingMachineTransaction.
     *
     * @param id the id of the vendingMachineTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendingMachineTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vending-machine-transactions/{id}")
    public ResponseEntity<VendingMachineTransactionDTO> getVendingMachineTransaction(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineTransaction : {}", id);
        Optional<VendingMachineTransactionDTO> vendingMachineTransactionDTO = vendingMachineTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vendingMachineTransactionDTO);
    }

    /**
     * {@code DELETE  /vending-machine-transactions/:id} : delete the "id" vendingMachineTransaction.
     *
     * @param id the id of the vendingMachineTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vending-machine-transactions/{id}")
    public ResponseEntity<Void> deleteVendingMachineTransaction(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineTransaction : {}", id);
        vendingMachineTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
