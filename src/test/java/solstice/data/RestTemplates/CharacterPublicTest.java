package solstice.data.RestTemplates;

import solstice.data.entity.AbstractCharacterTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterPublicTest extends AbstractCharacterTest {
    @Test
    public void requestURL() {
    }

    @Test
    public void getAlliance_id() {
        assertEquals(Integer.valueOf(1727758877), characterPublic.getAlliance_id());
    }

    @Test
    public void getAncestry_id() {
        assertEquals(Integer.valueOf(9), characterPublic.getAncestry_id());
    }

    @Test
    public void getBirthday() {
        assertEquals("2010-08-29T23:41Z[UTC]", characterPublic.getBirthday().toString());
    }

    @Test
    public void getBloodline_id() {
        assertEquals(Integer.valueOf(2), characterPublic.getBloodline_id());
    }

    @Test
    public void getCorporation_id() {
        assertEquals(Integer.valueOf(761955047), characterPublic.getCorporation_id());
    }

    @Test
    public void getDescription() {
        assertEquals("", characterPublic.getDescription());
    }

    @Test
    public void getFaction_id() {
        assertNull(characterPublic.getFaction_id());
    }

    @Test
    public void getGender() {
        assertEquals("male", characterPublic.getGender());
    }

    @Test
    public void getName() {
        assertEquals("Natuli", characterPublic.getName());
    }

    @Test
    public void getRace_id() {
        assertEquals(Integer.valueOf(1), characterPublic.getRace_id());
    }

    @Test
    public void getSecurity_status() {
        assertTrue(Float.valueOf("5.0") > characterPublic.getSecurity_status());
    }
}