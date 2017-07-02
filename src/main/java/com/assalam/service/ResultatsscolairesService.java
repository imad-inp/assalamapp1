package com.assalam.service;

import com.assalam.domain.Resultatsscolaires;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Resultatsscolaires.
 */
public interface ResultatsscolairesService {

    /**
     * Save a resultatsscolaires.
     *
     * @param resultatsscolaires the entity to save
     * @return the persisted entity
     */
    Resultatsscolaires save(Resultatsscolaires resultatsscolaires);

    /**
     *  Get all the resultatsscolaires.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Resultatsscolaires> findAll(Pageable pageable);

    /**
     *  Get the "id" resultatsscolaires.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Resultatsscolaires findOne(Long id);

    /**
     *  Delete the "id" resultatsscolaires.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
