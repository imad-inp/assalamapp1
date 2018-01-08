package com.assalam.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.assalam.service.FilesService;
import com.assalam.domain.Files;
import com.assalam.repository.FilesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing files.
 */
@Service
@Transactional
public class FilesServiceImpl implements FilesService {

  private final Logger log = LoggerFactory.getLogger(FilesServiceImpl.class);

  private final FilesRepository filesRepository;

  public FilesServiceImpl(FilesRepository filesRepository) {
    this.filesRepository = filesRepository;
  }

  /**
   * Save a files.
   * 
   * @param files
   *          the entity to save
   * @return the persisted entity
   * @throws IOException
   * @throws FileNotFoundException
   */
  @Override
  public Files save(Files file) throws FileNotFoundException, IOException {
    log.debug("Request to save files : {}", file);

    File theDir = new File("files");

    // if the directory does not exist, create it
    if (!theDir.exists()) {
        theDir.mkdir();
    }

    byte[] fileContent = file.getFile();
    file.setFile(null);
    Files savedFile = filesRepository.save(file);
    try (FileOutputStream fos = new FileOutputStream("files/" + savedFile.getId())) {
      fos.write(fileContent);
      fos.close();
    }
    return savedFile;
  }

  /**
   * Get all the filess.
   *
   * @param pageable
   *          the pagination information
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public Page<Files> findAll(Pageable pageable) {
    log.debug("Request to get all filess");
    return filesRepository.findAll(pageable);
  }

  /**
   * Get one files by id.
   * 
   * @param id
   *          the id of the entity
   * @return the entity
   * @throws IOException
   */
  @Override
  @Transactional(readOnly = true)
  public Files findOne(Long id) throws IOException {
    log.debug("Request to get files : {}", id);
    Files file = filesRepository.findOne(id);
    Path path = Paths.get("files/" + file.getId());
    byte[] result = java.nio.file.Files.readAllBytes(path);
    file.setFile(result);
    return file;

  }

  /**
   * Delete the files by id.
   *
   * @param id
   *          the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete files : {}", id);
    filesRepository.delete(id);
  }

  @Override
  public Files save(Files files, String path) throws IOException {

    return null;
  }
}
