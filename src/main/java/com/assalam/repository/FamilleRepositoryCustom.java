package com.assalam.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.assalam.domain.Enfant;
import com.assalam.domain.Famille;

public interface FamilleRepositoryCustom {


  List<Famille> pullByName(String name);

  List<Famille> pullByCin(String cin);
}
