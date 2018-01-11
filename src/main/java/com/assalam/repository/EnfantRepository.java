package com.assalam.repository;

import java.util.List;

import com.assalam.domain.Enfant;
import com.assalam.domain.Famille;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Enfant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnfantRepository extends JpaRepository<Enfant, Long> {
     Page<Enfant> findByFamilleId(Pageable pageable, Long familleId);

  long countByKafalaState(String statut);

  Page<Enfant> findByKafalaState(Pageable pageable, String kafalaState);

  @Override
  Page<Enfant> findAll(Pageable pageable);



  List<Enfant> findByKafalaStateAndNomOrPrenom(String name, String name2, String statut);
}
