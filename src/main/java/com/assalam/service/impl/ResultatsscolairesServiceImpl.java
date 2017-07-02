package com.assalam.service.impl;

import com.assalam.service.ResultatsscolairesService;
import com.assalam.domain.Resultatsscolaires;
import com.assalam.repository.ResultatsscolairesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Resultatsscolaires.
 */
@Service
@Transactional
public class ResultatsscolairesServiceImpl implements ResultatsscolairesService{

    private final Logger log = LoggerFactory.getLogger(ResultatsscolairesServiceImpl.class);

    private final ResultatsscolairesRepository resultatsscolairesRepository;

    public ResultatsscolairesServiceImpl(ResultatsscolairesRepository resultatsscolairesRepository) {
        this.resultatsscolairesRepository = resultatsscolairesRepository;
    }

    /**
     * Save a resultatsscolaires.
     *
     * @param resultatsscolaires the entity to save
     * @return the persisted entity
     */
    @Override
    public Resultatsscolaires save(Resultatsscolaires resultatsscolaires) {
        log.debug("Request to save Resultatsscolaires : {}", resultatsscolaires);
        return resultatsscolairesRepository.save(resultatsscolaires);
    }

    /**
     *  Get all the resultatsscolaires.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Resultatsscolaires> findAll(Pageable pageable) {
        log.debug("Request to get all Resultatsscolaires");
        return resultatsscolairesRepository.findAll(pageable);
    }

    /**
     *  Get one resultatsscolaires by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Resultatsscolaires findOne(Long id) {
        log.debug("Request to get Resultatsscolaires : {}", id);
        return resultatsscolairesRepository.findOne(id);
    }

    /**
     *  Delete the  resultatsscolaires by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Resultatsscolaires : {}", id);
        resultatsscolairesRepository.delete(id);
    }
}
