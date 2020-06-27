package com.avasquez.web.rest;

import com.avasquez.service.CollectionAlertService;
import com.avasquez.web.rest.errors.BadRequestAlertException;
import com.avasquez.service.dto.CollectionAlertDTO;
import com.avasquez.service.dto.CollectionAlertCriteria;
import com.avasquez.service.CollectionAlertQueryService;

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
 * REST controller for managing {@link com.avasquez.domain.CollectionAlert}.
 */
@RestController
@RequestMapping("/api")
public class CollectionAlertResource {

    private final Logger log = LoggerFactory.getLogger(CollectionAlertResource.class);

    private static final String ENTITY_NAME = "collectionAlert";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CollectionAlertService collectionAlertService;

    private final CollectionAlertQueryService collectionAlertQueryService;

    public CollectionAlertResource(CollectionAlertService collectionAlertService, CollectionAlertQueryService collectionAlertQueryService) {
        this.collectionAlertService = collectionAlertService;
        this.collectionAlertQueryService = collectionAlertQueryService;
    }

    /**
     * {@code POST  /collection-alerts} : Create a new collectionAlert.
     *
     * @param collectionAlertDTO the collectionAlertDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new collectionAlertDTO, or with status {@code 400 (Bad Request)} if the collectionAlert has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/collection-alerts")
    public ResponseEntity<CollectionAlertDTO> createCollectionAlert(@RequestBody CollectionAlertDTO collectionAlertDTO) throws URISyntaxException {
        log.debug("REST request to save CollectionAlert : {}", collectionAlertDTO);
        if (collectionAlertDTO.getId() != null) {
            throw new BadRequestAlertException("A new collectionAlert cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CollectionAlertDTO result = collectionAlertService.save(collectionAlertDTO);
        return ResponseEntity.created(new URI("/api/collection-alerts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /collection-alerts} : Updates an existing collectionAlert.
     *
     * @param collectionAlertDTO the collectionAlertDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collectionAlertDTO,
     * or with status {@code 400 (Bad Request)} if the collectionAlertDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the collectionAlertDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/collection-alerts")
    public ResponseEntity<CollectionAlertDTO> updateCollectionAlert(@RequestBody CollectionAlertDTO collectionAlertDTO) throws URISyntaxException {
        log.debug("REST request to update CollectionAlert : {}", collectionAlertDTO);
        if (collectionAlertDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CollectionAlertDTO result = collectionAlertService.save(collectionAlertDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, collectionAlertDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /collection-alerts} : get all the collectionAlerts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of collectionAlerts in body.
     */
    @GetMapping("/collection-alerts")
    public ResponseEntity<List<CollectionAlertDTO>> getAllCollectionAlerts(CollectionAlertCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CollectionAlerts by criteria: {}", criteria);
        Page<CollectionAlertDTO> page = collectionAlertQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /collection-alerts/count} : count all the collectionAlerts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/collection-alerts/count")
    public ResponseEntity<Long> countCollectionAlerts(CollectionAlertCriteria criteria) {
        log.debug("REST request to count CollectionAlerts by criteria: {}", criteria);
        return ResponseEntity.ok().body(collectionAlertQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /collection-alerts/:id} : get the "id" collectionAlert.
     *
     * @param id the id of the collectionAlertDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the collectionAlertDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/collection-alerts/{id}")
    public ResponseEntity<CollectionAlertDTO> getCollectionAlert(@PathVariable Long id) {
        log.debug("REST request to get CollectionAlert : {}", id);
        Optional<CollectionAlertDTO> collectionAlertDTO = collectionAlertService.findOne(id);
        return ResponseUtil.wrapOrNotFound(collectionAlertDTO);
    }

    /**
     * {@code DELETE  /collection-alerts/:id} : delete the "id" collectionAlert.
     *
     * @param id the id of the collectionAlertDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/collection-alerts/{id}")
    public ResponseEntity<Void> deleteCollectionAlert(@PathVariable Long id) {
        log.debug("REST request to delete CollectionAlert : {}", id);
        collectionAlertService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
