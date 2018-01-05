package com.assalam.web.rest;

import com.assalam.AssalamApp;

import com.assalam.domain.Kafala;
import com.assalam.repository.KafalaRepository;
import com.assalam.service.KafalaService;
import com.assalam.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the KafalaResource REST controller.
 *
 * @see KafalaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssalamApp.class)
public class KafalaResourceIntTest {

    private static final Long DEFAULT_MONTANT = 1L;
    private static final Long UPDATED_MONTANT = 2L;

  private static final String DEFAULT_DATEDEBUT = "";
    private static final LocalDate UPDATED_DATEDEBUT = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private KafalaRepository kafalaRepository;

    @Autowired
    private KafalaService kafalaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restKafalaMockMvc;

    private Kafala kafala;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        KafalaResource kafalaResource = new KafalaResource(kafalaService);
        this.restKafalaMockMvc = MockMvcBuilders.standaloneSetup(kafalaResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Kafala createEntity(EntityManager em) {
        Kafala kafala = new Kafala()
            .montant(DEFAULT_MONTANT)
            .datedebut(DEFAULT_DATEDEBUT);
        return kafala;
    }

    @Before
    public void initTest() {
        kafala = createEntity(em);
    }

    @Test
    @Transactional
    public void createKafala() throws Exception {
        int databaseSizeBeforeCreate = kafalaRepository.findAll().size();

        // Create the Kafala
        restKafalaMockMvc.perform(post("/api/kafalas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kafala)))
            .andExpect(status().isCreated());

        // Validate the Kafala in the database
        List<Kafala> kafalaList = kafalaRepository.findAll();
        assertThat(kafalaList).hasSize(databaseSizeBeforeCreate + 1);
        Kafala testKafala = kafalaList.get(kafalaList.size() - 1);
        assertThat(testKafala.getMontant()).isEqualTo(DEFAULT_MONTANT);

    }

    @Test
    @Transactional
    public void createKafalaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kafalaRepository.findAll().size();

        // Create the Kafala with an existing ID
        kafala.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKafalaMockMvc.perform(post("/api/kafalas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kafala)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Kafala> kafalaList = kafalaRepository.findAll();
        assertThat(kafalaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKafalas() throws Exception {
        // Initialize the database
        kafalaRepository.saveAndFlush(kafala);

        // Get all the kafalaList
        restKafalaMockMvc.perform(get("/api/kafalas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kafala.getId().intValue())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.intValue())))
            .andExpect(jsonPath("$.[*].datedebut").value(hasItem(DEFAULT_DATEDEBUT.toString())));
    }

    @Test
    @Transactional
    public void getKafala() throws Exception {
        // Initialize the database
        kafalaRepository.saveAndFlush(kafala);

        // Get the kafala
        restKafalaMockMvc.perform(get("/api/kafalas/{id}", kafala.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kafala.getId().intValue()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.intValue()))
            .andExpect(jsonPath("$.datedebut").value(DEFAULT_DATEDEBUT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingKafala() throws Exception {
        // Get the kafala
        restKafalaMockMvc.perform(get("/api/kafalas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKafala() throws Exception {
        // Initialize the database
        kafalaService.save(kafala);

        int databaseSizeBeforeUpdate = kafalaRepository.findAll().size();

        // Update the kafala
        Kafala updatedKafala = kafalaRepository.findOne(kafala.getId());


        restKafalaMockMvc.perform(put("/api/kafalas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedKafala)))
            .andExpect(status().isOk());

        // Validate the Kafala in the database
        List<Kafala> kafalaList = kafalaRepository.findAll();
        assertThat(kafalaList).hasSize(databaseSizeBeforeUpdate);
        Kafala testKafala = kafalaList.get(kafalaList.size() - 1);
        assertThat(testKafala.getMontant()).isEqualTo(UPDATED_MONTANT);

    }

    @Test
    @Transactional
    public void updateNonExistingKafala() throws Exception {
        int databaseSizeBeforeUpdate = kafalaRepository.findAll().size();

        // Create the Kafala

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restKafalaMockMvc.perform(put("/api/kafalas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kafala)))
            .andExpect(status().isCreated());

        // Validate the Kafala in the database
        List<Kafala> kafalaList = kafalaRepository.findAll();
        assertThat(kafalaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteKafala() throws Exception {
        // Initialize the database
        kafalaService.save(kafala);

        int databaseSizeBeforeDelete = kafalaRepository.findAll().size();

        // Get the kafala
        restKafalaMockMvc.perform(delete("/api/kafalas/{id}", kafala.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Kafala> kafalaList = kafalaRepository.findAll();
        assertThat(kafalaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kafala.class);
        Kafala kafala1 = new Kafala();
        kafala1.setId(1L);
        Kafala kafala2 = new Kafala();
        kafala2.setId(kafala1.getId());
        assertThat(kafala1).isEqualTo(kafala2);
        kafala2.setId(2L);
        assertThat(kafala1).isNotEqualTo(kafala2);
        kafala1.setId(null);
        assertThat(kafala1).isNotEqualTo(kafala2);
    }
}
