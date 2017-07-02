package com.assalam.service.impl;

import com.assalam.domain.Kafala;
import com.assalam.service.PaiementService;
import com.assalam.domain.Paiement;
import com.assalam.repository.KafalaRepository;
import com.assalam.repository.PaiementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Paiement.
 */
@Service
@Transactional
public class PaiementServiceImpl implements PaiementService{

    private final Logger log = LoggerFactory.getLogger(PaiementServiceImpl.class);

    private final PaiementRepository paiementRepository;
    @Autowired
    private KafalaRepository kafalaRepository;

    public PaiementServiceImpl(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    /**
     * Save a paiement.
     *
     * @param paiement the entity to save
     * @return the persisted entity
     */
    @Override
    public Paiement save(Paiement paiement) {
        
        log.debug("Request to save Paiement : {}", paiement);
        Paiement paiementToReturn = paiementRepository.save(paiement);
        Kafala kafala = kafalaRepository.findOne(paiement.getKafala().getId());
        
        kafala.setMoispayes(kafala.getMoispayes() + paiement.getMoispayes());
        return paiementToReturn;
    }

    /**
     *  Get all the paiements.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Paiement> findAll(Pageable pageable) {
        log.debug("Request to get all Paiements");
        return paiementRepository.findAll(pageable);
    }

    /**
     *  Get one paiement by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Paiement findOne(Long id) {
        log.debug("Request to get Paiement : {}", id);
        return paiementRepository.findOne(id);
    }

    /**
     *  Delete the  paiement by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Paiement : {}", id);
        
        Paiement paiement = findOne(id);
        Kafala kafala = kafalaRepository.findOne(paiement.getKafala().getId());
        Long kafalaMoispayes = kafala.getMoispayes();
        kafala.setMoispayes(kafalaMoispayes - paiement.getMoispayes());
        paiementRepository.delete(id);
    }
}
