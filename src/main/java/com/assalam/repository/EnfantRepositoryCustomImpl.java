package com.assalam.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

import com.assalam.domain.Enfant;

@Repository
@Transactional(readOnly = true)
public class EnfantRepositoryCustomImpl implements EnfantRepositoryCustom {

  @PersistenceContext
  EntityManager entityManager;

  @Override
  public List<Enfant> pullByStatuts(List<String> statuts) {
    Query query = entityManager.createQuery("SELECT enfant FROM Enfant as enfant " +
        "WHERE enfant.kafalaState IN :statuts", Enfant.class);
    query.setParameter("statuts", statuts);
    return query.getResultList();
  }

  @Override
  public Page<Enfant> pullByStatuts(Pageable pageable, List<String> statuts) {
    return null;
  }

  @Override
  public List<Enfant> findAllReduced() {
    Query query = entityManager.createQuery("SELECT enfant.nom, enfant.prenom FROM Enfant as enfant ");

    return query.getResultList();
  }

  @Override
  public List<Enfant> searchByNameAndStatus(String name, String status) {
    Query query = entityManager
        .createQuery(
            "SELECT enfant FROM Enfant as enfant "
                +
                "WHERE enfant.kafalaState = :status AND enfant.nom like CONCAT('%',:name,'%')  OR enfant.prenom like CONCAT('%',:name,'%')",
            Enfant.class);
    query.setParameter("status", status);
    query.setParameter("name", name);
    return query.getResultList();
  }

}
