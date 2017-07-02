package com.assalam.repository;

import com.assalam.domain.Demandeadhesion;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Demandeadhesion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandeadhesionRepository extends JpaRepository<Demandeadhesion,Long> {
    
}
