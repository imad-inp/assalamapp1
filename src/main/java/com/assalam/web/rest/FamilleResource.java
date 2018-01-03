package com.assalam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.assalam.domain.Famille;
import com.assalam.service.FamilleService;
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
 * REST controller for managing Famille.
 */
@RestController
@RequestMapping("/api")
public class FamilleResource {

  private final Logger log = LoggerFactory.getLogger(FamilleResource.class);

  private static final String ENTITY_NAME = "famille";

  private final FamilleService familleService;

  public FamilleResource(FamilleService familleService) {
    this.familleService = familleService;
  }

  /**
   * POST /familles : Create a new famille.
   * 
   * @param famille
   *          the famille to create
   * @return the ResponseEntity with status 201 (Created) and with body the new famille, or with status 400 (Bad
   *         Request) if the famille has already an ID
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   */
  @PostMapping("/familles")
  @Timed
  public ResponseEntity<Famille> createFamille(@RequestBody Famille famille) throws URISyntaxException {
    log.debug("REST request to save Famille : {}", famille);
    if (famille.getId() != null) {
      return ResponseEntity.badRequest()
          .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new famille cannot already have an ID"))
          .body(null);
    }
    Famille result = familleService.save(famille);
    return ResponseEntity.created(new URI("/api/familles/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /familles : Updates an existing famille.
   * 
   * @param famille
   *          the famille to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated famille,
   *         or with status 400 (Bad Request) if the famille is not valid,
   *         or with status 500 (Internal Server Error) if the famille couldn't be updated
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   */
  @PutMapping("/familles")
  @Timed
  public ResponseEntity<Famille> updateFamille(@RequestBody Famille famille) throws URISyntaxException {
    log.debug("REST request to update Famille : {}", famille);
    if (famille.getId() == null) {
      return createFamille(famille);
    }
    Famille result = familleService.save(famille);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, famille.getId().toString()))
        .body(result);
  }

  /**
   * GET /familles : get all the familles.
   * 
   * @param pageable
   *          the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of familles in body
   */
  @GetMapping("/familles")
  @Timed
  public ResponseEntity<List<Famille>> getAllFamilles(@ApiParam Pageable pageable,
      @RequestParam(name = "searchType", required = false) String searchType,
      @RequestParam(name = "searchValue", required = false) String searchValue) {
    log.debug("REST request to get a page of Familles");
    Page<Famille> page = null;
    if (searchType != null && searchType.equalsIgnoreCase("name")) {
      page = familleService.findByLastName(pageable, searchValue);

    }
    else if (searchType != null && searchType.equalsIgnoreCase("cin")) {
      page = familleService.findByCin(pageable, searchValue);
    }
    else {
      page = familleService.findAll(pageable);
    }

    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/familles");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * GET /familles/:id : get the "id" famille.
   * 
   * @param id
   *          the id of the famille to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the famille, or with status 404 (Not Found)
   */
  @GetMapping("/familles/{id}")
  @Timed
  public ResponseEntity<Famille> getFamille(@PathVariable Long id) {
    log.debug("REST request to get Famille : {}", id);
    Famille famille = familleService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(famille));
  }

  /**
   * DELETE /familles/:id : delete the "id" famille.
   * 
   * @param id
   *          the id of the famille to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/familles/{id}")
  @Timed
  public ResponseEntity<Void> deleteFamille(@PathVariable Long id) {
    log.debug("REST request to delete Famille : {}", id);
    familleService.delete(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
  }

  /**
   * SEARCH /_search/imads?query=:query : search for the imad corresponding
   * to the query.
   * 
   * @param query
   *          the query of the imad search
   * @param pageable
   *          the pagination information
   * @return the result of the search
   */
  @GetMapping("/_search/imads")
  @Timed
  public ResponseEntity<List<Famille>> searchFamilles(@RequestParam String query, @ApiParam Pageable pageable) {
    log.debug("REST request to search for a page of Imads for query {}", query);
    Page<Famille> page = familleService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/famille");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }
}
