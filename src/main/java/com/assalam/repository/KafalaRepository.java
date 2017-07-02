package com.assalam.repository;

import com.assalam.domain.Kafala;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Kafala entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KafalaRepository extends JpaRepository<Kafala,Long> {
    
}
