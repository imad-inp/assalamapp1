	package com.assalam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Enfant.
 */
@Entity
@Table(name = "enfant")
public class Enfant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "tel")
    private String tel;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;
    
    @Transient
    @JsonProperty
    private String fullName;

    @Column(name = "commentaires")
    private String commentaires;

    @OneToMany(mappedBy = "enfant")
    @JsonIgnore
    private Set<Kafala> kafalats = new HashSet<>();

    @OneToMany(mappedBy = "enfant")
    @JsonIgnore
    private Set<Resultatsscolaires> resultats = new HashSet<>();

    @ManyToOne
    private Famille famille;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Enfant nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Enfant prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public Enfant DateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
        return this;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public Enfant adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public Enfant tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Enfant photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public Enfant photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }
    public String getFullName() {
        return this.nom + " " + this.prenom;
    }
    

    public String getCommentaires() {
        return commentaires;
    }

    public Enfant commentaires(String commentaires) {
        this.commentaires = commentaires;
        return this;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Set<Kafala> getKafalats() {
        return kafalats;
    }

    public Enfant kafalats(Set<Kafala> kafalas) {
        this.kafalats = kafalas;
        return this;
    }

    public Enfant addKafalat(Kafala kafala) {
        this.kafalats.add(kafala);
        kafala.setEnfant(this);
        return this;
    }

    public Enfant removeKafalat(Kafala kafala) {
        this.kafalats.remove(kafala);
        kafala.setEnfant(null);
        return this;
    }

    public void setKafalats(Set<Kafala> kafalas) {
        this.kafalats = kafalas;
    }

    public Set<Resultatsscolaires> getResultats() {
        return resultats;
    }

    public Enfant resultats(Set<Resultatsscolaires> resultatsscolaires) {
        this.resultats = resultatsscolaires;
        return this;
    }

    public Enfant addResultats(Resultatsscolaires resultatsscolaires) {
        this.resultats.add(resultatsscolaires);
        resultatsscolaires.setEnfant(this);
        return this;
    }

    public Enfant removeResultats(Resultatsscolaires resultatsscolaires) {
        this.resultats.remove(resultatsscolaires);
        resultatsscolaires.setEnfant(null);
        return this;
    }

    public void setResultats(Set<Resultatsscolaires> resultatsscolaires) {
        this.resultats = resultatsscolaires;
    }

    public Famille getFamille() {
        return famille;
    }

    public Enfant famille(Famille famille) {
        this.famille = famille;
        return this;
    }

    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Enfant enfant = (Enfant) o;
        if (enfant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), enfant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Enfant{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", age='" + getDateDeNaissance() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", tel='" + getTel() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", commentaires='" + getCommentaires() + "'" +
            "}";
    }
}
