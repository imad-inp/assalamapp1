package com.assalam.domain;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Reminder.
 */
@Entity
@Table(name = "reminder")
public class Reminder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jhi_date")
    private String date;

    @Column(name = "jhi_type")
    private String type;

    @ManyToOne
  @JsonIgnore
    private Kafil kafils;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public Reminder date(String date) {
        this.date = date;
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public Reminder type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Kafil getKafils() {
        return kafils;
    }

    public Reminder kafils(Kafil kafil) {
        this.kafils = kafil;
        return this;
    }

    public void setKafils(Kafil kafil) {
        this.kafils = kafil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reminder reminder = (Reminder) o;
        if (reminder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reminder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reminder{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
