package com.covit.web.rest;
import com.covit.domain.Cellar;
import com.covit.repository.CellarRepository;
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
 * REST controller for managing Cellar.
 */
@RestController
@RequestMapping("/api")
public class CellarResource {

    private final Logger log = LoggerFactory.getLogger(CellarResource.class);

    private static final String ENTITY_NAME = "covitCellar";

    private final CellarRepository cellarRepository;

    public CellarResource(CellarRepository cellarRepository) {
        this.cellarRepository = cellarRepository;
    }

    /**
     * POST  /cellars : Create a new cellar.
     *
     * @param cellar the cellar to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cellar, or with status 400 (Bad Request) if the cellar has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/cellars")
    public ResponseEntity<Cellar> createCellar(@RequestBody Cellar cellar) throws URISyntaxException {
        log.debug("REST request to save Cellar : {}", cellar);
        if (cellar.getId() != null) {
            throw new BadRequestAlertException("A new cellar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cellar result = cellarRepository.save(cellar);
        return ResponseEntity.created(new URI("/api/cellars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cellars : Updates an existing cellar.
     *
     * @param cellar the cellar to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cellar,
     * or with status 400 (Bad Request) if the cellar is not valid,
     * or with status 500 (Internal Server Error) if the cellar couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/cellars")
    public ResponseEntity<Cellar> updateCellar(@RequestBody Cellar cellar) throws URISyntaxException {
        log.debug("REST request to update Cellar : {}", cellar);
        if (cellar.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Cellar result = cellarRepository.save(cellar);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cellar.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cellars : get all the cellars.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cellars in body
     */
    @GetMapping("/cellars")
    public List<Cellar> getAllCellars() {
        log.debug("REST request to get all Cellars");
        return cellarRepository.findAll();
    }

    /**
     * GET  /cellars/:id : get the "id" cellar.
     *
     * @param id the id of the cellar to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cellar, or with status 404 (Not Found)
     */
    @GetMapping("/cellars/{id}")
    public ResponseEntity<Cellar> getCellar(@PathVariable Long id) {
        log.debug("REST request to get Cellar : {}", id);
        Optional<Cellar> cellar = cellarRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cellar);
    }

    /**
     * DELETE  /cellars/:id : delete the "id" cellar.
     *
     * @param id the id of the cellar to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/cellars/{id}")
    public ResponseEntity<Void> deleteCellar(@PathVariable Long id) {
        log.debug("REST request to delete Cellar : {}", id);
        cellarRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
