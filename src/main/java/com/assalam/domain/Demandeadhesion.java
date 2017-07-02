package com.assalam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.assalam.domain.enumeration.Statut;

/**
 * A Demandeadhesion.
 */
@Entity
@Table(name = "demandeadhesion")
public class Demandeadhesion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datedemande")
    private LocalDate datedemande;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private Statut statut;

    @Lob
    @Column(name = "demande")
    private byte[] demande;
    
    
    @Column(name = "demande_content_type")
    private String demandeContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatedemande() {
        return datedemande;
    }

    public Demandeadhesion datedemande(LocalDate datedemande) {
        this.datedemande = datedemande;
        return this;
    }

    public void setDatedemande(LocalDate datedemande) {
        this.datedemande = datedemande;
    }

    public Statut getStatut() {
        return statut;
    }

    public Demandeadhesion statut(Statut statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public byte[] getDemande() {
        return this.demande;
    }

    public Demandeadhesion demande(byte[] demande) {
        this.demande = demande;
        return this;
    }

    public void setDemande(byte[] demande) {
        this.demande = demande;
    }
    
      public String getDemandeContentType() {
        return demandeContentType;
    }

    public Demandeadhesion demandeContentType(String photoContentType) {
        this.demandeContentType = demandeContentType;
        return this;
    }

    public void setDemandeContentType(String demandeContentType) {
        this.demandeContentType = demandeContentType;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Demandeadhesion demandeadhesion = (Demandeadhesion) o;
        if (demandeadhesion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demandeadhesion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Demandeadhesion{"
                + "id=" + getId()
                + ", datedemande='" + getDatedemande() + "'"
                + ", statut='" + getStatut() + "'"
                + "}";
    }
}
