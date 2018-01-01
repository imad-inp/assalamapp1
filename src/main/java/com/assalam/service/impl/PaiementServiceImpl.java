package com.assalam.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.assalam.domain.Demandeadhesion;
import com.assalam.domain.Kafala;
import com.assalam.service.PaiementService;
import com.assalam.service.util.PDFUtil;
import com.assalam.domain.Paiement;
import com.assalam.domain.enumeration.Statut;
import com.assalam.repository.KafalaRepository;
import com.assalam.repository.PaiementRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing Paiement.
 */
@Service
@Transactional
public class PaiementServiceImpl implements PaiementService {

  private final Logger log = LoggerFactory.getLogger(PaiementServiceImpl.class);

  private final PaiementRepository paiementRepository;

  @Autowired
  private KafalaRepository kafalaRepository;

  public PaiementServiceImpl(PaiementRepository paiementRepository) {
    this.paiementRepository = paiementRepository;
  }

  /**
   * Save a paiement.
   * 
   * @param paiement
   *          the entity to save
   * @return the persisted entity
   */
  @Override
  public Paiement save(Paiement paiement, boolean isSaving) {

    log.debug("Request to save Paiement : {}", paiement);
    Long moisPayesToRemove = 0L;
    // if we're in update mode, get the paid months of the previous payment.
    if (isSaving) {
      moisPayesToRemove = paiementRepository.getOne(paiement.getId()).getMoispayes();
    }

    Paiement paiementToReturn = paiementRepository.save(paiement);

    Kafala kafala = kafalaRepository.findOne(paiement.getKafala().getId());

    log.debug("kafala update mois payes" + kafala);
    kafala.setMoispayes(kafala.getMoispayes() + paiement.getMoispayes() - moisPayesToRemove);

    return paiementToReturn;
  }

  /**
   * Get all the paiements.
   * 
   * @param pageable
   *          the pagination information
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public Page<Paiement> findAll(Pageable pageable) {
    log.debug("Request to get all Paiements");
    return paiementRepository.findAll(pageable);
  }

  /**
   * Get one payments by kafala ID.
   * 
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public List<Paiement> findByKafalaId(String kafalaId) {
    log.debug("Request to get Payments by kafala id : {}", kafalaId);
    return paiementRepository.findByKafalaId(Long.valueOf(kafalaId));
  }

  /**
   * Get one paiement by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Paiement findOne(Long id) {
    log.debug("Request to get Paiement : {}", id);
    return paiementRepository.findOne(id);
  }

  /**
   * Delete the paiement by id.
   * 
   * @param id
   *          the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete Paiement : {}", id);

    Paiement paiement = findOne(id);
    Kafala kafala = kafalaRepository.findOne(paiement.getKafala().getId());
    Long kafalaMoispayes = kafala.getMoispayes();
    paiementRepository.delete(id);
    kafala.setMoispayes(kafalaMoispayes - paiement.getMoispayes());

  }

  /**
   * Get paiments as pdf for the kafil ID in input{@inheritDoc}
   */
  @Override
  public byte[] getPaiementsAsPdf(String kafalaId) {
    Document document = new Document();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    BaseFont bf = null;
    Font f = FontFactory.getFont(PDFUtil.FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    try {
      PdfWriter.getInstance(document, new FileOutputStream(new File("k.pdf")));
      bf = BaseFont.createFont("arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    }
    catch (DocumentException | IOException e) {

      e.printStackTrace();
    }

    document.open();

    Chunk chunk = new Chunk("orld" + "\u0627\u0644\u0633\u0639\u0631", new Font(bf, 22));

    try {
      document.add(chunk);
    }
    catch (DocumentException e) {

      e.printStackTrace();
    }
    document.close();

    return byteArrayOutputStream.toByteArray();
  }

  @Override
  public byte[] getPaiementReceipt(Paiement paiement) {

    Document document = new Document();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    BaseFont bf = null;
    PdfWriter writer = null;
    try {
      bf = BaseFont.createFont(PDFUtil.FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
      writer = PdfWriter.getInstance(document, byteArrayOutputStream);

      document.open();
      List<Phrase> phrases = new ArrayList<Phrase>();
      Phrase p2 = new Phrase("");
      p2.add(new Chunk(" اسم الطفل : ", new Font(bf, 22)));
      p2.add(new Chunk(paiement.getKafala().getEnfant().getFullName()));
      phrases.add(p2);

      Phrase p = new Phrase("");
      p.add(new Chunk(" اسم الكفيل : ", new Font(bf, 22)));
      p.add(paiement.getKafala().getKafil().getFullName());
      phrases.add(p);

      Phrase p3 = new Phrase("");
      p3.add(new Chunk(" عدد الاشهر : ", new Font(bf, 22)));
      p3.add(new Chunk(paiement.getMoispayes().toString()));
      phrases.add(p3);

      Phrase p1 = new Phrase("");
      p1.add(new Chunk(" مبلغ الكفالة : ", new Font(bf, 22)));
      p1.add(new Chunk(paiement.getMontant().toString()));
      phrases.add(p1);


      Phrase p5 = new Phrase("");
      p5.add(new Chunk(" نوع الاداء : ", new Font(bf, 22)));
      p5.add(new Chunk(paiement.getType().toString()));
      phrases.add(p5);

      Phrase p4 = new Phrase("");
      p4.add(new Chunk(" تاريخ الاداء : ", new Font(bf, 22)));
      p4.add(new Chunk(paiement.getDate().toString()));
      phrases.add(p4);


      PdfPTable table = new PdfPTable(2);
      table.setTotalWidth(200);
      table.setWidths(new int[] { 10, 10 });
      table.setHorizontalAlignment(Element.ALIGN_RIGHT);
      PdfPCell cell = new PdfPCell();
      for (Phrase phrase : phrases) {
        cell.addElement(phrase);
        table.addCell(cell);
      }

      document.add(table);
    }
    catch (DocumentException | IOException e) {

      e.printStackTrace();
    }

    document.close();

    return byteArrayOutputStream.toByteArray();
  }

}

