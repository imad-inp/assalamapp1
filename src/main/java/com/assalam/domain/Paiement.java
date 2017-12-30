package com.assalam.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.assalam.domain.enumeration.PaiementType;

/**
 * A Paiement.
 */
@Entity
@Table(name = "paiement")
public class Paiement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "montant")
    private Long montant;

    @Column(name = "mois_payes")
    private Long moispayes;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PaiementType type;

    @Column(name = "commentaires")
    private String commentaires;

    @ManyToOne
    private Kafala kafala;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Paiement date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getMontant() {
        return montant;
    }

    public Paiement montant(Long montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Long montant) {
        this.montant = montant;
    }

    public PaiementType getType() {
        return type;
    }

    public Paiement type(PaiementType type) {
        this.type = type;
        return this;
    }

    public void setType(PaiementType type) {
        this.type = type;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public Paiement commentaires(String commentaires) {
        this.commentaires = commentaires;
        return this;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Kafala getKafala() {
        return kafala;
    }

    public Paiement kafala(Kafala kafala) {
        this.kafala = kafala;
        return this;
    }

    public void setKafala(Kafala kafala) {
        this.kafala = kafala;
    }

    public Long getMoispayes() {
        return this.moispayes;
    }

    public Paiement moispayes(Long moispayes) {
        this.moispayes = moispayes;
        return this;
    }

    public void setMoispayes(Long moispayes) {
        this.moispayes = moispayes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Paiement paiement = (Paiement) o;
        if (paiement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), paiement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Paiement{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", montant='" + getMontant() + "'" +
            ", type='" + getType() + "'" +
            ", commentaires='" + getCommentaires() + "'" +
            "}";
    }
}
