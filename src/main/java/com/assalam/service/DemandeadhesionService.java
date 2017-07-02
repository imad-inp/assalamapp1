package com.assalam.service;

import com.assalam.domain.Demandeadhesion;
import java.util.List;

/**
 * Service Interface for managing Demandeadhesion.
 */
public interface DemandeadhesionService {

    /**
     * Save a demandeadhesion.
     *
     * @param demandeadhesion the entity to save
     * @return the persisted entity
     */
    Demandeadhesion save(Demandeadhesion demandeadhesion);

    /**
     *  Get all the demandeadhesions.
     *
     *  @return the list of entities
     */
    List<Demandeadhesion> findAll();

    /**
     *  Get the "id" demandeadhesion.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Demandeadhesion findOne(Long id);

    /**
     *  Delete the "id" demandeadhesion.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
