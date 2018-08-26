package com.assalam.service.impl;



import com.assalam.service.FilesService;
import com.assalam.service.KafalaService;
import com.assalam.domain.Files;
import com.assalam.domain.Kafala;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import com.assalam.domain.Paiement;
import com.assalam.repository.FilesRepository;
import com.assalam.repository.KafalaRepository;
import com.assalam.repository.PaiementRepository;

import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Kafala.
 */
@Service
@Transactional
public class KafalaServiceImpl implements KafalaService{

    private final Logger log = LoggerFactory.getLogger(KafalaServiceImpl.class);

    private final KafalaRepository kafalaRepository;

    @Autowired
  private FilesService filesService;

  @Autowired
    private PaiementRepository paiementRepository;

    public KafalaServiceImpl(KafalaRepository kafalaRepository) {
        this.kafalaRepository = kafalaRepository;
    }

    /**
   * Save a kafala.
   * 
   * @param kafala
   *          the entity to save
   * @return the persisted entity
   * @throws IOException
   * @throws FileNotFoundException
   */
    @Override
  public Kafala save(Kafala kafala) throws FileNotFoundException, IOException {
        log.debug("Request to save Kafala : {}", kafala);
    if (kafala.getTmpEngagement() != null) {
      Files file = new Files();
      file.setFile(kafala.getTmpEngagement());
      file.setFileContentType(kafala.getTmpEngagementContentType());
      file.setId(kafala.getId());
      Files result = filesService.save(file);
      kafala.setEngagementRef(result.getId());
    }
        return kafalaRepository.save(kafala);

    }

    /**
     *  Get all the kafalas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Kafala> findAll(Pageable pageable) {
        log.debug("Request to get all Kafalas");
        return kafalaRepository.findAll(pageable);
    }

    /**
     *  Get one kafala by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Kafala findOne(Long id) {
        log.debug("Request to get Kafala : {}", id);
        return kafalaRepository.findOne(id);
    }

  @Override
  public Integer countLateKafalas(String kafalaState) {
    int count = 0;
    List<Kafala> kafalas = null;
    if (kafalaState != null) {
      kafalas = kafalaRepository.findByState(kafalaState);
    }
    else {
      kafalas = kafalaRepository.findAll();
    }
    for (Kafala kafala : kafalas) {
      if (isLate(kafala)) {
        count++;
      }
    }
    return count;
  }

  /**
   * Check if a kafala's payments are late
   *
   * @param kafala
   * @return
   */
  private boolean isLate(Kafala kafala) {
    boolean isLate = false;
    if (kafala.getStartDate() != null && (kafala.getEndDate() == null || kafala.getEndDate() == "")) {
      LocalDate startDate = LocalDate.parse(kafala.getStartDate());

    long monthDiff = LocalDate.now().getMonthValue() - startDate.getMonthValue();

    long yearDiff = (LocalDate.now().getYear() - startDate.getYear());
      isLate = monthDiff + (yearDiff * 12) + 1 > kafala.getMoispayes();

    }
    return isLate;

  }

    /**
     *  Delete the  kafala by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Kafala : {}", id);
    Kafala kafalaToDelete = kafalaRepository.findOne(id);
    if (kafalaToDelete != null) {
      for (Paiement paiment : kafalaToDelete.getPaiements()) {
        paiementRepository.delete(paiment);
      }
    }
        kafalaRepository.delete(id);
    }

  @Override
  public Page<Kafala> findByKafilId(Pageable pageable, String kafilId) {

    return kafalaRepository.findByKafilId(pageable, Long.valueOf(kafilId));
  }

  @Override
  public Page<Kafala> findByEnfantId(Pageable pageable, String enfantId) {

    return kafalaRepository.findByEnfantId(pageable, Long.valueOf(enfantId));
  }

  @Override
  public List<Kafala> findLateKafalas(String kafalaState) {
    List<Kafala> kafalas = null;
    if (kafalaState != null) {
      kafalas = kafalaRepository.findByState(kafalaState);
    }
    else {
      kafalas = kafalaRepository.findAll();
    }

    Iterator<Kafala> it = kafalas.iterator();
    while (it.hasNext()) {
      if (!isLate(it.next())) {
        it.remove();

      }

    }
    return kafalas;
  }

  @Override
  public List<Kafala> findByState(String state) {
    return kafalaRepository.findByState(state);
  }

  @Override
  public List<Kafala> findByStartYear(String searchValue) {

    return kafalaRepository.findByStartDateContaining(searchValue);
  }

  @Override
  public List<Kafala> findByStartYearAndState(String datedebut, String state) {

    return kafalaRepository.findByStartDateContainingAndState(datedebut, state);
  }

  @Override
  public List<Kafala> findByEndYearAndState(String datefin, String state) {

    return kafalaRepository.findByEndDateContainingAndState(datefin, state);
  }
}
