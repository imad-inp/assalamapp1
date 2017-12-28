package com.assalam.repository;

import com.assalam.domain.Demandeadhesion;
import com.assalam.domain.enumeration.Statut;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Demandeadhesion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeadhesionRepository extends JpaRepository<Demandeadhesion,Long> {

    Page<Demandeadhesion> findByStatut(Pageable pageable, Statut statut);

  List<Demandeadhesion> findByStatut(Statut statut);
}
