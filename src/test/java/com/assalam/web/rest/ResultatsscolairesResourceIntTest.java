package com.assalam.web.rest;

import com.assalam.AssalamApp;

import com.assalam.domain.Resultatsscolaires;
import com.assalam.repository.ResultatsscolairesRepository;
import com.assalam.service.ResultatsscolairesService;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ResultatsscolairesResource REST controller.
 *
 * @see ResultatsscolairesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssalamApp.class)
public class ResultatsscolairesResourceIntTest {

  private static final String DEFAULT_ANNEE = "1";

  private static final String UPDATED_ANNEE = "2";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ResultatsscolairesRepository resultatsscolairesRepository;

    @Autowired
    private ResultatsscolairesService resultatsscolairesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResultatsscolairesMockMvc;

    private Resultatsscolaires resultatsscolaires;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ResultatsscolairesResource resultatsscolairesResource = new ResultatsscolairesResource(resultatsscolairesService);
        this.restResultatsscolairesMockMvc = MockMvcBuilders.standaloneSetup(resultatsscolairesResource)
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
    public static Resultatsscolaires createEntity(EntityManager em) {
        Resultatsscolaires resultatsscolaires = new Resultatsscolaires()
            .annee(DEFAULT_ANNEE)
            .description(DEFAULT_DESCRIPTION);
        return resultatsscolaires;
    }

    @Before
    public void initTest() {
        resultatsscolaires = createEntity(em);
    }

    @Test
    @Transactional
    public void createResultatsscolaires() throws Exception {
        int databaseSizeBeforeCreate = resultatsscolairesRepository.findAll().size();

        // Create the Resultatsscolaires
        restResultatsscolairesMockMvc.perform(post("/api/resultatsscolaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultatsscolaires)))
            .andExpect(status().isCreated());

        // Validate the Resultatsscolaires in the database
        List<Resultatsscolaires> resultatsscolairesList = resultatsscolairesRepository.findAll();
        assertThat(resultatsscolairesList).hasSize(databaseSizeBeforeCreate + 1);
        Resultatsscolaires testResultatsscolaires = resultatsscolairesList.get(resultatsscolairesList.size() - 1);
        assertThat(testResultatsscolaires.getAnnee()).isEqualTo(DEFAULT_ANNEE);
        assertThat(testResultatsscolaires.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createResultatsscolairesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resultatsscolairesRepository.findAll().size();

        // Create the Resultatsscolaires with an existing ID
        resultatsscolaires.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResultatsscolairesMockMvc.perform(post("/api/resultatsscolaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultatsscolaires)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Resultatsscolaires> resultatsscolairesList = resultatsscolairesRepository.findAll();
        assertThat(resultatsscolairesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllResultatsscolaires() throws Exception {
        // Initialize the database
        resultatsscolairesRepository.saveAndFlush(resultatsscolaires);

        // Get all the resultatsscolairesList
        restResultatsscolairesMockMvc.perform(get("/api/resultatsscolaires?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resultatsscolaires.getId().intValue())))
            .andExpect(jsonPath("$.[*].annee").value(hasItem(DEFAULT_ANNEE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getResultatsscolaires() throws Exception {
        // Initialize the database
        resultatsscolairesRepository.saveAndFlush(resultatsscolaires);

        // Get the resultatsscolaires
        restResultatsscolairesMockMvc.perform(get("/api/resultatsscolaires/{id}", resultatsscolaires.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resultatsscolaires.getId().intValue()))
            .andExpect(jsonPath("$.annee").value(DEFAULT_ANNEE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingResultatsscolaires() throws Exception {
        // Get the resultatsscolaires
        restResultatsscolairesMockMvc.perform(get("/api/resultatsscolaires/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResultatsscolaires() throws Exception {
        // Initialize the database
        resultatsscolairesService.save(resultatsscolaires);

        int databaseSizeBeforeUpdate = resultatsscolairesRepository.findAll().size();

        // Update the resultatsscolaires
        Resultatsscolaires updatedResultatsscolaires = resultatsscolairesRepository.findOne(resultatsscolaires.getId());
        updatedResultatsscolaires
            .annee(UPDATED_ANNEE)
            .description(UPDATED_DESCRIPTION);

        restResultatsscolairesMockMvc.perform(put("/api/resultatsscolaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedResultatsscolaires)))
            .andExpect(status().isOk());

        // Validate the Resultatsscolaires in the database
        List<Resultatsscolaires> resultatsscolairesList = resultatsscolairesRepository.findAll();
        assertThat(resultatsscolairesList).hasSize(databaseSizeBeforeUpdate);
        Resultatsscolaires testResultatsscolaires = resultatsscolairesList.get(resultatsscolairesList.size() - 1);
        assertThat(testResultatsscolaires.getAnnee()).isEqualTo(UPDATED_ANNEE);
        assertThat(testResultatsscolaires.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingResultatsscolaires() throws Exception {
        int databaseSizeBeforeUpdate = resultatsscolairesRepository.findAll().size();

        // Create the Resultatsscolaires

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResultatsscolairesMockMvc.perform(put("/api/resultatsscolaires")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resultatsscolaires)))
            .andExpect(status().isCreated());

        // Validate the Resultatsscolaires in the database
        List<Resultatsscolaires> resultatsscolairesList = resultatsscolairesRepository.findAll();
        assertThat(resultatsscolairesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteResultatsscolaires() throws Exception {
        // Initialize the database
        resultatsscolairesService.save(resultatsscolaires);

        int databaseSizeBeforeDelete = resultatsscolairesRepository.findAll().size();

        // Get the resultatsscolaires
        restResultatsscolairesMockMvc.perform(delete("/api/resultatsscolaires/{id}", resultatsscolaires.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Resultatsscolaires> resultatsscolairesList = resultatsscolairesRepository.findAll();
        assertThat(resultatsscolairesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Resultatsscolaires.class);
        Resultatsscolaires resultatsscolaires1 = new Resultatsscolaires();
        resultatsscolaires1.setId(1L);
        Resultatsscolaires resultatsscolaires2 = new Resultatsscolaires();
        resultatsscolaires2.setId(resultatsscolaires1.getId());
        assertThat(resultatsscolaires1).isEqualTo(resultatsscolaires2);
        resultatsscolaires2.setId(2L);
        assertThat(resultatsscolaires1).isNotEqualTo(resultatsscolaires2);
        resultatsscolaires1.setId(null);
        assertThat(resultatsscolaires1).isNotEqualTo(resultatsscolaires2);
    }
}
