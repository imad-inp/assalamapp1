package com.assalam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.assalam.domain.Kafala;
import com.assalam.service.KafalaService;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing Kafala.
 */
@RestController
@RequestMapping("/api")
public class KafalaResource {

  private final Logger log = LoggerFactory.getLogger(KafalaResource.class);

  private static final String ENTITY_NAME = "kafala";

  private final KafalaService kafalaService;

  public KafalaResource(KafalaService kafalaService) {
    this.kafalaService = kafalaService;
  }

  /**
   * POST /kafalas : Create a new kafala.
   *
   * @param kafala
   *          the kafala to create
   * @return the ResponseEntity with status 201 (Created) and with body the new kafala, or with status 400 (Bad Request)
   *         if the kafala has already an ID
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   * @throws IOException
   * @throws FileNotFoundException
   */
  @PostMapping("/kafalas")
  @Timed
  public ResponseEntity<Kafala> createKafala(@RequestBody Kafala kafala) throws URISyntaxException,
      FileNotFoundException, IOException {
    log.debug("REST request to save Kafala : {}", kafala);
    if (kafala.getId() != null) {
      return ResponseEntity.badRequest()
          .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new kafala cannot already have an ID"))
          .body(null);
    }
    Kafala result = kafalaService.save(kafala);
    return ResponseEntity.created(new URI("/api/kafalas/" + result.getId()))
        .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * PUT /kafalas : Updates an existing kafala.
   *
   * @param kafala
   *          the kafala to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated kafala,
   *         or with status 400 (Bad Request) if the kafala is not valid,
   *         or with status 500 (Internal Server Error) if the kafala couldn't be updated
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   * @throws IOException
   * @throws FileNotFoundException
   */
  @PutMapping("/kafalas")
  @Timed
  public ResponseEntity<Kafala> updateKafala(@RequestBody Kafala kafala) throws URISyntaxException,
      FileNotFoundException, IOException {
    log.debug("REST request to update Kafala : {}", kafala);
    if (kafala.getId() == null) {
      return createKafala(kafala);
    }
    Kafala result = kafalaService.save(kafala);
    return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, kafala.getId().toString()))
        .body(result);
  }

  /**
   * GET /kafalas : get all the kafalas.
   *
   * @param pageable
   *          the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of kafalas in body
   */
  @GetMapping("/kafalas")
  @Timed
  public ResponseEntity<List<Kafala>> getAllKafalas(@ApiParam Pageable pageable,
      @RequestParam(name = "searchType", required = false) String searchType,
      @RequestParam(name = "searchValue", required = false) String searchValue) {
    log.debug("REST request to get a page of Kafalas");
    List<Kafala> list = null;
    Page<Kafala> page = null;
    HttpHeaders headers = new HttpHeaders();
    if (searchType == null) {
      page = kafalaService.findAll(pageable);
    }
    else {
      switch (searchType) {
      case "enfantId":
        page = kafalaService.findByEnfantId(pageable, searchValue);
        break;
      case "kafilId":
        page = kafalaService.findByKafilId(pageable, searchValue);
        break;
      case "state":
        list = kafalaService.findByState(searchValue);
        break;
      case "startYear":
        list = kafalaService.findByStartYear(searchValue);
        break;
      case "startYear&state":
        String startDate = searchValue.split("&")[0];
        String state = searchValue.split("&")[1];
        list = kafalaService.findByStartYearAndState(startDate, state);
        break;
      case "endYear&state":
        String endDate = searchValue.split("&")[0];
        String state2 = searchValue.split("&")[1];
        list = kafalaService.findByEndYearAndState(endDate, state2);
        break;
      default:
        page = kafalaService.findAll(pageable);
      }
    }
    if (page != null) {
      headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/kafalas");

      list = page.getContent();
    }
    return new ResponseEntity<>(list, headers, HttpStatus.OK);
  }

  @GetMapping("/kafalas/late")
  @Timed
  public ResponseEntity<List<Kafala>> getLateKafalas() {
    log.debug("REST request to get a page of Kafalas");

    List<Kafala> kafalas = kafalaService.findLateKafalas();

    return new ResponseEntity<>(kafalas, HttpStatus.OK);
  }

  /**
   * GET /kafalas/:id : get the "id" kafala.
   *
   * @param id
   *          the id of the kafala to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the kafala, or with status 404 (Not Found)
   */
  @GetMapping("/kafalas/{id}")
  @Timed
  public ResponseEntity<Kafala> getKafala(@PathVariable Long id) {
    log.debug("REST request to get Kafala : {}", id);
    Kafala kafala = kafalaService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(kafala));
  }

  /**
   * DELETE /kafalas/:id : delete the "id" kafala.
   *
   * @param id
   *          the id of the kafala to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/kafalas/{id}")
  @Timed
  public ResponseEntity<Void> deleteKafala(@PathVariable Long id) {
    log.debug("REST request to delete Kafala : {}", id);
    kafalaService.delete(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
  }

  /**
   * GET count/kafalas : count the kafalas.
   *
   * @param pageable
   *          the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of kafalas in body
   */
  @GetMapping("/count/latekafalas")
  @Timed
  public ResponseEntity<Map<String, Integer>> countLateKafalas() {
    log.debug("REST request to count Kafalas");
    Integer count = kafalaService.countLateKafalas();
    Map<String, Integer> countMap = new HashMap();
    countMap.put("count", count);
    return new ResponseEntity<>(countMap, HttpStatus.OK);
  }

}
