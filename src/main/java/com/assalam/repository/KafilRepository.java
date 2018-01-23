package com.assalam.repository;

import com.assalam.domain.Kafil;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Kafil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KafilRepository extends JpaRepository<Kafil,Long> {

  Page<Kafil> findByNomContainingOrPrenomContaining(Pageable pageable, String search, String searchValue);

  Long countByState(String state);
}
