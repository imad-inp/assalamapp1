package com.assalam.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.assalam.domain.Enfant;

public interface EnfantRepositoryCustom {


  List<Enfant> pullByStatuts(List<String> statuts);

  Page<Enfant> pullByStatuts(Pageable pageable, List<String> statuts);

  List<Enfant> findAllReduced();

}
