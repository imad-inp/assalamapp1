package com.assalam.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

  @Column(name = "state")
  private String state;

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public byte[] getTmpCertifMariage() {
    return tmpCertifMariage;
  }

  public void setTmpCertifMariage(byte[] tmpCertifMariage) {
    this.tmpCertifMariage = tmpCertifMariage;
  }

  public String getTmpCertifMariageContentType() {
    return tmpCertifMariageContentType;
  }

  public void setTmpCertifMariageContentType(String tmpCertifMariageContentType) {
    this.tmpCertifMariageContentType = tmpCertifMariageContentType;
  }

  public String getCertifMariageRef() {
    return certifMariageRef;
  }

  public void setCertifMariageRef(String certifMariageRef) {
    this.certifMariageRef = certifMariageRef;
  }

  public byte[] getTmpCertifDeces() {
    return tmpCertifDeces;
  }

  public void setTmpCertifDeces(byte[] tmpCertifDeces) {
    this.tmpCertifDeces = tmpCertifDeces;
  }

  public String getTmpCertifDecesContentType() {
    return tmpCertifDecesContentType;
  }

  public void setTmpCertifDecesContentType(String tmpCertifDecesContentType) {
    this.tmpCertifDecesContentType = tmpCertifDecesContentType;
  }

  public String getCertifDecesRef() {
    return certifDecesRef;
  }

  public void setCertifDecesRef(String certifDecesRef) {
    this.certifDecesRef = certifDecesRef;
  }

  public byte[] getTmpCertifDivorce() {
    return tmpCertifDivorce;
  }

  public void setTmpCertifDivorce(byte[] tmpCertifDivorce) {
    this.tmpCertifDivorce = tmpCertifDivorce;
  }

  public String getTmpCertifDivorceContentType() {
    return tmpCertifDivorceContentType;
  }

  public void setTmpCertifDivorceContentType(String tmpCertifDivorceContentType) {
    this.tmpCertifDivorceContentType = tmpCertifDivorceContentType;
  }

  public String getCertifDivorceRef() {
    return certifDivorceRef;
  }

  public void setCertifDivorceRef(String certifDivorceRef) {
    this.certifDivorceRef = certifDivorceRef;
  }

  public byte[] getTmpCinMereCopie() {
    return tmpCinMereCopie;
  }

  public void setTmpCinMereCopie(byte[] tmpcinMereCopie) {
    this.tmpCinMereCopie = tmpcinMereCopie;
  }

  public String getTmpCinMereCopieContentType() {
    return tmpCinMereCopieContentType;
  }

  public void setTmpCinMereCopieContentType(String tmpcinMereCopieContentType) {
    this.tmpCinMereCopieContentType = tmpcinMereCopieContentType;
  }

  public String getCinMereCopieRef() {
    return cinMereCopieRef;
  }

  public void setCinMereCopieRef(String cinMereCopieRef) {
    this.cinMereCopieRef = cinMereCopieRef;
  }

  public byte[] getTmpCinPereCopie() {
    return tmpCinPereCopie;
  }

  public void setTmpCinPereCopie(byte[] tmpcinPereCopie) {
    this.tmpCinPereCopie = tmpcinPereCopie;
  }

  public String getTmpCinPereCopieContentType() {
    return tmpCinPereCopieContentType;
  }

  public void setTmpCinPereCopieContentType(String tmpcinPereCopieContentType) {
    this.tmpCinPereCopieContentType = tmpcinPereCopieContentType;
  }

  public String getCinPereCopieRef() {
    return cinPereCopieRef;
  }

  public void setCinPereCopieRef(String cinPereCopieRef) {
    this.cinPereCopieRef = cinPereCopieRef;
  }

  public byte[] getTmpRamid() {
    return tmpRamid;
  }

  public void setTmpRamid(byte[] tmpRamid) {
    this.tmpRamid = tmpRamid;
  }

  public String getTmpRamidContentType() {
    return tmpRamidContentType;
  }

  public void setTmpRamidContentType(String tmpRamidContentType) {
    this.tmpRamidContentType = tmpRamidContentType;
  }

  public String getRamidRef() {
    return ramidRef;
  }

  public void setRamidRef(String ramidRef) {
    this.ramidRef = ramidRef;
  }

    @Column(name = "cin_pere")
    private String cinPere;

  @Column(name = "etat_social")
  private String etatSocial;

  @Column(name = "revenu_mensuel")
  private Long revenuMensuel;



  @Transient
  @JsonProperty
  private byte[] tmpCertifMariage;

  @Transient
  @JsonProperty
  private String tmpCertifMariageContentType;

  @Column(name = "certif_mariage_ref")
  private String certifMariageRef;

  @Transient
  @JsonProperty
  private byte[] tmpCertifDeces;

  @Transient
  @JsonProperty
  private String tmpCertifDecesContentType;

  @Column(name = "certif_deces_ref")
  private String certifDecesRef;

  @Transient
  @JsonProperty
  private byte[] tmpCertifDivorce;

  @Transient
  @JsonProperty
  private String tmpCertifDivorceContentType;

  @Column(name = "certif_divorce_ref")
  private String certifDivorceRef;

  @Transient
  @JsonProperty
  private byte[] tmpCinMereCopie;

  @Transient
  @JsonProperty
  private String tmpCinMereCopieContentType;

  @Column(name = "photo_mere_ref")
  private Long photoMereRef;

  public Long getPhotoMereRef() {
    return photoMereRef;
  }

  public void setPhotoMereRef(Long photoMereRef) {
    this.photoMereRef = photoMereRef;
  }

  public byte[] getTmpPhotoMere() {
    return tmpPhotoMere;
  }

  public void setTmpPhotoMere(byte[] tmpPhotoMere) {
    this.tmpPhotoMere = tmpPhotoMere;
  }

  public String getTmpPhotoMereContentType() {
    return tmpPhotoMereContentType;
  }

  public void setTmpPhotoMereContentType(String tmpPhotoMereContentType) {
    this.tmpPhotoMereContentType = tmpPhotoMereContentType;
  }

  @Transient
  @JsonProperty
  private byte[] tmpPhotoMere;

  @Transient
  @JsonProperty
  private String tmpPhotoMereContentType;

  @Column(name = "cin_mere_copie_ref")
  private String cinMereCopieRef;

  @Transient
  @JsonProperty
  private byte[] tmpCinPereCopie;

  @Transient
  @JsonProperty
  private String tmpCinPereCopieContentType;

  @Column(name = "cin_pere_copie_ref")
  private String cinPereCopieRef;


  @Transient
  @JsonProperty
  private byte[] tmpRamid;

  @Transient
  @JsonProperty
  private String tmpRamidContentType;

  @Column(name = "ramid_ref")
  private String ramidRef;




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
