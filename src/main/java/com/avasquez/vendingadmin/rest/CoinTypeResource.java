package com.avasquez.web.rest;

import com.avasquez.service.CoinTypeService;
import com.avasquez.web.rest.errors.BadRequestAlertException;
import com.avasquez.service.dto.CoinTypeDTO;

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
 * REST controller for managing {@link com.avasquez.domain.CoinType}.
 */
@RestController
@RequestMapping("/api")
public class CoinTypeResource {

    private final Logger log = LoggerFactory.getLogger(CoinTypeResource.class);

    private static final String ENTITY_NAME = "coinType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CoinTypeService coinTypeService;

    public CoinTypeResource(CoinTypeService coinTypeService) {
        this.coinTypeService = coinTypeService;
    }

    /**
     * {@code POST  /coin-types} : Create a new coinType.
     *
     * @param coinTypeDTO the coinTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new coinTypeDTO, or with status {@code 400 (Bad Request)} if the coinType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/coin-types")
    public ResponseEntity<CoinTypeDTO> createCoinType(@RequestBody CoinTypeDTO coinTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CoinType : {}", coinTypeDTO);
        if (coinTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new coinType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CoinTypeDTO result = coinTypeService.save(coinTypeDTO);
        return ResponseEntity.created(new URI("/api/coin-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /coin-types} : Updates an existing coinType.
     *
     * @param coinTypeDTO the coinTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated coinTypeDTO,
     * or with status {@code 400 (Bad Request)} if the coinTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the coinTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/coin-types")
    public ResponseEntity<CoinTypeDTO> updateCoinType(@RequestBody CoinTypeDTO coinTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CoinType : {}", coinTypeDTO);
        if (coinTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CoinTypeDTO result = coinTypeService.save(coinTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, coinTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /coin-types} : get all the coinTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of coinTypes in body.
     */
    @GetMapping("/coin-types")
    public ResponseEntity<List<CoinTypeDTO>> getAllCoinTypes(Pageable pageable) {
        log.debug("REST request to get a page of CoinTypes");
        Page<CoinTypeDTO> page = coinTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /coin-types/:id} : get the "id" coinType.
     *
     * @param id the id of the coinTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the coinTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/coin-types/{id}")
    public ResponseEntity<CoinTypeDTO> getCoinType(@PathVariable Long id) {
        log.debug("REST request to get CoinType : {}", id);
        Optional<CoinTypeDTO> coinTypeDTO = coinTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(coinTypeDTO);
    }

    /**
     * {@code DELETE  /coin-types/:id} : delete the "id" coinType.
     *
     * @param id the id of the coinTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/coin-types/{id}")
    public ResponseEntity<Void> deleteCoinType(@PathVariable Long id) {
        log.debug("REST request to delete CoinType : {}", id);
        coinTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
