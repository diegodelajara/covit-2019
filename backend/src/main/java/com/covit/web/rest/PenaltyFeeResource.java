package com.covit.web.rest;
import com.covit.domain.PenaltyFee;
import com.covit.repository.PenaltyFeeRepository;
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
 * REST controller for managing PenaltyFee.
 */
@RestController
@RequestMapping("/api")
public class PenaltyFeeResource {

    private final Logger log = LoggerFactory.getLogger(PenaltyFeeResource.class);

    private static final String ENTITY_NAME = "covitPenaltyFee";

    private final PenaltyFeeRepository penaltyFeeRepository;

    public PenaltyFeeResource(PenaltyFeeRepository penaltyFeeRepository) {
        this.penaltyFeeRepository = penaltyFeeRepository;
    }

    /**
     * POST  /penalty-fees : Create a new penaltyFee.
     *
     * @param penaltyFee the penaltyFee to create
     * @return the ResponseEntity with status 201 (Created) and with body the new penaltyFee, or with status 400 (Bad Request) if the penaltyFee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/penalty-fees")
    public ResponseEntity<PenaltyFee> createPenaltyFee(@RequestBody PenaltyFee penaltyFee) throws URISyntaxException {
        log.debug("REST request to save PenaltyFee : {}", penaltyFee);
        if (penaltyFee.getId() != null) {
            throw new BadRequestAlertException("A new penaltyFee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PenaltyFee result = penaltyFeeRepository.save(penaltyFee);
        return ResponseEntity.created(new URI("/api/penalty-fees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /penalty-fees : Updates an existing penaltyFee.
     *
     * @param penaltyFee the penaltyFee to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated penaltyFee,
     * or with status 400 (Bad Request) if the penaltyFee is not valid,
     * or with status 500 (Internal Server Error) if the penaltyFee couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/penalty-fees")
    public ResponseEntity<PenaltyFee> updatePenaltyFee(@RequestBody PenaltyFee penaltyFee) throws URISyntaxException {
        log.debug("REST request to update PenaltyFee : {}", penaltyFee);
        if (penaltyFee.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PenaltyFee result = penaltyFeeRepository.save(penaltyFee);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, penaltyFee.getId().toString()))
            .body(result);
    }

    /**
     * GET  /penalty-fees : get all the penaltyFees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of penaltyFees in body
     */
    @GetMapping("/penalty-fees")
    public List<PenaltyFee> getAllPenaltyFees() {
        log.debug("REST request to get all PenaltyFees");
        return penaltyFeeRepository.findAll();
    }

    /**
     * GET  /penalty-fees/:id : get the "id" penaltyFee.
     *
     * @param id the id of the penaltyFee to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the penaltyFee, or with status 404 (Not Found)
     */
    @GetMapping("/penalty-fees/{id}")
    public ResponseEntity<PenaltyFee> getPenaltyFee(@PathVariable Long id) {
        log.debug("REST request to get PenaltyFee : {}", id);
        Optional<PenaltyFee> penaltyFee = penaltyFeeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(penaltyFee);
    }

    /**
     * DELETE  /penalty-fees/:id : delete the "id" penaltyFee.
     *
     * @param id the id of the penaltyFee to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/penalty-fees/{id}")
    public ResponseEntity<Void> deletePenaltyFee(@PathVariable Long id) {
        log.debug("REST request to delete PenaltyFee : {}", id);
        penaltyFeeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
