package com.assalam.service.impl;

import com.assalam.service.DemandeadhesionService;
import com.assalam.domain.Demandeadhesion;
import com.assalam.domain.enumeration.Statut;
import com.assalam.repository.DemandeadhesionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Implementation for managing Demandeadhesion.
 */
@Service
@Transactional
public class DemandeadhesionServiceImpl implements DemandeadhesionService{

    private final Logger log = LoggerFactory.getLogger(DemandeadhesionServiceImpl.class);

    private final DemandeadhesionRepository demandeadhesionRepository;

    public DemandeadhesionServiceImpl(DemandeadhesionRepository demandeadhesionRepository) {
        this.demandeadhesionRepository = demandeadhesionRepository;
    }

    /**
     * Save a demandeadhesion.
     *
     * @param demandeadhesion the entity to save
     * @return the persisted entity
     */
    @Override
    public Demandeadhesion save(Demandeadhesion demandeadhesion) {
        log.debug("Request to save Demandeadhesion : {}", demandeadhesion);
        return demandeadhesionRepository.save(demandeadhesion);
    }

    /**
     *  Get all the demandeadhesions.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Demandeadhesion> findAll(Pageable pageable) {
        log.debug("Request to get all Demandeadhesions");
        return demandeadhesionRepository.findAll(pageable);
    }

  @Override
  @Transactional(readOnly = true)
  public List<Demandeadhesion> findAll() {
    log.debug("Request to get all Demandeadhesions");
    return demandeadhesionRepository.findAll();
  }

    /**
     *  Get one demandeadhesion by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Demandeadhesion findOne(Long id) {
        log.debug("Request to get Demandeadhesion : {}", id);
        return demandeadhesionRepository.findOne(id);
    }

     /**
     *  Get one demandeadhesion by statut.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Demandeadhesion> findbyStatut(Pageable pageable, Statut statut) {
        log.debug("Request to get Demandeadhesion by statut : {}", statut);
        return demandeadhesionRepository.findByStatut(pageable, statut);
    }

  @Override
  @Transactional(readOnly = true)
  public List<Demandeadhesion> findbyStatut(Statut statut) {
    log.debug("Request to get Demandeadhesion by statut : {}", statut);
    return demandeadhesionRepository.findByStatut(statut);
  }

    /**
     *  Delete the  demandeadhesion by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Demandeadhesion : {}", id);
        demandeadhesionRepository.delete(id);
    }

  @Override
  public Page<Demandeadhesion> findbyFamilleId(Pageable pageable, Long familleId) {

    return demandeadhesionRepository.findByFamilleId(pageable, familleId);
  }
}
