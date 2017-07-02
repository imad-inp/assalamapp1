package com.assalam.web.rest;

import com.assalam.AssalamApp;

import com.assalam.domain.Demandeadhesion;
import com.assalam.repository.DemandeadhesionRepository;
import com.assalam.service.DemandeadhesionService;
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

import com.assalam.domain.enumeration.Statut;
/**
 * Test class for the DemandeadhesionResource REST controller.
 *
 * @see DemandeadhesionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssalamApp.class)
public class DemandeadhesionResourceIntTest {

    private static final LocalDate DEFAULT_DATEDEMANDE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEDEMANDE = LocalDate.now(ZoneId.systemDefault());

    private static final Statut DEFAULT_STATUT = Statut.OUVERTE;
    private static final Statut UPDATED_STATUT = Statut.EN_COURS;

    @Autowired
    private DemandeadhesionRepository demandeadhesionRepository;

    @Autowired
    private DemandeadhesionService demandeadhesionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDemandeadhesionMockMvc;

    private Demandeadhesion demandeadhesion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DemandeadhesionResource demandeadhesionResource = new DemandeadhesionResource(demandeadhesionService);
        this.restDemandeadhesionMockMvc = MockMvcBuilders.standaloneSetup(demandeadhesionResource)
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
    public static Demandeadhesion createEntity(EntityManager em) {
        Demandeadhesion demandeadhesion = new Demandeadhesion()
            .datedemande(DEFAULT_DATEDEMANDE)
            .statut(DEFAULT_STATUT);
        return demandeadhesion;
    }

    @Before
    public void initTest() {
        demandeadhesion = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemandeadhesion() throws Exception {
        int databaseSizeBeforeCreate = demandeadhesionRepository.findAll().size();

        // Create the Demandeadhesion
        restDemandeadhesionMockMvc.perform(post("/api/demandeadhesions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeadhesion)))
            .andExpect(status().isCreated());

        // Validate the Demandeadhesion in the database
        List<Demandeadhesion> demandeadhesionList = demandeadhesionRepository.findAll();
        assertThat(demandeadhesionList).hasSize(databaseSizeBeforeCreate + 1);
        Demandeadhesion testDemandeadhesion = demandeadhesionList.get(demandeadhesionList.size() - 1);
        assertThat(testDemandeadhesion.getDatedemande()).isEqualTo(DEFAULT_DATEDEMANDE);
        assertThat(testDemandeadhesion.getStatut()).isEqualTo(DEFAULT_STATUT);
    }

    @Test
    @Transactional
    public void createDemandeadhesionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeadhesionRepository.findAll().size();

        // Create the Demandeadhesion with an existing ID
        demandeadhesion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeadhesionMockMvc.perform(post("/api/demandeadhesions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeadhesion)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Demandeadhesion> demandeadhesionList = demandeadhesionRepository.findAll();
        assertThat(demandeadhesionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDemandeadhesions() throws Exception {
        // Initialize the database
        demandeadhesionRepository.saveAndFlush(demandeadhesion);

        // Get all the demandeadhesionList
        restDemandeadhesionMockMvc.perform(get("/api/demandeadhesions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demandeadhesion.getId().intValue())))
            .andExpect(jsonPath("$.[*].datedemande").value(hasItem(DEFAULT_DATEDEMANDE.toString())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())));
    }

    @Test
    @Transactional
    public void getDemandeadhesion() throws Exception {
        // Initialize the database
        demandeadhesionRepository.saveAndFlush(demandeadhesion);

        // Get the demandeadhesion
        restDemandeadhesionMockMvc.perform(get("/api/demandeadhesions/{id}", demandeadhesion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(demandeadhesion.getId().intValue()))
            .andExpect(jsonPath("$.datedemande").value(DEFAULT_DATEDEMANDE.toString()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDemandeadhesion() throws Exception {
        // Get the demandeadhesion
        restDemandeadhesionMockMvc.perform(get("/api/demandeadhesions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemandeadhesion() throws Exception {
        // Initialize the database
        demandeadhesionService.save(demandeadhesion);

        int databaseSizeBeforeUpdate = demandeadhesionRepository.findAll().size();

        // Update the demandeadhesion
        Demandeadhesion updatedDemandeadhesion = demandeadhesionRepository.findOne(demandeadhesion.getId());
        updatedDemandeadhesion
            .datedemande(UPDATED_DATEDEMANDE)
            .statut(UPDATED_STATUT);

        restDemandeadhesionMockMvc.perform(put("/api/demandeadhesions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDemandeadhesion)))
            .andExpect(status().isOk());

        // Validate the Demandeadhesion in the database
        List<Demandeadhesion> demandeadhesionList = demandeadhesionRepository.findAll();
        assertThat(demandeadhesionList).hasSize(databaseSizeBeforeUpdate);
        Demandeadhesion testDemandeadhesion = demandeadhesionList.get(demandeadhesionList.size() - 1);
        assertThat(testDemandeadhesion.getDatedemande()).isEqualTo(UPDATED_DATEDEMANDE);
        assertThat(testDemandeadhesion.getStatut()).isEqualTo(UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void updateNonExistingDemandeadhesion() throws Exception {
        int databaseSizeBeforeUpdate = demandeadhesionRepository.findAll().size();

        // Create the Demandeadhesion

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDemandeadhesionMockMvc.perform(put("/api/demandeadhesions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeadhesion)))
            .andExpect(status().isCreated());

        // Validate the Demandeadhesion in the database
        List<Demandeadhesion> demandeadhesionList = demandeadhesionRepository.findAll();
        assertThat(demandeadhesionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDemandeadhesion() throws Exception {
        // Initialize the database
        demandeadhesionService.save(demandeadhesion);

        int databaseSizeBeforeDelete = demandeadhesionRepository.findAll().size();

        // Get the demandeadhesion
        restDemandeadhesionMockMvc.perform(delete("/api/demandeadhesions/{id}", demandeadhesion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Demandeadhesion> demandeadhesionList = demandeadhesionRepository.findAll();
        assertThat(demandeadhesionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demandeadhesion.class);
        Demandeadhesion demandeadhesion1 = new Demandeadhesion();
        demandeadhesion1.setId(1L);
        Demandeadhesion demandeadhesion2 = new Demandeadhesion();
        demandeadhesion2.setId(demandeadhesion1.getId());
        assertThat(demandeadhesion1).isEqualTo(demandeadhesion2);
        demandeadhesion2.setId(2L);
        assertThat(demandeadhesion1).isNotEqualTo(demandeadhesion2);
        demandeadhesion1.setId(null);
        assertThat(demandeadhesion1).isNotEqualTo(demandeadhesion2);
    }
}
