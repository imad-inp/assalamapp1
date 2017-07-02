package com.assalam.repository;

import com.assalam.domain.Resultatsscolaires;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Resultatsscolaires entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResultatsscolairesRepository extends JpaRepository<Resultatsscolaires,Long> {
    
}
