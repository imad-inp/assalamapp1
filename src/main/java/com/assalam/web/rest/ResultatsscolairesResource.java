package com.assalam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.assalam.domain.Resultatsscolaires;
import com.assalam.service.ResultatsscolairesService;
import com.assalam.web.rest.util.HeaderUtil;
import com.assalam.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Resultatsscolaires.
 */
@RestController
@RequestMapping("/api")
public class ResultatsscolairesResource {

    private final Logger log = LoggerFactory.getLogger(ResultatsscolairesResource.class);

    private static final String ENTITY_NAME = "resultatsscolaires";

    private final ResultatsscolairesService resultatsscolairesService;

    public ResultatsscolairesResource(ResultatsscolairesService resultatsscolairesService) {
        this.resultatsscolairesService = resultatsscolairesService;
    }

    /**
     * POST  /resultatsscolaires : Create a new resultatsscolaires.
     *
     * @param resultatsscolaires the resultatsscolaires to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resultatsscolaires, or with status 400 (Bad Request) if the resultatsscolaires has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resultatsscolaires")
    @Timed
    public ResponseEntity<Resultatsscolaires> createResultatsscolaires(@RequestBody Resultatsscolaires resultatsscolaires) throws URISyntaxException {
        log.debug("REST request to save Resultatsscolaires : {}", resultatsscolaires);
        if (resultatsscolaires.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new resultatsscolaires cannot already have an ID")).body(null);
        }
        Resultatsscolaires result = resultatsscolairesService.save(resultatsscolaires);
        return ResponseEntity.created(new URI("/api/resultatsscolaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resultatsscolaires : Updates an existing resultatsscolaires.
     *
     * @param resultatsscolaires the resultatsscolaires to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resultatsscolaires,
     * or with status 400 (Bad Request) if the resultatsscolaires is not valid,
     * or with status 500 (Internal Server Error) if the resultatsscolaires couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resultatsscolaires")
    @Timed
    public ResponseEntity<Resultatsscolaires> updateResultatsscolaires(@RequestBody Resultatsscolaires resultatsscolaires) throws URISyntaxException {
        log.debug("REST request to update Resultatsscolaires : {}", resultatsscolaires);
        if (resultatsscolaires.getId() == null) {
            return createResultatsscolaires(resultatsscolaires);
        }
        Resultatsscolaires result = resultatsscolairesService.save(resultatsscolaires);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resultatsscolaires.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resultatsscolaires : get all the resultatsscolaires.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of resultatsscolaires in body
     */
    @GetMapping("/resultatsscolaires")
    @Timed
    public ResponseEntity<List<Resultatsscolaires>> getAllResultatsscolaires(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Resultatsscolaires");
        Page<Resultatsscolaires> page = resultatsscolairesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/resultatsscolaires");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /resultatsscolaires/:id : get the "id" resultatsscolaires.
     *
     * @param id the id of the resultatsscolaires to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resultatsscolaires, or with status 404 (Not Found)
     */
    @GetMapping("/resultatsscolaires/{id}")
    @Timed
    public ResponseEntity<Resultatsscolaires> getResultatsscolaires(@PathVariable Long id) {
        log.debug("REST request to get Resultatsscolaires : {}", id);
        Resultatsscolaires resultatsscolaires = resultatsscolairesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(resultatsscolaires));
    }

    /**
     * DELETE  /resultatsscolaires/:id : delete the "id" resultatsscolaires.
     *
     * @param id the id of the resultatsscolaires to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resultatsscolaires/{id}")
    @Timed
    public ResponseEntity<Void> deleteResultatsscolaires(@PathVariable Long id) {
        log.debug("REST request to delete Resultatsscolaires : {}", id);
        resultatsscolairesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
