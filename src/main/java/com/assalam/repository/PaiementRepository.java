package com.assalam.repository;

import com.assalam.domain.Demandeadhesion;
import com.assalam.domain.Paiement;
import com.assalam.domain.enumeration.Statut;

import java.util.List;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Paiement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaiementRepository extends JpaRepository<Paiement,Long> {

  Page<Paiement> findByKafalaId(Pageable pageable, Long kafalaId);

  List<Paiement> findByKafalaId(Long valueOf);
}
