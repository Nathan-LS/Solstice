package solstice.data.controller;

import org.springframework.boot.test.web.client.TestRestTemplate;
import solstice.data.entity.CharacterEntity;
import solstice.data.repository.CharacterRepository;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.ZonedDateTime;

import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = NONE)
public class CharacterControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private CharacterController characterController;

    @Autowired
    protected CharacterRepository characterRepository;

    protected String getRequestUrl(Integer id){
        return String.format("http://localhost:%s/character/id?id=%s", port, id);
    }

    @Test
    public void contextLoads() throws Exception{
        assertNotNull(characterController);
    }

    @Test
    public void getCharCEO() throws Exception{
        String r = this.restTemplate.getForObject(getRequestUrl(443630591), String.class);
        String r_assert = "{\"character\":{\"id\":443630591,\"name\":\"The Mittani\"},\"corporation\":{\"id\":667531913,\"name\":\"GoonWaffe\",\"ticker\":\"GEWNS\"},\"alliance\":{\"id\":1354830081,\"name\":\"Goonswarm Federation\",\"ticker\":\"CONDI\"}}";
        assertEquals(r_assert, r);
        CharacterEntity c = characterRepository.findOneById(443630591).get();
        ZonedDateTime lastUpdate = c.getMeta().getLastUpdate();
        assertEquals(Integer.valueOf(1), c.getMeta().getRequestCount());
        this.restTemplate.getForObject(getRequestUrl(443630591), String.class);
        c = characterRepository.findOneById(443630591).get();
        assertEquals(Integer.valueOf(2), c.getMeta().getRequestCount());
        assertEquals(lastUpdate, c.getMeta().getLastUpdate());
    }
}
