package com.assalam.service.impl;

import com.assalam.service.FamilleService;
import com.assalam.domain.Famille;
import com.assalam.repository.FamilleRepository;
import com.assalam.repository.search.FamilleSearchRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Famille.
 */
@Service
@Transactional
public class FamilleServiceImpl implements FamilleService {

  private final Logger log = LoggerFactory.getLogger(FamilleServiceImpl.class);

  private final FamilleRepository familleRepository;

  private final FamilleSearchRepository familleSearchRepository;

  public FamilleServiceImpl(FamilleRepository familleRepository, FamilleSearchRepository familleSearchRepository) {
    this.familleRepository = familleRepository;
    this.familleSearchRepository = familleSearchRepository;

  }

  /**
   * Save a famille.
   * 
   * @param famille
   *          the entity to save
   * @return the persisted entity
   */
  @Override
  public Famille save(Famille famille) {
    log.debug("Request to save Famille : {}", famille);
    Famille result = familleRepository.save(famille);
    familleSearchRepository.save(result);
    return result;
  }

  /**
   * Get all the familles.
   * 
   * @param pageable
   *          the pagination information
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public Page<Famille> findAll(Pageable pageable) {
    log.debug("Request to get all Familles");
    return familleRepository.findAll(pageable);
  }

  /**
   * Get one famille by id.
   * 
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Famille findOne(Long id) {
    log.debug("Request to get Famille : {}", id);
    return familleRepository.findOne(id);
  }

  /**
   * Delete the famille by id.
   * 
   * @param id
   *          the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete Famille : {}", id);
    familleRepository.delete(id);
  }

  @Override
  public Page<Famille> findByLastName(Pageable pageable, String searchValue) {
    return familleRepository.findByPereContainingOrMereContaining(pageable, searchValue, searchValue);

  }

  @Override
  public Page<Famille> findByCin(Pageable pageable, String searchValue) {

    return familleRepository.findByCinPereContainingOrCinMereContaining(pageable, searchValue, searchValue);

  }

  /**
   * Search for the imad corresponding to the query.
   * 
   * @param query
   *          the query of the search
   * @param pageable
   *          the pagination information
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public Page<Famille> search(String query, Pageable pageable) {
    log.debug("Request to search for a page of Imads for query {}", query);
    Page<Famille> result = familleSearchRepository.search(queryStringQuery(query), pageable);
    return result;

  }
}
