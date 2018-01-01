package com.assalam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.assalam.domain.Paiement;
import com.assalam.service.PaiementService;
import com.assalam.service.sms.SmsServiceImpl;
import com.assalam.web.rest.util.HeaderUtil;
import com.assalam.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.undertow.util.Headers;
import io.github.jhipster.web.util.ResponseUtil;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Paiement.
 */
@RestController
@RequestMapping("/api")
public class PaiementResource {

    private final Logger log = LoggerFactory.getLogger(PaiementResource.class);

    private static final String ENTITY_NAME = "paiement";

    private final PaiementService paiementService;



    public PaiementResource(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    /**
     * POST  /paiements : Create a new paiement.
     *
     * @param paiement the paiement to create
     * @return the ResponseEntity with status 201 (Created) and with body the new paiement, or with status 400 (Bad Request) if the paiement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/paiements")
    @Timed
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) throws URISyntaxException {
        log.debug("REST request to save Paiement : {}", paiement);
        if (paiement.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new paiement cannot already have an ID")).body(null);
        }
    Paiement result = paiementService.save(paiement, false);
        return ResponseEntity.created(new URI("/api/paiements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /paiements : Updates an existing paiement.
     *
     * @param paiement the paiement to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated paiement,
     * or with status 400 (Bad Request) if the paiement is not valid,
     * or with status 500 (Internal Server Error) if the paiement couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/paiements")
    @Timed
    public ResponseEntity<Paiement> updatePaiement(@RequestBody Paiement paiement) throws URISyntaxException {
        log.debug("REST request to update Paiement : {}", paiement);
        if (paiement.getId() == null) {
            return createPaiement(paiement);
        }
    Paiement result = paiementService.save(paiement, true);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, paiement.getId().toString()))
            .body(result);
    }

    /**
     * GET  /paiements : get all the paiements.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of paiements in body
     */
    @GetMapping("/paiements")
    @Timed
    public ResponseEntity<List<Paiement>> getAllPaiements(@ApiParam Pageable pageable, @RequestParam(required = false) String kafalaId) {
        log.debug("REST request to get a page of Paiements");
        Page<Paiement> page = null;
    List<Paiement> paiements = null;

        if(kafalaId == null){
      paiements = paiementService.findAll(pageable).getContent();

        }
        else {
      paiements = paiementService.findByKafalaId(kafalaId);
        }


    return new ResponseEntity<>(paiements, HttpStatus.OK);
    }

    /**
   * GET /paiements/csv : get all the paiements.
   * 
   * @param pageable
   *          the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of paiements in body
   */
  @PostMapping("/paiements/pdf")
  @Timed
  public ResponseEntity<byte[]> getPaiementReceipt(@RequestBody Paiement paiement) {
    log.debug("REST request to get the receipt of the payment");
    byte[] document = null;



    document = paiementService.getPaiementReceipt(paiement);


    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType("application/pdf"));

    headers.setContentDispositionFormData("imad", "imad.pdf");
    return new ResponseEntity<>(document, headers, HttpStatus.OK);
  }

  /**
   * GET /paiements/:id : get the "id" paiement.
   * 
   * @param id
   *          the id of the paiement to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the paiement, or with status 404 (Not Found)
   */
    @GetMapping("/paiements/{id}")
    @Timed
    public ResponseEntity<Paiement> getPaiement(@PathVariable Long id) {
        log.debug("REST request to get Paiement : {}", id);
        Paiement paiement = paiementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(paiement));
    }

    /**
     * DELETE  /paiements/:id : delete the "id" paiement.
     *
     * @param id the id of the paiement to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/paiements/{id}")
    @Timed
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        log.debug("REST request to delete Paiement : {}", id);
        paiementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
