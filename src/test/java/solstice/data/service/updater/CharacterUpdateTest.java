package solstice.data.service.updater;

import solstice.data.entity.CharacterEntity;
import solstice.data.entity.CorporationEntity;
import solstice.data.repository.CharacterRepository;
import solstice.data.repository.CorporationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = NONE)
public class CharacterUpdateTest {
    @Autowired
    protected CharacterUpdate characterUpdate;

    @Autowired
    protected CharacterRepository characterRepository;

    @Autowired
    protected CorporationRepository corporationRepository;

    @Before
    public void setUp() throws Exception {
        CharacterEntity invalidCharacter = new CharacterEntity(2);
        invalidCharacter.getMeta().setInvalid();
        characterRepository.save(invalidCharacter);
        characterRepository.save(new CharacterEntity(1));
        characterRepository.save(new CharacterEntity(1326083433));
        CorporationEntity corp = new CorporationEntity(5);
        corp.setName("Test Corp");
        corporationRepository.save(corp);
        assert(characterRepository.count() == 3);
        assert(corporationRepository.count() == 1);
    }

    @Test
    public void loadCorporationNew(){
        CharacterEntity c = characterRepository.findOneById(1).get();
        c.setCorporationIdTransient(6);
        characterUpdate.loadCorporation(c);
        characterRepository.save(c);
        assert(corporationRepository.count() == 2);
        characterRepository.deleteAll();
        assertNotNull(corporationRepository.findOneById(6).get());
    }

    @Test
    public void loadCorporationExisting(){
        CharacterEntity c = characterRepository.findOneById(1).get();
        c.setCorporationIdTransient(5);
        characterUpdate.loadCorporation(c);
        c = characterRepository.save(c);
        CorporationEntity corp = corporationRepository.findOneById(5).get();
        assertEquals("Test Corp", corp.getName());
        characterRepository.deleteAll();
    }

    @Test
    public void getCharacterInvalid(){
        try{
            characterUpdate.getModel(2);
            fail("Exception not raised.");
        }catch (ResponseStatusException ex){
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertEquals("You are attempting to query a non existent instance of CharacterEntity with ID of: 2.", ex.getReason());
        }
    }

    @Test
    public void getCharacterValidExisting(){
        CharacterEntity c = characterUpdate.getModel(1326083433);
        assertEquals("Natuli", c.getName());
        assertEquals(Integer.valueOf(761955047), c.getCorporationEntity().getId());
        c.getMeta().ensureValid();  //make sure no exceptions are raised i.e. still valid
    }
}