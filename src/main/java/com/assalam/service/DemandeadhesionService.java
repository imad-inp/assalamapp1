package com.assalam.service;

import com.assalam.domain.Demandeadhesion;
import com.assalam.domain.enumeration.Statut;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Demandeadhesion.
 */
public interface DemandeadhesionService {

  /**
   * Save a demandeadhesion.
   * 
   * @param demandeadhesion
   *          the entity to save
   * @return the persisted entity
   */
  Demandeadhesion save(Demandeadhesion demandeadhesion);

  /**
   * Get all the demandeadhesions.
   * 
   * @return the list of entities
   */
  Page<Demandeadhesion> findAll(Pageable pageable);

  List<Demandeadhesion> findAll();

  /**
   * Get the "id" demandeadhesion.
   * 
   * @param id
   *          the id of the entity
   * @return the entity
   */
  Demandeadhesion findOne(Long id);

  /**
   * Delete the "id" demandeadhesion.
   * 
   * @param id
   *          the id of the entity
   */
  void delete(Long id);

  /**
   * Get the demandeadhesion by statut.
   * 
   * @param statut
   *          the statut of the entity
   * @return the e
   **/
  public Page<Demandeadhesion> findbyStatut(Pageable pageable, Statut statut);

  public List<Demandeadhesion> findbyStatut(Statut statut);

  Page<Demandeadhesion> findbyFamilleId(Pageable pageable, Long valueOf);
}
