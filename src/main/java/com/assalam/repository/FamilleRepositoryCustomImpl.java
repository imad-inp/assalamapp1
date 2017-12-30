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
import com.assalam.domain.Famille;

@Repository
@Transactional(readOnly = true)
public class FamilleRepositoryCustomImpl implements FamilleRepositoryCustom {

  @PersistenceContext
  EntityManager entityManager;

  @Override
  public List<Famille> pullByName(String name) {
    Query query = entityManager.createQuery("SELECT famille FROM Famille as famille " +
        "WHERE famille.pere LIKE %:name% OR famille.mere LIKE %:name%", Enfant.class);
    query.setParameter("name", name);
    return query.getResultList();
  }

  @Override
  public List<Famille> pullByCin(String cin) {
    Query query = entityManager.createQuery("SELECT enfant FROM Enfant as enfant " +
        "WHERE enfant.kafalaState IN :statuts", Enfant.class);
    query.setParameter("cin", cin);
    return query.getResultList();
  }


}
