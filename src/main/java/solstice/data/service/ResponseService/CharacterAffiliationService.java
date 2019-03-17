package solstice.data.service.ResponseService;

import solstice.data.ResponseTemplates.EntityBasic;
import solstice.data.RestTemplates.AlliancePublic;
import solstice.data.entity.AllianceEntity;
import solstice.data.entity.CharacterEntity;
import solstice.data.entity.CorporationEntity;
import solstice.data.service.updater.AllianceUpdate;
import solstice.data.service.updater.CharacterUpdate;
import solstice.data.service.updater.CorporationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterAffiliationService {
    @Autowired
    private CharacterUpdate characterUpdate;

    @Autowired
    private CorporationUpdate corporationUpdate;

    @Autowired
    private AllianceUpdate allianceUpdate;


    public EntityBasic getResponse(Integer lookupId){
        CharacterEntity ch = characterUpdate.getModel(lookupId);
        return new EntityBasic(ch, ch.getCorporationEntity(), ch.getAllianceEntity());
    }
}
