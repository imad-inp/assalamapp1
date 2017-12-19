package com.assalam.service.impl;

import com.assalam.service.EnfantService;
import com.assalam.domain.Enfant;
import com.assalam.repository.EnfantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Service Implementation for managing Enfant.
 */
@Service
@Transactional
public class EnfantServiceImpl implements EnfantService{

    private final Logger log = LoggerFactory.getLogger(EnfantServiceImpl.class);

    private final EnfantRepository enfantRepository;

    public EnfantServiceImpl(EnfantRepository enfantRepository) {
        this.enfantRepository = enfantRepository;
    }

    /**
     * Save a enfant.
     *
     * @param enfant the entity to save
     * @return the persisted entity
     */
    @Override
    public Enfant save(Enfant enfant) {
        log.debug("Request to save Enfant : {}", enfant);
        return enfantRepository.save(enfant);
    }

    /**
     *  Get all the enfants.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Enfant> findAll(Pageable pageable) {
        log.debug("Request to get all Enfants");
        return enfantRepository.findAll(pageable);
    }

    /**
     *  Get one enfant by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Enfant findOne(Long id) {
        log.debug("Request to get Enfant : {}", id);
        return enfantRepository.findOne(id);
    }
	
	  
     /**
     *  Get Enfants by famille Ids.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Enfant> findbyFamilleId(Pageable pageable, Long familleId) {
        log.debug("Request to get enfants by famille Id : {}", familleId);
        return enfantRepository.findByFamilleId(pageable, familleId);
    }

    /**
     *  Delete the  enfant by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Enfant : {}", id);
        enfantRepository.delete(id);
    }
}
