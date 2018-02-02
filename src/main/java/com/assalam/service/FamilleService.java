package com.assalam.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.assalam.domain.Famille;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Famille.
 */
public interface FamilleService {

    /**
   * Save a famille.
   * 
   * @param famille
   *          the entity to save
   * @return the persisted entity
   * @throws IOException
   * @throws FileNotFoundException
   */
  Famille save(Famille famille) throws FileNotFoundException, IOException;

    /**
     *  Get all the familles.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Famille> findAll(Pageable pageable);

    /**
     *  Get the "id" famille.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Famille findOne(Long id);

    /**
   * Get the famille by state
   * 
   * @param state
   *          state of the family
   * @return the entity
   */
  Page<Famille> findByState(Pageable pageable, String state);

  /**
   * Delete the "id" famille.
   * 
   * @param id
   *          the id of the entity
   */
    void delete(Long id);

  Page<Famille> findByLastName(Pageable pageable, String searchValue);

  Page<Famille> findByCin(Pageable pageable, String searchValue);
}
