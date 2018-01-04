package com.assalam.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class Files implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Lob
  @Column(name = "file")
  private byte[] file;

  @Column(name = "file_content_type")
  private String fileContentType;

  @Column(name = "name")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Enfant getEnfant() {
    return enfant;
  }

  public void setEnfant(Enfant enfant) {
    this.enfant = enfant;
  }

  public byte[] getFile() {
    return file;
  }

  public void setFile(byte[] file) {
    this.file = file;
  }

  public String getFileContentType() {
    return fileContentType;
  }

  public void setFileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
  }

  @ManyToOne(optional = true)
  @JoinColumn(name = "idenfant")
  private Enfant enfant;

  public Famille getFamille() {
    return famille;
  }

  public void setFamille(Famille famille) {
    this.famille = famille;
  }

  public Kafil getKafil() {
    return kafil;
  }

  public void setKafil(Kafil kafil) {
    this.kafil = kafil;
  }

  public Kafala getKafala() {
    return kafala;
  }

  public void setKafala(Kafala kafala) {
    this.kafala = kafala;
  }

  public Paiement getPaiement() {
    return paiement;
  }

  public void setPaiement(Paiement paiement) {
    this.paiement = paiement;
  }

  @ManyToOne(optional = true)
  @JoinColumn(name = "idfamille")
  private Famille famille;

  @ManyToOne(optional = true)
  @JoinColumn(name = "idkafil")
  private Kafil kafil;

  @ManyToOne(optional = true)
  @JoinColumn(name = "idkafala")
  private Kafala kafala;

  @ManyToOne(optional = true)
  @JoinColumn(name = "idpaiement")
  private Paiement paiement;
}
