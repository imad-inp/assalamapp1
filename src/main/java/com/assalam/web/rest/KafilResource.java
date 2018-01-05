package com.assalam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.assalam.domain.Kafil;
import com.assalam.service.KafilService;
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
 * REST controller for managing Kafil.
 */
@RestController
@RequestMapping("/api")
public class KafilResource {

  private final Logger log = LoggerFactory.getLogger(KafilResource.class);

  private static final String ENTITY_NAME = "kafil";

  private final KafilService kafilService;

  public KafilResource(KafilService kafilService) {
    this.kafilService = kafilService;
  }

  /**
   * POST /kafils : Create a new kafil.
   * 
   * @param kafil
   *          the kafil to create
   * @return the ResponseEntity with status 201 (Created) and with body the new kafil, or with status 400 (Bad Request)
   *         if the kafil has already an ID
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   */
  @PostMapping("/kafils")
  @Timed
  public ResponseEntity<Kafil> createKafil(@RequestBody Kafil kafil) throws URISyntaxException {
    log.debug("REST request to save Kafil : {}", kafil);
    if (kafil.getId() != null) {
      return ResponseEntity.badRequest()
          .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new kafil cannot already have an ID"))
          .body(null);
    }
    Kafil result = kafilService.save(kafil);
    return ResponseEntity.created(new URI("/api/kafils/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /kafils : Updates an existing kafil.
   * 
   * @param kafil
   *          the kafil to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated kafil,
   *         or with status 400 (Bad Request) if the kafil is not valid,
   *         or with status 500 (Internal Server Error) if the kafil couldn't be updated
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   */
  @PutMapping("/kafils")
  @Timed
  public ResponseEntity<Kafil> updateKafil(@RequestBody Kafil kafil) throws URISyntaxException {
    log.debug("REST request to update Kafil : {}", kafil);
    if (kafil.getId() == null) {
      return createKafil(kafil);
    }
    Kafil result = kafilService.save(kafil);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, kafil.getId().toString()))
        .body(result);
  }

  /**
   * GET /kafils : get all the kafils.
   * 
   * @param pageable
   *          the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of kafils in body
   */
  @GetMapping("/kafils")
  @Timed
  public ResponseEntity<List<Kafil>> getAllKafils(@ApiParam Pageable pageable,
      @RequestParam(name = "noPaging", required = false) boolean noPaging) {
    log.debug("REST request to get a page of Kafils");
    List<Kafil> result = null;
    Page<Kafil> page = null;
    HttpHeaders headers = new HttpHeaders();
    if (!noPaging) {
      page = kafilService.findAll(pageable);
      result = page.getContent();
    }
    else {
      result = kafilService.findAll();
    }
    if (page != null) {
      headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/kafils");
    }
    return new ResponseEntity<>(result, headers, HttpStatus.OK);
  }

  /**
   * GET /kafils : get all the kafils.
   * 
   * @param pageable
   *          the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of kafils in body
   */
  @GetMapping("/kafils/search")
  @Timed
  public ResponseEntity<List<Kafil>> searchKafils(@ApiParam Pageable pageable,
      @RequestParam(name = "searchType", required = false) String searchType,
      @RequestParam(name = "searchValue", required = false) String searchValue) {
    log.debug("REST request to search Kafils");
    Page<Kafil> page = null;
    if (searchType != null && searchType.equalsIgnoreCase("name")) {
      page = kafilService.findByLastName(pageable, searchValue);

    }
    else {
      page = kafilService.findAll(pageable);
    }

    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/kafils");
    return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
  }

  /**
   * GET /kafils/:id : get the "id" kafil.
   * 
   * @param id
   *          the id of the kafil to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the kafil, or with status 404 (Not Found)
   */
  @GetMapping("/kafils/{id}")
  @Timed
  public ResponseEntity<Kafil> getKafil(@PathVariable Long id) {
    log.debug("REST request to get Kafil : {}", id);
    Kafil kafil = kafilService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(kafil));
  }

  /**
   * DELETE /kafils/:id : delete the "id" kafil.
   * 
   * @param id
   *          the id of the kafil to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/kafils/{id}")
  @Timed
  public ResponseEntity<Void> deleteKafil(@PathVariable Long id) {
    log.debug("REST request to delete Kafil : {}", id);
    kafilService.delete(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
  }
}
