package com.assalam.service;

import java.io.FileNotFoundException;
import java.io.IOException;
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
   * @param kafala
   *          the entity to save
   * @return the persisted entity
   * @throws IOException
   * @throws FileNotFoundException
   */
  Kafala save(Kafala kafala) throws FileNotFoundException, IOException;

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
   * @param kafalaState
   * 
   * @return
   */
  Integer countLateKafalas(String kafalaState);

  Page<Kafala> findByKafilId(Pageable pageable, String kafilId);

  Page<Kafala> findByEnfantId(Pageable pageable, String enfantId);

  List<Kafala> findLateKafalas(String kafalaState);

  List<Kafala> findByState(String searchValue);

  List<Kafala> findByStartYear(String searchValue);





  List<Kafala> findByStartYearAndState(String datedebut, String state);

  List<Kafala> findByEndYearAndState(String datefin, String state);
}
