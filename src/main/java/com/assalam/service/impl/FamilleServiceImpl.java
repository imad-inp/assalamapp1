package com.assalam.service.impl;

import com.assalam.service.FamilleService;
import com.assalam.domain.Famille;
import com.assalam.repository.FamilleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Famille.
 */
@Service
@Transactional
public class FamilleServiceImpl implements FamilleService{

    private final Logger log = LoggerFactory.getLogger(FamilleServiceImpl.class);

    private final FamilleRepository familleRepository;

    public FamilleServiceImpl(FamilleRepository familleRepository) {
        this.familleRepository = familleRepository;
    }

    /**
     * Save a famille.
     *
     * @param famille the entity to save
     * @return the persisted entity
     */
    @Override
    public Famille save(Famille famille) {
        log.debug("Request to save Famille : {}", famille);
        return familleRepository.save(famille);
    }

    /**
     *  Get all the familles.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Famille> findAll(Pageable pageable) {
        log.debug("Request to get all Familles");
        return familleRepository.findAll(pageable);
    }

    /**
     *  Get one famille by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Famille findOne(Long id) {
        log.debug("Request to get Famille : {}", id);
        return familleRepository.findOne(id);
    }

    /**
     *  Delete the  famille by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Famille : {}", id);
        familleRepository.delete(id);
    }
}
