package solstice.data.repository;

import solstice.data.entity.CharacterEntity;
import solstice.data.entity.CorporationEntity;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CharacterEntityCorporationInteraction extends AbstractModelTest {

    @Before
    public void setUp() throws Exception {
        entityManager.persistAndFlush(new CharacterEntity(1));
        CorporationEntity corp = new CorporationEntity(5);
        corp.setName("Test Corp");
        entityManager.persistAndFlush(corp);
        assert(characterRepository.count() == 1);
        assert(corporationRepository.count() == 1);
    }

    @Test
    public void test_loadCorporationNew(){
        CharacterEntity c = new CharacterEntity(2);
        CorporationEntity corp = new CorporationEntity((6));
        c.setCorporationEntity(corp);
        CharacterEntity cE = characterRepository.save(c);
        assertEquals(Integer.valueOf(6), cE.getCorporationEntity().getId());
        assertEquals(corp.getId(), cE.getCorporationEntity().getId());
    }

    @Test
    public void test_loadCorporationOverwrite(){
        assertEquals("Test Corp", entityManager.find(CorporationEntity.class, 5).getName());
        CharacterEntity c = new CharacterEntity(2);
        CorporationEntity corp = new CorporationEntity((5));
        c.setCorporationEntity(corp);
        characterRepository.save(c);
        assertNull( entityManager.find(CorporationEntity.class, 5).getName());
    }

}