package com.assalam.service;

import com.assalam.domain.Reminder;
import java.util.List;

/**
 * Service Interface for managing Reminder.
 */
public interface ReminderService {

    /**
     * Save a reminder.
     *
     * @param reminder the entity to save
     * @return the persisted entity
     */
    Reminder save(Reminder reminder);

    /**
     *  Get all the reminders.
     *
     *  @return the list of entities
     */
    List<Reminder> findAll();

    /**
     *  Get the "id" reminder.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Reminder findOne(Long id);

    /**
     *  Delete the "id" reminder.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

  List<Reminder> findByKafilId(Long id);
}
