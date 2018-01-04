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
 * A Kafala.
 */
@Entity
@Table(name = "kafala")
public class Kafala implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "montant")
  private Long montant;

  @Column(name = "start_date")
  private String startDate;

  @Column(name = "end_date")
  private String endDate;


  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  @Column(name = "mois_payes", nullable = false)
  private Long moispayes = new Long(0);

  @Column(name = "state")
  private String state = "EN_COURS";

  @Lob
  @Column(name = "engagement")
  private byte[] engagement;

  @Column(name = "engagement_content_type")
  private String engagementContentType;



  public String getTmpEngagementContentType() {
    return tmpEngagementContentType;
  }

  public void setTmpEngagementContentType(String tmpEngagementContentType) {
    this.tmpEngagementContentType = tmpEngagementContentType;
  }

  @Column(name = "engagement_ref")
  private Long engagementRef;

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public Long getEngagementRef() {
    return engagementRef;
  }

  public void setEngagementRef(Long long1) {
    this.engagementRef = long1;
  }

  public byte[] getTmpEngagement() {
    return tmpEngagement;
  }

  public void setTmpEngagement(byte[] tmpEngagement) {
    this.tmpEngagement = tmpEngagement;
  }



  public String getEndDate() {
    return endDate;
  }

  @Transient
  @JsonProperty
  private byte[] tmpEngagement;

  @Transient
  @JsonProperty
  private String tmpEngagementContentType;

  public String getEngagementContentType() {
    return engagementContentType;
  }

  public void setEngagementContentType(String engagementContentType) {
    this.engagementContentType = engagementContentType;
  }

  public byte[] getEngagement() {
    return engagement;
  }

  public void setEngagement(byte[] engagement) {
    this.engagement = engagement;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @OneToMany(mappedBy = "kafala", fetch = FetchType.LAZY)
  @JsonIgnore
  private Set<Paiement> paiements = new HashSet<>();

  @ManyToOne
  private Enfant enfant;

  @ManyToOne
  private Famille famille;

  @ManyToOne
  private Kafil kafil;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMontant() {
    return montant;
  }

  public Kafala montant(Long montant) {
    this.montant = montant;
    return this;
  }

  public void setMontant(Long montant) {
    this.montant = montant;
  }



  public Kafala datedebut(String startDate) {
    this.startDate = startDate;
    return this;
  }



  public Long getMoispayes() {
    return this.moispayes;
  }

  public Kafala moispayes(Long moispayes) {
    this.moispayes = moispayes;
    return this;
  }

  public void setMoispayes(Long moispayes) {
    this.moispayes = moispayes;
  }

  public Set<Paiement> getPaiements() {
    return paiements;
  }

  public Kafala paiements(Set<Paiement> paiements) {
    this.paiements = paiements;
    return this;
  }

  public Kafala addPaiements(Paiement paiement) {
    this.paiements.add(paiement);
    paiement.setKafala(this);
    return this;
  }

  public Kafala removePaiements(Paiement paiement) {
    this.paiements.remove(paiement);
    paiement.setKafala(null);
    return this;
  }

  public void setPaiements(Set<Paiement> paiements) {
    this.paiements = paiements;
  }

  public Enfant getEnfant() {
    return enfant;
  }

  public Kafala enfant(Enfant enfant) {
    this.enfant = enfant;
    return this;
  }

  public void setEnfant(Enfant enfant) {
    this.enfant = enfant;
  }

  public Famille getFamille() {
    return famille;
  }

  public Kafala famille(Famille famille) {
    this.famille = famille;
    return this;
  }

  public void setFamille(Famille famille) {
    this.famille = famille;
  }

  public Kafil getKafil() {
    return kafil;
  }

  public Kafala kafil(Kafil kafil) {
    this.kafil = kafil;
    return this;
  }

  public void setKafil(Kafil kafil) {
    this.kafil = kafil;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Kafala kafala = (Kafala)o;
    if (kafala.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), kafala.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "Kafala{" +
        "id=" + getId() +
        ", montant='" + getMontant() + "'" +
        ", datedebut='" + "'" +
        "}";
  }
}
