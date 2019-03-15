package solstice.data.controller;

import solstice.data.ResponseTemplates.EntityBasic;
import solstice.data.service.ResponseService.CharacterAffiliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/character")
public class CharacterController {
    @Autowired
    private CharacterAffiliationService characterAffiliationService;

    @GetMapping(path="/id")
    public ResponseEntity<EntityBasic> findID(@RequestParam String id) {
        EntityBasic r = characterAffiliationService.getResponse(Integer.parseInt(id));
        return new ResponseEntity<EntityBasic>(r, HttpStatus.OK);
    }
}
