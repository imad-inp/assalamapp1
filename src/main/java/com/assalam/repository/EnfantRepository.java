package com.assalam.repository;

import com.assalam.domain.Enfant;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Enfant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnfantRepository extends JpaRepository<Enfant,Long> {
     Page<Enfant> findByFamilleId(Pageable pageable, Long familleId);
}
