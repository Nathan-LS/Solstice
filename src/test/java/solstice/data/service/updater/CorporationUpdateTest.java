package solstice.data.service.updater;

import solstice.data.entity.CorporationEntity;
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
public class CorporationUpdateTest {
    @Autowired
    protected CorporationUpdate CorporationUpdate;

    @Autowired
    protected CorporationRepository corporationRepository;

    @Before
    public void setUp() throws Exception {
        CorporationEntity invalidCorporation = new CorporationEntity(2);
        invalidCorporation.getMeta().setInvalid();
        corporationRepository.save(invalidCorporation);
        corporationRepository.save(new CorporationEntity(1));
        corporationRepository.save(new CorporationEntity(761955047));
//        AllianceEntity alliance = new AllianceEntity(5);
//        alliance.setName("Test Alliance");
//        corporationRepository.save(corp);
        assert(corporationRepository.count() == 3);
    }

//    @Test
//    public void loadCorporationNew(){
//        CorporationEntity c = CorporationRepository.findOneById(1).get();
//        c.setCorporationIdTransient(6);
//        CorporationUpdate.loadCorporation(c);
//        CorporationRepository.save(c);
//        assert(corporationRepository.count() == 2);
//        CorporationRepository.deleteAll();
//        assertNotNull(corporationRepository.findOneById(6).get());
//    }

//    @Test
//    public void loadCorporationExisting(){
//        CorporationEntity c = CorporationRepository.findOneById(1).get();
//        c.setCorporationIdTransient(5);
//        CorporationUpdate.loadCorporation(c);
//        c = CorporationRepository.save(c);
//        CorporationEntity corp = corporationRepository.findOneById(5).get();
//        assertEquals("Test Corp", corp.getName());
//        CorporationRepository.deleteAll();
//    }

    @Test
    public void getCorporationInvalid(){
        try{
            CorporationUpdate.getModel(2);
            fail("Exception not raised.");
        }catch (ResponseStatusException ex){
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
            assertEquals("You are attempting to query a non existent instance of CorporationEntity with ID of: 2.", ex.getReason());
        }
    }

    @Test
    public void getCorporationValidExisting(){
        CorporationEntity c = CorporationUpdate.getModel(761955047);
        assertEquals("BURN EDEN", c.getName());
        c.getMeta().ensureValid();  //make sure no exceptions are raised i.e. still valid
    }
}