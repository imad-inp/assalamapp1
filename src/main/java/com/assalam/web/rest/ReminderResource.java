package com.assalam.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.assalam.domain.Reminder;
import com.assalam.service.ReminderService;
import com.assalam.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Reminder.
 */
@RestController
@RequestMapping("/api")
public class ReminderResource {

    private final Logger log = LoggerFactory.getLogger(ReminderResource.class);

    private static final String ENTITY_NAME = "reminder";

    private final ReminderService reminderService;

    public ReminderResource(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    /**
     * POST  /reminders : Create a new reminder.
     *
     * @param reminder the reminder to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reminder, or with status 400 (Bad Request) if the reminder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reminders")
    @Timed
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) throws URISyntaxException {
        log.debug("REST request to save Reminder : {}", reminder);
        if (reminder.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new reminder cannot already have an ID")).body(null);
        }
        Reminder result = reminderService.save(reminder);
        return ResponseEntity.created(new URI("/api/reminders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reminders : Updates an existing reminder.
     *
     * @param reminder the reminder to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reminder,
     * or with status 400 (Bad Request) if the reminder is not valid,
     * or with status 500 (Internal Server Error) if the reminder couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reminders")
    @Timed
    public ResponseEntity<Reminder> updateReminder(@RequestBody Reminder reminder) throws URISyntaxException {
        log.debug("REST request to update Reminder : {}", reminder);
        if (reminder.getId() == null) {
            return createReminder(reminder);
        }
        Reminder result = reminderService.save(reminder);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reminder.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reminders : get all the reminders.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reminders in body
     */
    @GetMapping("/reminders")
    @Timed
  public List<Reminder> getAllReminders(@RequestParam(name = "kafilId", required = false) String kafilId) {
        log.debug("REST request to get all Reminders");
    List<Reminder> reminders = null;
    if (kafilId != null) {
      reminders = reminderService.findByKafilId(Long.valueOf(kafilId));
    }
    else {
      reminders = reminderService.findAll();
    }
    return reminders;
    }

    /**
     * GET  /reminders/:id : get the "id" reminder.
     *
     * @param id the id of the reminder to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reminder, or with status 404 (Not Found)
     */
    @GetMapping("/reminders/{id}")
    @Timed
    public ResponseEntity<Reminder> getReminder(@PathVariable Long id) {
        log.debug("REST request to get Reminder : {}", id);
        Reminder reminder = reminderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(reminder));
    }

    /**
     * DELETE  /reminders/:id : delete the "id" reminder.
     *
     * @param id the id of the reminder to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reminders/{id}")
    @Timed
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        log.debug("REST request to delete Reminder : {}", id);
        reminderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
