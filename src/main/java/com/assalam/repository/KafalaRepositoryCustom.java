package com.assalam.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.assalam.domain.Enfant;

public interface KafalaRepositoryCustom {


  List<Enfant> findByCriteria(List<String> statuts);

  Page<Enfant> pullByStatuts(Pageable pageable, List<String> statuts);

}
