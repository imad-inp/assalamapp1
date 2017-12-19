package com.assalam.web.rest;

import com.assalam.AssalamApp;

import com.assalam.domain.Enfant;
import com.assalam.repository.EnfantRepository;
import com.assalam.service.EnfantService;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EnfantResource REST controller.
 *
 * @see EnfantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssalamApp.class)
public class EnfantResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_COMMENTAIRES = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRES = "BBBBBBBBBB";

    @Autowired
    private EnfantRepository enfantRepository;

    @Autowired
    private EnfantService enfantService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEnfantMockMvc;

    private Enfant enfant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EnfantResource enfantResource = new EnfantResource(enfantService);
        this.restEnfantMockMvc = MockMvcBuilders.standaloneSetup(enfantResource)
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
    public static Enfant createEntity(EntityManager em) {
        Enfant enfant = new Enfant()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .age(DEFAULT_AGE)
            .adresse(DEFAULT_ADRESSE)
            .tel(DEFAULT_TEL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .commentaires(DEFAULT_COMMENTAIRES);
        return enfant;
    }

    @Before
    public void initTest() {
        enfant = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnfant() throws Exception {
        int databaseSizeBeforeCreate = enfantRepository.findAll().size();

        // Create the Enfant
        restEnfantMockMvc.perform(post("/api/enfants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enfant)))
            .andExpect(status().isCreated());

        // Validate the Enfant in the database
        List<Enfant> enfantList = enfantRepository.findAll();
        assertThat(enfantList).hasSize(databaseSizeBeforeCreate + 1);
        Enfant testEnfant = enfantList.get(enfantList.size() - 1);
        assertThat(testEnfant.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEnfant.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        // assertThat(testEnfant.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testEnfant.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testEnfant.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testEnfant.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testEnfant.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testEnfant.getCommentaires()).isEqualTo(DEFAULT_COMMENTAIRES);
    }

    @Test
    @Transactional
    public void createEnfantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = enfantRepository.findAll().size();

        // Create the Enfant with an existing ID
        enfant.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEnfantMockMvc.perform(post("/api/enfants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enfant)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Enfant> enfantList = enfantRepository.findAll();
        assertThat(enfantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllEnfants() throws Exception {
        // Initialize the database
        enfantRepository.saveAndFlush(enfant);

        // Get all the enfantList
        restEnfantMockMvc.perform(get("/api/enfants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(enfant.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL.toString())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].commentaires").value(hasItem(DEFAULT_COMMENTAIRES.toString())));
    }

    @Test
    @Transactional
    public void getEnfant() throws Exception {
        // Initialize the database
        enfantRepository.saveAndFlush(enfant);

        // Get the enfant
        restEnfantMockMvc.perform(get("/api/enfants/{id}", enfant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enfant.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL.toString()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.commentaires").value(DEFAULT_COMMENTAIRES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnfant() throws Exception {
        // Get the enfant
        restEnfantMockMvc.perform(get("/api/enfants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnfant() throws Exception {
        // Initialize the database
        enfantService.save(enfant);

        int databaseSizeBeforeUpdate = enfantRepository.findAll().size();

        // Update the enfant
        Enfant updatedEnfant = enfantRepository.findOne(enfant.getId());
        updatedEnfant
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .age(UPDATED_AGE)
            .adresse(UPDATED_ADRESSE)
            .tel(UPDATED_TEL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .commentaires(UPDATED_COMMENTAIRES);

        restEnfantMockMvc.perform(put("/api/enfants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedEnfant)))
            .andExpect(status().isOk());

        // Validate the Enfant in the database
        List<Enfant> enfantList = enfantRepository.findAll();
        assertThat(enfantList).hasSize(databaseSizeBeforeUpdate);
        Enfant testEnfant = enfantList.get(enfantList.size() - 1);
        assertThat(testEnfant.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEnfant.getPrenom()).isEqualTo(UPDATED_PRENOM);
        // assertThat(testEnfant.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testEnfant.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testEnfant.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testEnfant.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testEnfant.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testEnfant.getCommentaires()).isEqualTo(UPDATED_COMMENTAIRES);
    }

    @Test
    @Transactional
    public void updateNonExistingEnfant() throws Exception {
        int databaseSizeBeforeUpdate = enfantRepository.findAll().size();

        // Create the Enfant

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restEnfantMockMvc.perform(put("/api/enfants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(enfant)))
            .andExpect(status().isCreated());

        // Validate the Enfant in the database
        List<Enfant> enfantList = enfantRepository.findAll();
        assertThat(enfantList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteEnfant() throws Exception {
        // Initialize the database
        enfantService.save(enfant);

        int databaseSizeBeforeDelete = enfantRepository.findAll().size();

        // Get the enfant
        restEnfantMockMvc.perform(delete("/api/enfants/{id}", enfant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Enfant> enfantList = enfantRepository.findAll();
        assertThat(enfantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Enfant.class);
        Enfant enfant1 = new Enfant();
        enfant1.setId(1L);
        Enfant enfant2 = new Enfant();
        enfant2.setId(enfant1.getId());
        assertThat(enfant1).isEqualTo(enfant2);
        enfant2.setId(2L);
        assertThat(enfant1).isNotEqualTo(enfant2);
        enfant1.setId(null);
        assertThat(enfant1).isNotEqualTo(enfant2);
    }
}
