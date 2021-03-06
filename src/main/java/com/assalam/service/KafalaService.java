package com.assalam.service;

import java.util.List;

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

  /**
   * Count the number of late kafalas
   * 
   * @return
   */
  Integer countLateKafalas();

  Page<Kafala> findByKafilId(Pageable pageable, String kafilId);

  Page<Kafala> findByEnfantId(Pageable pageable, String enfantId);

  List<Kafala> findLateKafalas();

  List<Kafala> findByState(String searchValue);

  List<Kafala> findByStartYear(String searchValue);





  List<Kafala> findByStartYearAndState(String datedebut, String state);

  List<Kafala> findByEndYearAndState(String datefin, String state);
}
