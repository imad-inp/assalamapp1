package com.assalam.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Resultatsscolaires.
 */
@Entity
@Table(name = "resultatsscolaires")
public class Resultatsscolaires implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Enfant enfant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public Resultatsscolaires annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public String getDescription() {
        return description;
    }

    public Resultatsscolaires description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enfant getEnfant() {
        return enfant;
    }

    public Resultatsscolaires enfant(Enfant enfant) {
        this.enfant = enfant;
        return this;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resultatsscolaires resultatsscolaires = (Resultatsscolaires) o;
        if (resultatsscolaires.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resultatsscolaires.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Resultatsscolaires{" +
            "id=" + getId() +
            ", annee='" + getAnnee() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
