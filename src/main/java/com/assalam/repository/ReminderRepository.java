package com.assalam.repository;

import java.util.List;

import com.assalam.domain.Reminder;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Reminder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReminderRepository extends JpaRepository<Reminder,Long> {

  List<Reminder> findByKafilsId(Long kafilsId);

}
