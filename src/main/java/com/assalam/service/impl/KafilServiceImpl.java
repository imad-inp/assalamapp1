package com.assalam.service.impl;

import com.assalam.service.KafilService;
import com.assalam.domain.Kafil;
import com.assalam.repository.KafilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Kafil.
 */
@Service
@Transactional
public class KafilServiceImpl implements KafilService{

    private final Logger log = LoggerFactory.getLogger(KafilServiceImpl.class);

    private final KafilRepository kafilRepository;

    public KafilServiceImpl(KafilRepository kafilRepository) {
        this.kafilRepository = kafilRepository;
    }

    /**
     * Save a kafil.
     *
     * @param kafil the entity to save
     * @return the persisted entity
     */
    @Override
    public Kafil save(Kafil kafil) {
        log.debug("Request to save Kafil : {}", kafil);
        return kafilRepository.save(kafil);
    }

    /**
     *  Get all the kafils.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Kafil> findAll(Pageable pageable) {
        log.debug("Request to get all Kafils");
        return kafilRepository.findAll(pageable);
    }

    /**
     *  Get one kafil by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Kafil findOne(Long id) {
        log.debug("Request to get Kafil : {}", id);
        return kafilRepository.findOne(id);
    }

    /**
     *  Delete the  kafil by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Kafil : {}", id);
        kafilRepository.delete(id);
    }
}
