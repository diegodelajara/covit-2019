package com.covit.web.rest;
import com.covit.domain.Egress;
import com.covit.repository.EgressRepository;
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
 * REST controller for managing Egress.
 */
@RestController
@RequestMapping("/api")
public class EgressResource {

    private final Logger log = LoggerFactory.getLogger(EgressResource.class);

    private static final String ENTITY_NAME = "covitEgress";

    private final EgressRepository egressRepository;

    public EgressResource(EgressRepository egressRepository) {
        this.egressRepository = egressRepository;
    }

    /**
     * POST  /egresses : Create a new egress.
     *
     * @param egress the egress to create
     * @return the ResponseEntity with status 201 (Created) and with body the new egress, or with status 400 (Bad Request) if the egress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/egresses")
    public ResponseEntity<Egress> createEgress(@RequestBody Egress egress) throws URISyntaxException {
        log.debug("REST request to save Egress : {}", egress);
        if (egress.getId() != null) {
            throw new BadRequestAlertException("A new egress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Egress result = egressRepository.save(egress);
        return ResponseEntity.created(new URI("/api/egresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /egresses : Updates an existing egress.
     *
     * @param egress the egress to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated egress,
     * or with status 400 (Bad Request) if the egress is not valid,
     * or with status 500 (Internal Server Error) if the egress couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/egresses")
    public ResponseEntity<Egress> updateEgress(@RequestBody Egress egress) throws URISyntaxException {
        log.debug("REST request to update Egress : {}", egress);
        if (egress.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Egress result = egressRepository.save(egress);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, egress.getId().toString()))
            .body(result);
    }

    /**
     * GET  /egresses : get all the egresses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of egresses in body
     */
    @GetMapping("/egresses")
    public List<Egress> getAllEgresses() {
        log.debug("REST request to get all Egresses");
        return egressRepository.findAll();
    }

    /**
     * GET  /egresses/:id : get the "id" egress.
     *
     * @param id the id of the egress to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the egress, or with status 404 (Not Found)
     */
    @GetMapping("/egresses/{id}")
    public ResponseEntity<Egress> getEgress(@PathVariable Long id) {
        log.debug("REST request to get Egress : {}", id);
        Optional<Egress> egress = egressRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(egress);
    }

    /**
     * DELETE  /egresses/:id : delete the "id" egress.
     *
     * @param id the id of the egress to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/egresses/{id}")
    public ResponseEntity<Void> deleteEgress(@PathVariable Long id) {
        log.debug("REST request to delete Egress : {}", id);
        egressRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
