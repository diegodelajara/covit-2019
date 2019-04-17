package com.covit.web.rest;
import com.covit.domain.PenaltyFeeType;
import com.covit.repository.PenaltyFeeTypeRepository;
import com.covit.web.rest.errors.BadRequestAlertException;
import com.covit.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PenaltyFeeType.
 */
@RestController
@RequestMapping("/api")
public class PenaltyFeeTypeResource {

    private final Logger log = LoggerFactory.getLogger(PenaltyFeeTypeResource.class);

    private static final String ENTITY_NAME = "covitPenaltyFeeType";

    private final PenaltyFeeTypeRepository penaltyFeeTypeRepository;

    public PenaltyFeeTypeResource(PenaltyFeeTypeRepository penaltyFeeTypeRepository) {
        this.penaltyFeeTypeRepository = penaltyFeeTypeRepository;
    }

    /**
     * POST  /penalty-fee-types : Create a new penaltyFeeType.
     *
     * @param penaltyFeeType the penaltyFeeType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new penaltyFeeType, or with status 400 (Bad Request) if the penaltyFeeType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/penalty-fee-types")
    public ResponseEntity<PenaltyFeeType> createPenaltyFeeType(@RequestBody PenaltyFeeType penaltyFeeType) throws URISyntaxException {
        log.debug("REST request to save PenaltyFeeType : {}", penaltyFeeType);
        if (penaltyFeeType.getId() != null) {
            throw new BadRequestAlertException("A new penaltyFeeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PenaltyFeeType result = penaltyFeeTypeRepository.save(penaltyFeeType);
        return ResponseEntity.created(new URI("/api/penalty-fee-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /penalty-fee-types : Updates an existing penaltyFeeType.
     *
     * @param penaltyFeeType the penaltyFeeType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated penaltyFeeType,
     * or with status 400 (Bad Request) if the penaltyFeeType is not valid,
     * or with status 500 (Internal Server Error) if the penaltyFeeType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/penalty-fee-types")
    public ResponseEntity<PenaltyFeeType> updatePenaltyFeeType(@RequestBody PenaltyFeeType penaltyFeeType) throws URISyntaxException {
        log.debug("REST request to update PenaltyFeeType : {}", penaltyFeeType);
        if (penaltyFeeType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PenaltyFeeType result = penaltyFeeTypeRepository.save(penaltyFeeType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, penaltyFeeType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /penalty-fee-types : get all the penaltyFeeTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of penaltyFeeTypes in body
     */
    @GetMapping("/penalty-fee-types")
    public List<PenaltyFeeType> getAllPenaltyFeeTypes() {
        log.debug("REST request to get all PenaltyFeeTypes");
        return penaltyFeeTypeRepository.findAll();
    }

    /**
     * GET  /penalty-fee-types/:id : get the "id" penaltyFeeType.
     *
     * @param id the id of the penaltyFeeType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the penaltyFeeType, or with status 404 (Not Found)
     */
    @GetMapping("/penalty-fee-types/{id}")
    public ResponseEntity<PenaltyFeeType> getPenaltyFeeType(@PathVariable Long id) {
        log.debug("REST request to get PenaltyFeeType : {}", id);
        Optional<PenaltyFeeType> penaltyFeeType = penaltyFeeTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(penaltyFeeType);
    }

    /**
     * DELETE  /penalty-fee-types/:id : delete the "id" penaltyFeeType.
     *
     * @param id the id of the penaltyFeeType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/penalty-fee-types/{id}")
    public ResponseEntity<Void> deletePenaltyFeeType(@PathVariable Long id) {
        log.debug("REST request to delete PenaltyFeeType : {}", id);
        penaltyFeeTypeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
