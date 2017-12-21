package com.assalam.service;

import com.assalam.domain.Demandeadhesion;
import com.assalam.domain.Paiement;
import com.assalam.domain.enumeration.Statut;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Interface for managing Paiement.
 */
public interface PaiementService {

    /**
     * Save a paiement.
     *
     * @param paiement the entity to save
     * @return the persisted entity
     */
    Paiement save(Paiement paiement);

    /**
     *  Get all the paiements.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Paiement> findAll(Pageable pageable);

    /**
   * Get one demandeadhesion by statut.
   * 
   * @param id
   *          the id of the entity
   * @return the entity
   */

  public Page<Paiement> findByKafalaId(Pageable pageable, String kafalaId);

  /**
   * Get the "id" paiement.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
    Paiement findOne(Long id);

    /**
     *  Delete the "id" paiement.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
