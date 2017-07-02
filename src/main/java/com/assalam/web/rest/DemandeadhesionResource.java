package com.assalam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.assalam.domain.Demandeadhesion;
import com.assalam.service.DemandeadhesionService;
import com.assalam.web.rest.util.HeaderUtil;
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
 * REST controller for managing Demandeadhesion.
 */
@RestController
@RequestMapping("/api")
public class DemandeadhesionResource {

    private final Logger log = LoggerFactory.getLogger(DemandeadhesionResource.class);

    private static final String ENTITY_NAME = "demandeadhesion";

    private final DemandeadhesionService demandeadhesionService;

    public DemandeadhesionResource(DemandeadhesionService demandeadhesionService) {
        this.demandeadhesionService = demandeadhesionService;
    }

    /**
     * POST  /demandeadhesions : Create a new demandeadhesion.
     *
     * @param demandeadhesion the demandeadhesion to create
     * @return the ResponseEntity with status 201 (Created) and with body the new demandeadhesion, or with status 400 (Bad Request) if the demandeadhesion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/demandeadhesions")
    @Timed
    public ResponseEntity<Demandeadhesion> createDemandeadhesion(@RequestBody Demandeadhesion demandeadhesion) throws URISyntaxException {
        log.debug("REST request to save Demandeadhesion : {}", demandeadhesion);
        if (demandeadhesion.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new demandeadhesion cannot already have an ID")).body(null);
        }
        Demandeadhesion result = demandeadhesionService.save(demandeadhesion);
        return ResponseEntity.created(new URI("/api/demandeadhesions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /demandeadhesions : Updates an existing demandeadhesion.
     *
     * @param demandeadhesion the demandeadhesion to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated demandeadhesion,
     * or with status 400 (Bad Request) if the demandeadhesion is not valid,
     * or with status 500 (Internal Server Error) if the demandeadhesion couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/demandeadhesions")
    @Timed
    public ResponseEntity<Demandeadhesion> updateDemandeadhesion(@RequestBody Demandeadhesion demandeadhesion) throws URISyntaxException {
        log.debug("REST request to update Demandeadhesion : {}", demandeadhesion);
        if (demandeadhesion.getId() == null) {
            return createDemandeadhesion(demandeadhesion);
        }
        Demandeadhesion result = demandeadhesionService.save(demandeadhesion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, demandeadhesion.getId().toString()))
            .body(result);
    }

    /**
     * GET  /demandeadhesions : get all the demandeadhesions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of demandeadhesions in body
     */
    @GetMapping("/demandeadhesions")
    @Timed
    public List<Demandeadhesion> getAllDemandeadhesions() {
        log.debug("REST request to get all Demandeadhesions");
        return demandeadhesionService.findAll();
    }

    /**
     * GET  /demandeadhesions/:id : get the "id" demandeadhesion.
     *
     * @param id the id of the demandeadhesion to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the demandeadhesion, or with status 404 (Not Found)
     */
    @GetMapping("/demandeadhesions/{id}")
    @Timed
    public ResponseEntity<Demandeadhesion> getDemandeadhesion(@PathVariable Long id) {
        log.debug("REST request to get Demandeadhesion : {}", id);
        Demandeadhesion demandeadhesion = demandeadhesionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(demandeadhesion));
    }

    /**
     * DELETE  /demandeadhesions/:id : delete the "id" demandeadhesion.
     *
     * @param id the id of the demandeadhesion to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/demandeadhesions/{id}")
    @Timed
    public ResponseEntity<Void> deleteDemandeadhesion(@PathVariable Long id) {
        log.debug("REST request to delete Demandeadhesion : {}", id);
        demandeadhesionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
