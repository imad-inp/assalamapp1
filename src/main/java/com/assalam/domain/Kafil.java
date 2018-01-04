package com.assalam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Kafil.
 */
@Entity
@Table(name = "kafil")
public class Kafil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "tel")
    private String tel;

    @Column(name = "membre")
    private Boolean membre;

    @Column(name = "commentaires")
    private String commentaires;

  @Column(name = "date_debut")
  private String dateDebut;

  @Column(name = "date_fin")
  private String dateFin;

    @Transient
    @JsonProperty
    private String fullName;

  @Transient
  @JsonProperty
  private String fullId;

  public String getDateDebut() {
    return dateDebut;
  }

  public void setDateDebut(String dateDebut) {
    this.dateDebut = dateDebut;
  }

  public String getDateFin() {
    return dateFin;
  }

  public void setDateFin(String dateFin) {
    this.dateFin = dateFin;
  }

  public String getFullId() {
    String fullId = null;
    if (dateDebut != null && dateDebut.split("-")[0] != null) {
      fullId = dateDebut.split("-")[0] + "-" + id;
    }
    return fullId;
  }

    @OneToMany(mappedBy = "kafil")
    @JsonIgnore
    private Set<Kafala> kafalats = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Kafil nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Kafil prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Kafil adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public Kafil tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean isMembre() {
        return membre;
    }

    public Kafil membre(Boolean membre) {
        this.membre = membre;
        return this;
    }

    public void setMembre(Boolean membre) {
        this.membre = membre;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public Kafil commentaires(String commentaires) {
        this.commentaires = commentaires;
        return this;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }


    public String getFullName() {
        return this.nom + " " + this.prenom;
    }
    public Set<Kafala> getKafalats() {
        return kafalats;
    }

    public Kafil kafalats(Set<Kafala> kafalas) {
        this.kafalats = kafalas;
        return this;
    }

    public Kafil addKafalat(Kafala kafala) {
        this.kafalats.add(kafala);
        kafala.setKafil(this);
        return this;
    }

    public Kafil removeKafalat(Kafala kafala) {
        this.kafalats.remove(kafala);
        kafala.setKafil(null);
        return this;
    }

    public void setKafalats(Set<Kafala> kafalas) {
        this.kafalats = kafalas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Kafil kafil = (Kafil) o;
        if (kafil.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kafil.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Kafil{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", tel='" + getTel() + "'" +
            ", membre='" + isMembre() + "'" +
            ", commentaires='" + getCommentaires() + "'" +

            "}";
    }
}
