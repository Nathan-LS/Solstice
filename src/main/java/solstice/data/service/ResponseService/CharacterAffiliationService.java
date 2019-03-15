package solstice.data.service.ResponseService;

import solstice.data.ResponseTemplates.EntityBasic;
import solstice.data.entity.CharacterEntity;
import solstice.data.entity.CorporationEntity;
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


    public EntityBasic getResponse(Integer lookupId){
        CharacterEntity c = characterUpdate.getModel(lookupId);
        CorporationEntity corp = corporationUpdate.getModel(c.getCorporationEntity().getId());
        return new EntityBasic(c, corp, null);
    }
}
