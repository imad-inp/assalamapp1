package com.assalam.service;

import java.io.IOException;

import com.assalam.domain.Files;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Files.
 */
public interface FilesService {

    /**
   * Save a Files.
   * 
   * @param Files
   *          the entity to save
   * @return the persisted entity
   */
  Files save(Files files);

  Files save(Files files, String path) throws IOException;

    /**
   * Get all the Filess.
   * 
   * @param pageable
   *          the pagination information
   * @return the list of entities
   */
  Page<Files> findAll(Pageable pageable);

    /**
   * Get the "id" Files.
   * 
   * @param id
   *          the id of the entity
   * @return the entity
   */
  Files findOne(Long id);

    /**
   * Delete the "id" Files.
   * 
   * @param id
   *          the id of the entity
   */
    void delete(Long id);
}
