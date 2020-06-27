package com.avasquez.web.rest;

import com.avasquez.service.VendingMachineModelService;
import com.avasquez.web.rest.errors.BadRequestAlertException;
import com.avasquez.service.dto.VendingMachineModelDTO;

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
 * REST controller for managing {@link com.avasquez.domain.VendingMachineModel}.
 */
@RestController
@RequestMapping("/api")
public class VendingMachineModelResource {

    private final Logger log = LoggerFactory.getLogger(VendingMachineModelResource.class);

    private static final String ENTITY_NAME = "vendingMachineModel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VendingMachineModelService vendingMachineModelService;

    public VendingMachineModelResource(VendingMachineModelService vendingMachineModelService) {
        this.vendingMachineModelService = vendingMachineModelService;
    }

    /**
     * {@code POST  /vending-machine-models} : Create a new vendingMachineModel.
     *
     * @param vendingMachineModelDTO the vendingMachineModelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vendingMachineModelDTO, or with status {@code 400 (Bad Request)} if the vendingMachineModel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vending-machine-models")
    public ResponseEntity<VendingMachineModelDTO> createVendingMachineModel(@RequestBody VendingMachineModelDTO vendingMachineModelDTO) throws URISyntaxException {
        log.debug("REST request to save VendingMachineModel : {}", vendingMachineModelDTO);
        if (vendingMachineModelDTO.getId() != null) {
            throw new BadRequestAlertException("A new vendingMachineModel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VendingMachineModelDTO result = vendingMachineModelService.save(vendingMachineModelDTO);
        return ResponseEntity.created(new URI("/api/vending-machine-models/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vending-machine-models} : Updates an existing vendingMachineModel.
     *
     * @param vendingMachineModelDTO the vendingMachineModelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vendingMachineModelDTO,
     * or with status {@code 400 (Bad Request)} if the vendingMachineModelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vendingMachineModelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vending-machine-models")
    public ResponseEntity<VendingMachineModelDTO> updateVendingMachineModel(@RequestBody VendingMachineModelDTO vendingMachineModelDTO) throws URISyntaxException {
        log.debug("REST request to update VendingMachineModel : {}", vendingMachineModelDTO);
        if (vendingMachineModelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VendingMachineModelDTO result = vendingMachineModelService.save(vendingMachineModelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, vendingMachineModelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vending-machine-models} : get all the vendingMachineModels.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vendingMachineModels in body.
     */
    @GetMapping("/vending-machine-models")
    public ResponseEntity<List<VendingMachineModelDTO>> getAllVendingMachineModels(Pageable pageable) {
        log.debug("REST request to get a page of VendingMachineModels");
        Page<VendingMachineModelDTO> page = vendingMachineModelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vending-machine-models/:id} : get the "id" vendingMachineModel.
     *
     * @param id the id of the vendingMachineModelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vendingMachineModelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vending-machine-models/{id}")
    public ResponseEntity<VendingMachineModelDTO> getVendingMachineModel(@PathVariable Long id) {
        log.debug("REST request to get VendingMachineModel : {}", id);
        Optional<VendingMachineModelDTO> vendingMachineModelDTO = vendingMachineModelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vendingMachineModelDTO);
    }

    /**
     * {@code DELETE  /vending-machine-models/:id} : delete the "id" vendingMachineModel.
     *
     * @param id the id of the vendingMachineModelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vending-machine-models/{id}")
    public ResponseEntity<Void> deleteVendingMachineModel(@PathVariable Long id) {
        log.debug("REST request to delete VendingMachineModel : {}", id);
        vendingMachineModelService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
