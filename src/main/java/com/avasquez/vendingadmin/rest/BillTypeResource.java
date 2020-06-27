package com.avasquez.web.rest;

import com.avasquez.service.BillTypeService;
import com.avasquez.web.rest.errors.BadRequestAlertException;
import com.avasquez.service.dto.BillTypeDTO;

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
 * REST controller for managing {@link com.avasquez.domain.BillType}.
 */
@RestController
@RequestMapping("/api")
public class BillTypeResource {

    private final Logger log = LoggerFactory.getLogger(BillTypeResource.class);

    private static final String ENTITY_NAME = "billType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillTypeService billTypeService;

    public BillTypeResource(BillTypeService billTypeService) {
        this.billTypeService = billTypeService;
    }

    /**
     * {@code POST  /bill-types} : Create a new billType.
     *
     * @param billTypeDTO the billTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billTypeDTO, or with status {@code 400 (Bad Request)} if the billType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/bill-types")
    public ResponseEntity<BillTypeDTO> createBillType(@RequestBody BillTypeDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to save BillType : {}", billTypeDTO);
        if (billTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new billType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BillTypeDTO result = billTypeService.save(billTypeDTO);
        return ResponseEntity.created(new URI("/api/bill-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /bill-types} : Updates an existing billType.
     *
     * @param billTypeDTO the billTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billTypeDTO,
     * or with status {@code 400 (Bad Request)} if the billTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/bill-types")
    public ResponseEntity<BillTypeDTO> updateBillType(@RequestBody BillTypeDTO billTypeDTO) throws URISyntaxException {
        log.debug("REST request to update BillType : {}", billTypeDTO);
        if (billTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BillTypeDTO result = billTypeService.save(billTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bill-types} : get all the billTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billTypes in body.
     */
    @GetMapping("/bill-types")
    public ResponseEntity<List<BillTypeDTO>> getAllBillTypes(Pageable pageable) {
        log.debug("REST request to get a page of BillTypes");
        Page<BillTypeDTO> page = billTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bill-types/:id} : get the "id" billType.
     *
     * @param id the id of the billTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bill-types/{id}")
    public ResponseEntity<BillTypeDTO> getBillType(@PathVariable Long id) {
        log.debug("REST request to get BillType : {}", id);
        Optional<BillTypeDTO> billTypeDTO = billTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(billTypeDTO);
    }

    /**
     * {@code DELETE  /bill-types/:id} : delete the "id" billType.
     *
     * @param id the id of the billTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/bill-types/{id}")
    public ResponseEntity<Void> deleteBillType(@PathVariable Long id) {
        log.debug("REST request to delete BillType : {}", id);
        billTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
