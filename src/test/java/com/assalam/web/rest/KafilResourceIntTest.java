package com.assalam.web.rest;

import com.assalam.AssalamApp;

import com.assalam.domain.Kafil;
import com.assalam.repository.KafilRepository;
import com.assalam.service.KafilService;
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
 * Test class for the KafilResource REST controller.
 *
 * @see KafilResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssalamApp.class)
public class KafilResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MEMBRE = false;
    private static final Boolean UPDATED_MEMBRE = true;

    private static final String DEFAULT_COMMENTAIRES = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTAIRES = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    @Autowired
    private KafilRepository kafilRepository;

    @Autowired
    private KafilService kafilService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restKafilMockMvc;

    private Kafil kafil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        KafilResource kafilResource = new KafilResource(kafilService);
        this.restKafilMockMvc = MockMvcBuilders.standaloneSetup(kafilResource)
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
    public static Kafil createEntity(EntityManager em) {
        Kafil kafil = new Kafil()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adresse(DEFAULT_ADRESSE)
            .tel(DEFAULT_TEL)
            .membre(DEFAULT_MEMBRE)
            .commentaires(DEFAULT_COMMENTAIRES)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE);
        return kafil;
    }

    @Before
    public void initTest() {
        kafil = createEntity(em);
    }

    @Test
    @Transactional
    public void createKafil() throws Exception {
        int databaseSizeBeforeCreate = kafilRepository.findAll().size();

        // Create the Kafil
        restKafilMockMvc.perform(post("/api/kafils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kafil)))
            .andExpect(status().isCreated());

        // Validate the Kafil in the database
        List<Kafil> kafilList = kafilRepository.findAll();
        assertThat(kafilList).hasSize(databaseSizeBeforeCreate + 1);
        Kafil testKafil = kafilList.get(kafilList.size() - 1);
        assertThat(testKafil.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testKafil.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testKafil.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testKafil.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testKafil.isMembre()).isEqualTo(DEFAULT_MEMBRE);
        assertThat(testKafil.getCommentaires()).isEqualTo(DEFAULT_COMMENTAIRES);
        assertThat(testKafil.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testKafil.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createKafilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kafilRepository.findAll().size();

        // Create the Kafil with an existing ID
        kafil.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKafilMockMvc.perform(post("/api/kafils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kafil)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Kafil> kafilList = kafilRepository.findAll();
        assertThat(kafilList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllKafils() throws Exception {
        // Initialize the database
        kafilRepository.saveAndFlush(kafil);

        // Get all the kafilList
        restKafilMockMvc.perform(get("/api/kafils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kafil.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL.toString())))
            .andExpect(jsonPath("$.[*].membre").value(hasItem(DEFAULT_MEMBRE.booleanValue())))
            .andExpect(jsonPath("$.[*].commentaires").value(hasItem(DEFAULT_COMMENTAIRES.toString())))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))));
    }

    @Test
    @Transactional
    public void getKafil() throws Exception {
        // Initialize the database
        kafilRepository.saveAndFlush(kafil);

        // Get the kafil
        restKafilMockMvc.perform(get("/api/kafils/{id}", kafil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kafil.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL.toString()))
            .andExpect(jsonPath("$.membre").value(DEFAULT_MEMBRE.booleanValue()))
            .andExpect(jsonPath("$.commentaires").value(DEFAULT_COMMENTAIRES.toString()))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)));
    }

    @Test
    @Transactional
    public void getNonExistingKafil() throws Exception {
        // Get the kafil
        restKafilMockMvc.perform(get("/api/kafils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKafil() throws Exception {
        // Initialize the database
        kafilService.save(kafil);

        int databaseSizeBeforeUpdate = kafilRepository.findAll().size();

        // Update the kafil
        Kafil updatedKafil = kafilRepository.findOne(kafil.getId());
        updatedKafil
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adresse(UPDATED_ADRESSE)
            .tel(UPDATED_TEL)
            .membre(UPDATED_MEMBRE)
            .commentaires(UPDATED_COMMENTAIRES)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE);

        restKafilMockMvc.perform(put("/api/kafils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedKafil)))
            .andExpect(status().isOk());

        // Validate the Kafil in the database
        List<Kafil> kafilList = kafilRepository.findAll();
        assertThat(kafilList).hasSize(databaseSizeBeforeUpdate);
        Kafil testKafil = kafilList.get(kafilList.size() - 1);
        assertThat(testKafil.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testKafil.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testKafil.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testKafil.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testKafil.isMembre()).isEqualTo(UPDATED_MEMBRE);
        assertThat(testKafil.getCommentaires()).isEqualTo(UPDATED_COMMENTAIRES);
        assertThat(testKafil.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testKafil.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingKafil() throws Exception {
        int databaseSizeBeforeUpdate = kafilRepository.findAll().size();

        // Create the Kafil

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restKafilMockMvc.perform(put("/api/kafils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kafil)))
            .andExpect(status().isCreated());

        // Validate the Kafil in the database
        List<Kafil> kafilList = kafilRepository.findAll();
        assertThat(kafilList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteKafil() throws Exception {
        // Initialize the database
        kafilService.save(kafil);

        int databaseSizeBeforeDelete = kafilRepository.findAll().size();

        // Get the kafil
        restKafilMockMvc.perform(delete("/api/kafils/{id}", kafil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Kafil> kafilList = kafilRepository.findAll();
        assertThat(kafilList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kafil.class);
        Kafil kafil1 = new Kafil();
        kafil1.setId(1L);
        Kafil kafil2 = new Kafil();
        kafil2.setId(kafil1.getId());
        assertThat(kafil1).isEqualTo(kafil2);
        kafil2.setId(2L);
        assertThat(kafil1).isNotEqualTo(kafil2);
        kafil1.setId(null);
        assertThat(kafil1).isNotEqualTo(kafil2);
    }
}
