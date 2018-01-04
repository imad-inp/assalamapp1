package com.assalam.repository;

import com.assalam.domain.Files;
import com.assalam.domain.Kafil;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Kafil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {

}
