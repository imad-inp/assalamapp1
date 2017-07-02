package com.assalam.repository;

import com.assalam.domain.Paiement;
import java.util.List;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Paiement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {
    
  @Query("SELECT p FROM Paiement p WHERE p.kafala.id = :kafalaId")
  List<Paiement> findByKafalaId(Long kafalaId);
    
}
