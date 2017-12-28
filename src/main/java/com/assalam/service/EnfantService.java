package com.assalam.service;

import java.util.List;

import com.assalam.domain.Enfant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

/**
 * Service Interface for managing Enfant.
 */
public interface EnfantService {

    /**
     * Save a enfant.
     *
     * @param enfant the entity to save
     * @return the persisted entity
     */
    Enfant save(Enfant enfant);

    /**
     *  Get all the enfants.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Enfant> findAll(Pageable pageable);

    /**
     *  Get the "id" enfant.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Enfant findOne(Long id);

    /**
     *  Delete the "id" enfant.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

	         /**
   * Get the enfant by famille id.
   * 
   * @param id
   *          of the familly that the child belogns to
   * @return the e
   **/
     public Page<Enfant> findbyFamilleId(Pageable pageable, Long familleId);

  List<Enfant> findAll();

  List<Enfant> findbyStatuts(List<String> statuts);

  Page<Enfant> findbyStatuts(Pageable pageable, List<String> statuts);
}
