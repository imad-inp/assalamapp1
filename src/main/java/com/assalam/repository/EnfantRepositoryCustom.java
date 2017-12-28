package com.assalam.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.assalam.domain.Enfant;

public interface EnfantRepositoryCustom {

  @Query("SELECT e FROM Enfant e where e.kafalaState IN :statuts")
  List<Enfant> pullByStatuts(List<String> statuts);

  Page<Enfant> pullByStatuts(Pageable pageable, List<String> statuts);
}
