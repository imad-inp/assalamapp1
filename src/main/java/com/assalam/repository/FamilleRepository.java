package com.assalam.repository;

import com.assalam.domain.Famille;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Famille entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FamilleRepository extends JpaRepository<Famille,Long> {

  Page<Famille> findByPereContainingOrMereContaining(Pageable pageable, String pere, String mere);

  Page<Famille> findByCinPereContainingOrCinMereContaining(Pageable pageable, String cinPere, String cinMere);
}
