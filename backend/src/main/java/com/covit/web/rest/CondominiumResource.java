package com.covit.web.rest;
import com.covit.domain.Condominium;
import com.covit.repository.CondominiumRepository;
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
 * REST controller for managing Condominium.
 */
@RestController
@RequestMapping("/api")
public class CondominiumResource {

    private final Logger log = LoggerFactory.getLogger(CondominiumResource.class);

    private static final String ENTITY_NAME = "covitCondominium";

    private final CondominiumRepository condominiumRepository;

    public CondominiumResource(CondominiumRepository condominiumRepository) {
        this.condominiumRepository = condominiumRepository;
    }

    /**
     * POST  /condominiums : Create a new condominium.
     *
     * @param condominium the condominium to create
     * @return the ResponseEntity with status 201 (Created) and with body the new condominium, or with status 400 (Bad Request) if the condominium has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/condominiums")
    public ResponseEntity<Condominium> createCondominium(@RequestBody Condominium condominium) throws URISyntaxException {
        log.debug("REST request to save Condominium : {}", condominium);
        if (condominium.getId() != null) {
            throw new BadRequestAlertException("A new condominium cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Condominium result = condominiumRepository.save(condominium);
        return ResponseEntity.created(new URI("/api/condominiums/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /condominiums : Updates an existing condominium.
     *
     * @param condominium the condominium to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated condominium,
     * or with status 400 (Bad Request) if the condominium is not valid,
     * or with status 500 (Internal Server Error) if the condominium couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/condominiums")
    public ResponseEntity<Condominium> updateCondominium(@RequestBody Condominium condominium) throws URISyntaxException {
        log.debug("REST request to update Condominium : {}", condominium);
        if (condominium.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Condominium result = condominiumRepository.save(condominium);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, condominium.getId().toString()))
            .body(result);
    }

    /**
     * GET  /condominiums : get all the condominiums.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of condominiums in body
     */
    @GetMapping("/condominiums")
    public List<Condominium> getAllCondominiums() {
        log.debug("REST request to get all Condominiums");
        return condominiumRepository.findAll();
    }

    /**
     * GET  /condominiums/:id : get the "id" condominium.
     *
     * @param id the id of the condominium to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the condominium, or with status 404 (Not Found)
     */
    @GetMapping("/condominiums/{id}")
    public ResponseEntity<Condominium> getCondominium(@PathVariable Long id) {
        log.debug("REST request to get Condominium : {}", id);
        Optional<Condominium> condominium = condominiumRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(condominium);
    }

    /**
     * DELETE  /condominiums/:id : delete the "id" condominium.
     *
     * @param id the id of the condominium to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/condominiums/{id}")
    public ResponseEntity<Void> deleteCondominium(@PathVariable Long id) {
        log.debug("REST request to delete Condominium : {}", id);
        condominiumRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
