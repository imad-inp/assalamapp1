package com.assalam.service;

import com.assalam.domain.Kafala;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Kafala.
 */
public interface KafalaService {

    /**
     * Save a kafala.
     *
     * @param kafala the entity to save
     * @return the persisted entity
     */
    Kafala save(Kafala kafala);

    /**
     *  Get all the kafalas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Kafala> findAll(Pageable pageable);

    /**
     *  Get the "id" kafala.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Kafala findOne(Long id);

    /**
     *  Delete the "id" kafala.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
