package com.assalam.service.impl;

import com.assalam.service.ReminderService;
import com.assalam.domain.Reminder;
import com.assalam.repository.ReminderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Reminder.
 */
@Service
@Transactional
public class ReminderServiceImpl implements ReminderService{

    private final Logger log = LoggerFactory.getLogger(ReminderServiceImpl.class);

    private final ReminderRepository reminderRepository;

    public ReminderServiceImpl(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    /**
     * Save a reminder.
     *
     * @param reminder the entity to save
     * @return the persisted entity
     */
    @Override
    public Reminder save(Reminder reminder) {
        log.debug("Request to save Reminder : {}", reminder);
        return reminderRepository.save(reminder);
    }

    /**
     *  Get all the reminders.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Reminder> findAll() {
        log.debug("Request to get all Reminders");
        return reminderRepository.findAll();
    }

    /**
     *  Get one reminder by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Reminder findOne(Long id) {
        log.debug("Request to get Reminder : {}", id);
        return reminderRepository.findOne(id);
    }

  /**
   * find by Kafil Id
   * 
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public List<Reminder> findByKafilId(Long id) {
    log.debug("Request to get Reminders by kafilId : {}", id);
    return reminderRepository.findByKafilsId(id);
  }

    /**
     *  Delete the  reminder by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reminder : {}", id);
        reminderRepository.delete(id);
    }
}
