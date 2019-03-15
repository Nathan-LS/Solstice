package solstice.data.repository;

import solstice.data.entity.CharacterEntity;
import solstice.data.entity.CharacterMeta;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import static org.junit.Assert.*;

public class CharacterEntityTest extends AbstractModelTest {

    private CharacterEntity characterEntityPreFlush;

    private CharacterEntity characterEntityPostFlush;

    @Before
    public void setUp() throws Exception {
        characterEntityPreFlush = new CharacterEntity(1);
        characterEntityPreFlush.setName("Test");
        characterEntityPostFlush = entityManager.persistAndFlush(characterEntityPreFlush);
        assert(characterRepository.count() == 1);
    }

    @Test
    public void test_findOneById(){
        Optional<CharacterEntity> c = characterRepository.findOneById(1);
        assertTrue(c.isPresent());
        assertEquals("Test", c.get().getName());
    }

    @Test
    public void test_findOneById_NotFound(){
        Optional<CharacterEntity> c = characterRepository.findOneById(2);
        assertFalse(c.isPresent());
    }

    @Test
    public void getId() {
        assertEquals(Integer.valueOf(1), characterEntityPreFlush.getId());
        assertEquals(Integer.valueOf(1), characterEntityPostFlush.getId());
    }

    @Test
    public void getName() {
        assertEquals("Test", characterEntityPreFlush.getName());
        assertEquals("Test", characterEntityPostFlush.getName());
    }

    @Test
    public void setName() {
        characterEntityPreFlush.setName("Test2");
        entityManager.flush();
        entityManager.refresh(characterEntityPostFlush);
        assertEquals("Test2", characterEntityPostFlush.getName());
    }

    @Test
    public void getCharacterMeta() {
        CharacterMeta m = (CharacterMeta) characterEntityPostFlush.getMeta();
        assertNotNull(m);
        assertNotNull(m.getLastUpdate());
        CharacterEntity c = m.getLinkedEntity();
        assertNotNull(c);
        assertEquals(Integer.valueOf(1), c.getId());
        CharacterMeta meta = entityManager.find(CharacterMeta.class, 1);
    }

    @Test
    public void getCharacterMetaDeletedParent() {
        characterRepository.deleteAll();
        CharacterMeta meta = entityManager.find(CharacterMeta.class, 1);
        assertNull(meta);
    }
}