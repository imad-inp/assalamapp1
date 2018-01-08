package com.assalam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.assalam.domain.Files;

import com.assalam.service.FilesService;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Files
 */
@RestController
@RequestMapping("/api")
public class FilesResource {

    private final Logger log = LoggerFactory.getLogger(FilesResource.class);

  private static final String ENTITY_NAME = "files";

  private final FilesService filesService;

  public FilesResource(FilesService filesService) {
    this.filesService = filesService;
    }

    /**
   * POST /filess : Create a new files.
   * 
   * @param files
   *          the files to create
   * @return the ResponseEntity with status 201 (Created) and with body the new files, or with status 400 (Bad Request)
   *         if the files has already an ID
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   * @throws IOException
   * @throws FileNotFoundException
   */
  @PostMapping("/files")
    @Timed
  public ResponseEntity<Files> createfiles(@RequestBody Files files) throws URISyntaxException, FileNotFoundException,
      IOException {
    log.debug("REST request to save files : {}", files);
    if (files.getId() != null) {
      return ResponseEntity.badRequest()
          .headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new files cannot already have an ID"))
          .body(null);
        }
    Files result = filesService.save(files);
    return ResponseEntity.created(new URI("/api/filess/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
   * PUT /filess : Updates an existing files.
   * 
   * @param files
   *          the files to update
   * @return the ResponseEntity with status 200 (OK) and with body the updated files,
   *         or with status 400 (Bad Request) if the files is not valid,
   *         or with status 500 (Internal Server Error) if the files couldn't be updated
   * @throws URISyntaxException
   *           if the Location URI syntax is incorrect
   * @throws IOException
   * @throws FileNotFoundException
   */
  @PutMapping("/files")
    @Timed
  public ResponseEntity<Files> updatefiles(@RequestBody Files files) throws URISyntaxException, FileNotFoundException,
      IOException {
    log.debug("REST request to update files : {}", files);
    if (files.getId() == null) {
      return createfiles(files);
        }
    Files result = filesService.save(files);
        return ResponseEntity.ok()
        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, files.getId().toString()))
            .body(result);
    }

    /**
   * GET /filess : get all the filess.
   * 
   * @param pageable
   *          the pagination information
   * @return the ResponseEntity with status 200 (OK) and the list of filess in body
   */
  @GetMapping("/files")
    @Timed
  public ResponseEntity<List<Files>> getAllfiless(@ApiParam Pageable pageable) {
    log.debug("REST request to get a page of filess");
    Page<Files> page = filesService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/filess");
    return new ResponseEntity<>(null, null, HttpStatus.OK);
    }

    /**
   * GET /filess/:id : get the "id" files.
   * 
   * @param id
   *          the id of the files to retrieve
   * @return the ResponseEntity with status 200 (OK) and with body the files, or with status 404 (Not Found)
   * @throws IOException
   */
  @GetMapping("/files/{id}")
    @Timed
  public ResponseEntity<Files> getfiles(@PathVariable Long id) throws IOException {
    log.debug("REST request to get files : {}", id);
    Files files = filesService.findOne(id);
    return ResponseUtil.wrapOrNotFound(Optional.ofNullable(files));
    }

    /**
   * DELETE /filess/:id : delete the "id" files.
   * 
   * @param id
   *          the id of the files to delete
   * @return the ResponseEntity with status 200 (OK)
   */
  @DeleteMapping("/files/{id}")
    @Timed
  public ResponseEntity<Void> deletefiles(@PathVariable Long id) {
    log.debug("REST request to delete files : {}", id);
    filesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
