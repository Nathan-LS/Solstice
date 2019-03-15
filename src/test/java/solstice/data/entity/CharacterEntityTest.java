package solstice.data.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterEntityTest extends AbstractCharacterTest {
    @Test
    public void getAlliance_id() {
        assertEquals(Integer.valueOf(1727758877), characterEntity.getAllianceIdTransient());
    }

    @Test
    public void getAncestry_id() {
        assertEquals(Integer.valueOf(9), characterEntity.getAncestry_id());
    }

    @Test
    public void getBirthday() {
        assertEquals("2010-08-29T23:41Z[UTC]", characterEntity.getBirthday().toString());
    }

    @Test
    public void getBloodline_id() {
        assertEquals(Integer.valueOf(2), characterEntity.getBloodline_id());
    }

    //todo fix this
    public void getCorporationIdTransient() { assertNull(characterEntity.getCorporationIdTransient()); }

    @Test
    public void getCorporationIdEntity() { assertEquals(Integer.valueOf(761955047), characterEntity.getCorporationIdEntity()); }

    @Test
    public void getDescription() {
        assertEquals("", characterEntity.getDescription());
    }

    @Test
    public void getFaction_id() {
        assertNull(characterEntity.getFaction_id());
    }

    @Test
    public void getGender() {
        assertEquals("male", characterEntity.getGender());
    }

    @Test
    public void getName() {
        assertEquals("Natuli", characterEntity.getName());
    }

    @Test
    public void getRace_id() {
        assertEquals(Integer.valueOf(1), characterEntity.getRace_id());
    }

    @Test
    public void getSecurity_status() {
        assertTrue(Float.valueOf("5.0") > characterEntity.getSecurity_status());
    }
}