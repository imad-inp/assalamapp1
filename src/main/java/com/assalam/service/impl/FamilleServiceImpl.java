package com.assalam.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.assalam.service.FamilleService;
import com.assalam.service.FilesService;
import com.assalam.domain.Famille;
import com.assalam.domain.Files;
import com.assalam.repository.FamilleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private FilesService filesService;

    public FamilleServiceImpl(FamilleRepository familleRepository) {
        this.familleRepository = familleRepository;
    }

    /**
   * Save a famille.
   *
   * @param famille
   *          the entity to save
   * @return the persisted entity
   * @throws IOException
   * @throws FileNotFoundException
   */
    @Override
  public Famille save(Famille famille) throws FileNotFoundException, IOException {
        log.debug("Request to save Famille : {}", famille);

    processFiles(famille);
        return familleRepository.save(famille);
    }

  private void processFiles(Famille famille) throws FileNotFoundException, IOException {
    if (famille.getTmpCertifDeces() != null) {
      Files file = new Files();
      file.setFile(famille.getTmpCertifDeces());
      file.setFileContentType(famille.getTmpCertifDecesContentType());
      if (famille.getCertifDecesRef() != null) {
        file.setId(Long.valueOf(famille.getCertifDecesRef()));
      }
      Files result = filesService.save(file);
      famille.setCertifDecesRef(result.getId().toString());
    }
    if (famille.getTmpCertifDivorce() != null) {
      Files file = new Files();
      file.setFile(famille.getTmpCertifDivorce());
      file.setFileContentType(famille.getTmpCertifDivorceContentType());
      if (famille.getCertifDivorceRef() != null) {
        file.setId(Long.valueOf(famille.getCertifDivorceRef()));
      }
      Files result = filesService.save(file);
      famille.setCertifDivorceRef(result.getId().toString());
    }
    if (famille.getTmpCertifDeces() != null) {
      Files file = new Files();
      file.setFile(famille.getTmpCertifDeces());
      file.setFileContentType(famille.getTmpCertifDecesContentType());
      if (famille.getCertifDecesRef() != null) {
        file.setId(Long.valueOf(famille.getCertifDecesRef()));
      }
      Files result = filesService.save(file);
      famille.setCertifDecesRef(result.getId().toString());
    }
    if (famille.getTmpCertifMariage() != null) {
      Files file = new Files();
      file.setFile(famille.getTmpCertifMariage());
      file.setFileContentType(famille.getTmpCertifMariageContentType());
      if (famille.getCertifMariageRef() != null) {
        file.setId(Long.valueOf(famille.getCertifMariageRef()));
      }
      Files result = filesService.save(file);
      famille.setCertifMariageRef(result.getId().toString());
    }
    if (famille.getTmpRamid() != null) {
      Files file = new Files();
      file.setFile(famille.getTmpRamid());
      file.setFileContentType(famille.getTmpRamidContentType());
      if (famille.getRamidRef() != null) {
        file.setId(Long.valueOf(famille.getRamidRef()));
      }
      Files result = filesService.save(file);
      famille.setRamidRef(result.getId().toString());
    }
    if (famille.getTmpCinMereCopie() != null) {
      Files file = new Files();
      file.setFile(famille.getTmpCinMereCopie());
      file.setFileContentType(famille.getTmpCinMereCopieContentType());
      if (famille.getCinMereCopieRef() != null) {
        file.setId(Long.valueOf(famille.getCinMereCopieRef()));
      }
      Files result = filesService.save(file);
      famille.setCinMereCopieRef(result.getId().toString());
    }
    if (famille.getTmpCinPereCopie() != null) {
      Files file = new Files();
      file.setFile(famille.getTmpCinPereCopie());
      file.setFileContentType(famille.getTmpCinPereCopieContentType());
      if (famille.getCinPereCopieRef() != null) {
        file.setId(Long.valueOf(famille.getCinPereCopieRef()));
      }
      Files result = filesService.save(file);
      famille.setCinPereCopieRef(result.getId().toString());
    }
    if (famille.getTmpPhotoMere() != null) {
      Files file = new Files();
      file.setFile(famille.getTmpPhotoMere());
      file.setFileContentType(famille.getTmpPhotoMereContentType());
      if (famille.getPhotoMereRef() != null) {
        file.setId(Long.valueOf(famille.getPhotoMereRef()));
      }
      Files result = filesService.save(file);
      famille.setPhotoMereRef((result.getId()));
    }
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

  @Override
  public Page<Famille> findByLastName(Pageable pageable, String searchValue) {
    return familleRepository.findByPereContainingOrMereContaining(pageable, searchValue, searchValue);

  }

  @Override
  public Page<Famille> findByCin(Pageable pageable, String searchValue) {

    return familleRepository.findByCinPereContainingOrCinMereContaining(pageable, searchValue, searchValue);

  }

  @Override
  public Page<Famille> findByState(Pageable pageable, String state) {
    return familleRepository.findByState(pageable, state);
  }
}
