package solstice.data.entity;

import solstice.data.RestTemplates.CharacterPublic;
import solstice.data.repository.CharacterRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public abstract class AbstractCharacterTest {

    @Autowired
    protected TestEntityManager entityManager;

    @Autowired
    protected CharacterRepository characterRepository;

    protected static CharacterEntity characterEntity;

    protected static CharacterPublic characterPublic;

    @Before
    public void setUp(){
        if (characterEntity == null && characterPublic == null){
            CharacterEntity pilot = new CharacterEntity(1326083433);
            RestTemplate restTemplate = new RestTemplate();
            String rUrl = String.format("https://esi.evetech.net/latest/characters/%s/?datasource=tranquility", 1326083433);
            ResponseEntity<CharacterPublic> c =
                    restTemplate.exchange(rUrl, HttpMethod.GET, null, CharacterPublic.class);
            assertEquals(200, c.getStatusCodeValue());
            characterPublic = c.getBody();
            pilot.updateFromRestTemplate(characterPublic);
            CorporationEntity corp = new CorporationEntity(761955047);
            pilot.setCorporationEntity(corp);
            entityManager.persist(corp);
            entityManager.persistAndFlush(pilot);
            characterEntity = characterRepository.findOneById(pilot.getId()).get();
        }
    }
}