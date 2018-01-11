package com.assalam.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.assalam.service.EnfantService;
import com.assalam.service.FilesService;
import com.assalam.domain.Enfant;
import com.assalam.domain.Files;
import com.assalam.domain.Kafala;
import com.assalam.repository.EnfantRepository;
import com.assalam.repository.EnfantRepositoryCustom;
import com.assalam.repository.FilesRepository;
import com.assalam.repository.KafalaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EnfantServiceImpl implements EnfantService {

  private final Logger log = LoggerFactory.getLogger(EnfantServiceImpl.class);

  private final EnfantRepository enfantRepository;

  @Autowired
  private KafalaRepository kafalaRepository;

  @Autowired
  private EnfantRepositoryCustom enfantRepositoryCustom;

  @Autowired
  private FilesService filesService;

  public EnfantServiceImpl(EnfantRepository enfantRepository) {
    this.enfantRepository = enfantRepository;
  }

  /**
   * Save a enfant.
   * 
   * @param enfant
   *          the entity to save
   * @return the persisted entity
   * @throws IOException
   * @throws FileNotFoundException
   */
  @Override
  public Enfant save(Enfant enfant) throws FileNotFoundException, IOException {
    log.debug("Request to save Enfant : {}", enfant);

    if (enfant.getTmpPhoto() != null) {
      Files file = new Files();
      file.setFile(enfant.getTmpPhoto());
      file.setFileContentType(enfant.getTmpPhotoContentType());
      file.setId(enfant.getPhotoRef());
      Files result = filesService.save(file);
      enfant.setPhotoRef(result.getId());
    }
    return enfantRepository.save(enfant);
  }

  /**
   * Get all the enfants.
   *
   * @param pageable
   *          the pagination information
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public Page<Enfant> findAll(Pageable pageable) {
    log.debug("Request to get all Enfants");
    return enfantRepository.findAll(pageable);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Enfant> findAll() {
    log.debug("Request to get all Enfants");
    List<Enfant> enfants = enfantRepository.findAll();
    for (Enfant enfant : enfants) {
      enfant.setPhoto(null);
    }
    return enfants;
  }

  @Override
  public Page<Enfant> findbyStatuts(Pageable pageable, String statut) {
    return enfantRepository.findByKafalaState(pageable, statut);
  }

  /**
   * Get one enfant by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Enfant findOne(Long id) {
    log.debug("Request to get Enfant : {}", id);
    return enfantRepository.findOne(id);
  }

  /**
   * Get Enfants by famille Ids.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Page<Enfant> findbyFamilleId(Pageable pageable, Long familleId) {
    log.debug("Request to get enfants by famille Id : {}", familleId);
    return enfantRepository.findByFamilleId(pageable, familleId);
  }

  /**
   * Delete the enfant by id.
   *
   * @param id
   *          the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete Enfant : {}", id);
    Enfant enfantToDelete = enfantRepository.findOne(id);
    if (enfantToDelete != null) {
      for (Kafala kafala : enfantToDelete.getKafalats()) {
        kafalaRepository.delete(kafala);
      }
    }
    enfantRepository.delete(id);
  }

  @Override
  public Long countByStatut(String statut) {

    return enfantRepository.countByKafalaState(statut);
  }

  @Override
  public List<Enfant> searchByNameAndStatus(String name, String status) {
    return enfantRepositoryCustom.searchByNameAndStatus(name, status);

  }

}
