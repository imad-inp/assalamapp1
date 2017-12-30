package com.assalam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Famille.
 */
@Entity
@Table(name = "famille")
public class Famille implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "pere")
    private String pere;

    @Column(name = "mere")
    private String mere;

    @Column(name = "cin_mere")
    private String cinMere;

    @Column(name = "cin_pere")
    private String cinPere;

  @Column(name = "etat_social")
  private String etatSocial;

  @Column(name = "revenu_mensuel")
  private Long revenuMensuel;

  @Lob
  @Column(name = "certif_mariage")
  private byte[] certifMariage;

  @Lob
  @Column(name = "certif_deces_mari")
  private byte[] certifDecesMari;

  @Lob
  @Column(name = "certif_divorce")
  private byte[] certifDivorce;

  @Lob
  @Column(name = "cin_mere_copie")
  private byte[] cinMereCopie;

  @Lob
  @Column(name = "cin_pere_copie")
  private byte[] cinPereCopie;

  @Column(name = "cin_pere_copie_content_type")
  private String cinPereCopieContentType;

  @Column(name = "cin_mere_copie_content_type")
  private String cinMereCopieContentType;

  @Column(name = "certif_divorce_content_type")
  private String certifDivorceContentType;

  @Column(name = "certif_deces_mari_content_type")
  private String certifDecesMariContentType;

  @Column(name = "certif_mariage_content_type")
  private String certifMariageContentType;

  public byte[] getCertifMariage() {
    return certifMariage;
  }

  public void setCertifMariage(byte[] certifMariage) {
    this.certifMariage = certifMariage;
  }

  public byte[] getCertifDecesMari() {
    return certifDecesMari;
  }

  public void setCertifDecesMari(byte[] certifDecesMari) {
    this.certifDecesMari = certifDecesMari;
  }

  public byte[] getCertifDivorce() {
    return certifDivorce;
  }

  public void setCertifDivorce(byte[] certifDivorce) {
    this.certifDivorce = certifDivorce;
  }

  public byte[] getCinMereCopie() {
    return cinMereCopie;
  }

  public void setCinMereCopie(byte[] cinMereCopie) {
    this.cinMereCopie = cinMereCopie;
  }

  public byte[] getCinPereCopie() {
    return cinPereCopie;
  }

  public void setCinPereCopie(byte[] cinPereCopie) {
    this.cinPereCopie = cinPereCopie;
  }

  public String getCinPereCopieContentType() {
    return cinPereCopieContentType;
  }

  public void setCinPereCopieContentType(String cinPereCopieContentType) {
    this.cinPereCopieContentType = cinPereCopieContentType;
  }

  public String getCinMereCopieContentType() {
    return cinMereCopieContentType;
  }

  public void setCinMereCopieContentType(String cinMereCopieContentType) {
    this.cinMereCopieContentType = cinMereCopieContentType;
  }

  public String getCertifDivorceContentType() {
    return certifDivorceContentType;
  }

  public void setCertifDivorceContentType(String certifDivorceContentType) {
    this.certifDivorceContentType = certifDivorceContentType;
  }

  public String getCertifDecesMariContentType() {
    return certifDecesMariContentType;
  }

  public void setCertifDecesMariContentType(String certifDecesMariContentType) {
    this.certifDecesMariContentType = certifDecesMariContentType;
  }

  public String getCertifMariageContentType() {
    return certifMariageContentType;
  }

  public void setCertifMariageContentType(String certifMariageContentType) {
    this.certifMariageContentType = certifMariageContentType;
  }

  public Long getRevenuMensuel() {
    return revenuMensuel;
  }

  public void setRevenuMensuel(Long revenuMensuel) {
    this.revenuMensuel = revenuMensuel;
  }

  public String getEtatSocial() {
    return etatSocial;
  }

  public void setEtatSocial(String etatSocial) {
    this.etatSocial = etatSocial;
  }

    public String getPere() {
      return pere;
    }

    public void setPere(String pere) {
      this.pere = pere;
    }

    public String getMere() {
      return mere;
    }

    public void setMere(String mere) {
      this.mere = mere;
    }

    public String getCinMere() {
      return cinMere;
    }

    public void setCinMere(String cinMere) {
      this.cinMere = cinMere;
    }

    public String getCinPere() {
      return cinPere;
    }

    public void setCinPere(String cinPere) {
      this.cinPere = cinPere;
    }

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "tel")
    private String tel;

    @Column(name = "commentaires")
    private String commentaires;

    @OneToMany(mappedBy = "famille")
    @JsonIgnore
    private Set<Kafala> kafalats = new HashSet<>();

    @OneToMany(mappedBy = "famille")
    @JsonIgnore
    private Set<Enfant> enfants = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Famille nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Famille adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public Famille tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public Famille commentaires(String commentaires) {
        this.commentaires = commentaires;
        return this;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public Set<Kafala> getKafalats() {
        return kafalats;
    }

    public Famille kafalats(Set<Kafala> kafalas) {
        this.kafalats = kafalas;
        return this;
    }

    public Famille addKafalat(Kafala kafala) {
        this.kafalats.add(kafala);
        kafala.setFamille(this);
        return this;
    }

    public Famille removeKafalat(Kafala kafala) {
        this.kafalats.remove(kafala);
        kafala.setFamille(null);
        return this;
    }

    public void setKafalats(Set<Kafala> kafalas) {
        this.kafalats = kafalas;
    }

    public Set<Enfant> getEnfants() {
        return enfants;
    }

    public Famille enfants(Set<Enfant> enfants) {
        this.enfants = enfants;
        return this;
    }

    public Famille addEnfants(Enfant enfant) {
        this.enfants.add(enfant);
        enfant.setFamille(this);
        return this;
    }

    public Famille removeEnfants(Enfant enfant) {
        this.enfants.remove(enfant);
        enfant.setFamille(null);
        return this;
    }

    public void setEnfants(Set<Enfant> enfants) {
        this.enfants = enfants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Famille famille = (Famille) o;
        if (famille.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), famille.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Famille{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", tel='" + getTel() + "'" +
            ", commentaires='" + getCommentaires() + "'" +
            "}";
    }
}
