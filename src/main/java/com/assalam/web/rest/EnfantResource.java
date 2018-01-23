package com.assalam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.assalam.domain.Enfant;
import com.assalam.domain.enumeration.Statut;
import com.assalam.service.EnfantService;
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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing Enfant.
 */
@RestController
@RequestMapping("/api")
public class EnfantResource {

  private final Logger log = LoggerFactory.getLogger(EnfantResource.class);

  private static final String ENTITY_NAME = "enfant";

  private final EnfantService enfantService;

  public EnfantResource(EnfantService enfantService) {
    this.enfantService = enfantService;
  }

  /**
   * POST /enfants : Create a new enfant.
   *
   * @param enfant
   *          the enfant to create
   * @return the ResponseEntity with status 201 (Created) and with body the new enfant, or with status 400 (Bad Request)
   *         if the enfant has already an ID
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   * @throws IOException
   */
  @PostMapping("/enfants")
  @Timed
  public ResponseEntity<Enfant> createEnfant(@RequestBody Enfant enfant) throws URISyntaxException, IOException {
    log.debug("REST request to save Enfant : {}", enfant);
    if (enfant.getId() != null) {
      return ResponseEntity.badRequest()
          .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new enfant cannot already have an ID"))
          .body(null);
    }
    Enfant result = enfantService.save(enfant);
    return ResponseEntity.created(new URI("/api/enfants/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /enfants : Updates an existing enfant.
   *
   * @param enfant
   *          the enfant to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated enfant,
   *         or with status 400 (Bad Request) if the enfant is not valid,
   *         or with status 500 (Internal Server Error) if the enfant couldn't be updated
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   * @throws IOException
   */
  @PutMapping("/enfants")
  @Timed
  public ResponseEntity<Enfant> updateEnfant(@RequestBody Enfant enfant) throws URISyntaxException, IOException {
    log.debug("REST request to update Enfant : {}", enfant);
    if (enfant.getId() == null) {
      return createEnfant(enfant);
    }
    Enfant result = enfantService.save(enfant);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, enfant.getId().toString()))
        .body(result);
  }

  /**
   * GET /enfants : get all the enfants.
   *
   * @param pageable
   *          the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of enfants in body
   */
  @GetMapping("/enfants")
  @Timed
  public ResponseEntity<List<Enfant>> getAllEnfants(@ApiParam Pageable pageable,
      @RequestParam(required = false) String familleId, @RequestParam(required = false) String statut) {
    log.debug("REST request to get a page of Enfants");
    Page<Enfant> page = null;
    List<Enfant> list = null;
    HttpHeaders headers = new HttpHeaders();
    if (familleId != null) {
      page = enfantService.findbyFamilleId(pageable, Long.valueOf(familleId));
      list = page.getContent();
    }
    else if (statut != null) {
      page = enfantService.findbyStatuts(pageable, statut);
      list = page.getContent();
    }
    else {
      list = enfantService.findAll();
    }
    if (page != null)
    {
      headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/enfants");
    }
    return new ResponseEntity<>(list, headers, HttpStatus.OK);
  }

  /**
   * GET /enfants/:id : get the "id" enfant.
   *
   * @param id
   *          the id of the enfant to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the enfant, or with status 404 (Not Found)
   */
  @GetMapping("/enfants/{id}")
  @Timed
  public ResponseEntity<Enfant> getEnfant(@PathVariable Long id) {
    log.debug("REST request to get Enfant : {}", id);
    Enfant enfant = enfantService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(enfant));
  }

  /**
   * GET /count/demandeadhesion : get count demande adhesion.
   *
   * @param pageable
   *          the pagination information
   * @param statut
   *          staut to be filtered
   * @return the ResponseEntity with status 200 (OK) and the list of enfants in body
   */
  @GetMapping("/enfants/count")
  @Timed
  public ResponseEntity<Map<String, Long>> countEnfant(
      @RequestParam(required = false) String statut) {
    log.debug("REST request to get the count of demandeadhesion filtered by statut" + statut);
    Long count;
    if (statut == null) {
      count = new Long(enfantService.findAll().size());
    }
    else {
      count = enfantService.countByStatut(statut);
    }

    Map<String, Long> countMap = new HashMap();
    countMap.put("count", count);

    return new ResponseEntity<>(countMap, HttpStatus.OK);
  }

  /**
   * GET /count/demandeadhesion : get count demande adhesion.
   *
   * @param pageable
   *          the pagination information
   * @param statut
   *          staut to be filtered
   * @return the ResponseEntity with status 200 (OK) and the list of enfants in body
   */
  @GetMapping("/enfants/search")
  @Timed
  public ResponseEntity<List<Enfant>> searchEnfant(
      @RequestParam(name = "statut", required = false) String statut,
      @RequestParam(name = "name", required = false) String name
      ) {


    if (statut == null) {
      statut = "";
    }
    if (name == null) {
      name = "";
    }

    List<Enfant> enfants = enfantService.searchByNameAndStatus(name, statut);

    return new ResponseEntity<>(enfants, HttpStatus.OK);
  }

  /**
   * DELETE /enfants/:id : delete the "id" enfant.
   *
   * @param id
   *          the id of the enfant to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/enfants/{id}")
  @Timed
  public ResponseEntity<Void> deleteEnfant(@PathVariable Long id) {
    log.debug("REST request to delete Enfant : {}", id);
    enfantService.delete(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
  }
}
