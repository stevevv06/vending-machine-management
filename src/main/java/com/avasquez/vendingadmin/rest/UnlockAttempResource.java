package com.avasquez.web.rest;

import com.avasquez.service.UnlockAttempService;
import com.avasquez.web.rest.errors.BadRequestAlertException;
import com.avasquez.service.dto.UnlockAttempDTO;
import com.avasquez.service.dto.UnlockAttempCriteria;
import com.avasquez.service.UnlockAttempQueryService;

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
 * REST controller for managing {@link com.avasquez.domain.UnlockAttemp}.
 */
@RestController
@RequestMapping("/api")
public class UnlockAttempResource {

    private final Logger log = LoggerFactory.getLogger(UnlockAttempResource.class);

    private static final String ENTITY_NAME = "unlockAttemp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnlockAttempService unlockAttempService;

    private final UnlockAttempQueryService unlockAttempQueryService;

    public UnlockAttempResource(UnlockAttempService unlockAttempService, UnlockAttempQueryService unlockAttempQueryService) {
        this.unlockAttempService = unlockAttempService;
        this.unlockAttempQueryService = unlockAttempQueryService;
    }

    /**
     * {@code POST  /unlock-attemps} : Create a new unlockAttemp.
     *
     * @param unlockAttempDTO the unlockAttempDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unlockAttempDTO, or with status {@code 400 (Bad Request)} if the unlockAttemp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unlock-attemps")
    public ResponseEntity<UnlockAttempDTO> createUnlockAttemp(@RequestBody UnlockAttempDTO unlockAttempDTO) throws URISyntaxException {
        log.debug("REST request to save UnlockAttemp : {}", unlockAttempDTO);
        if (unlockAttempDTO.getId() != null) {
            throw new BadRequestAlertException("A new unlockAttemp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnlockAttempDTO result = unlockAttempService.save(unlockAttempDTO);
        return ResponseEntity.created(new URI("/api/unlock-attemps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unlock-attemps} : Updates an existing unlockAttemp.
     *
     * @param unlockAttempDTO the unlockAttempDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unlockAttempDTO,
     * or with status {@code 400 (Bad Request)} if the unlockAttempDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unlockAttempDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unlock-attemps")
    public ResponseEntity<UnlockAttempDTO> updateUnlockAttemp(@RequestBody UnlockAttempDTO unlockAttempDTO) throws URISyntaxException {
        log.debug("REST request to update UnlockAttemp : {}", unlockAttempDTO);
        if (unlockAttempDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UnlockAttempDTO result = unlockAttempService.save(unlockAttempDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unlockAttempDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unlock-attemps} : get all the unlockAttemps.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unlockAttemps in body.
     */
    @GetMapping("/unlock-attemps")
    public ResponseEntity<List<UnlockAttempDTO>> getAllUnlockAttemps(UnlockAttempCriteria criteria, Pageable pageable) {
        log.debug("REST request to get UnlockAttemps by criteria: {}", criteria);
        Page<UnlockAttempDTO> page = unlockAttempQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unlock-attemps/count} : count all the unlockAttemps.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/unlock-attemps/count")
    public ResponseEntity<Long> countUnlockAttemps(UnlockAttempCriteria criteria) {
        log.debug("REST request to count UnlockAttemps by criteria: {}", criteria);
        return ResponseEntity.ok().body(unlockAttempQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /unlock-attemps/:id} : get the "id" unlockAttemp.
     *
     * @param id the id of the unlockAttempDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unlockAttempDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unlock-attemps/{id}")
    public ResponseEntity<UnlockAttempDTO> getUnlockAttemp(@PathVariable Long id) {
        log.debug("REST request to get UnlockAttemp : {}", id);
        Optional<UnlockAttempDTO> unlockAttempDTO = unlockAttempService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unlockAttempDTO);
    }

    /**
     * {@code DELETE  /unlock-attemps/:id} : delete the "id" unlockAttemp.
     *
     * @param id the id of the unlockAttempDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unlock-attemps/{id}")
    public ResponseEntity<Void> deleteUnlockAttemp(@PathVariable Long id) {
        log.debug("REST request to delete UnlockAttemp : {}", id);
        unlockAttempService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
