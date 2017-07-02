package com.assalam.service;

import com.assalam.domain.Kafil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Kafil.
 */
public interface KafilService {

    /**
     * Save a kafil.
     *
     * @param kafil the entity to save
     * @return the persisted entity
     */
    Kafil save(Kafil kafil);

    /**
     *  Get all the kafils.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Kafil> findAll(Pageable pageable);

    /**
     *  Get the "id" kafil.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Kafil findOne(Long id);

    /**
     *  Delete the "id" kafil.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
