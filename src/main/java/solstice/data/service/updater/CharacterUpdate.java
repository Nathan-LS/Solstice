package solstice.data.service.updater;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;
import solstice.data.RestTemplates.CharacterPublic;
import solstice.data.RestTemplates.CorporationPublic;
import solstice.data.entity.AllianceEntity;
import solstice.data.entity.CharacterEntity;
import solstice.data.entity.CorporationEntity;
import solstice.data.repository.AllianceRepository;
import solstice.data.repository.CharacterRepository;
import solstice.data.repository.CorporationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Transactional
public class CharacterUpdate extends AbstractUpdater <CharacterEntity, CharacterPublic>{
    @Autowired
    private CorporationUpdate corporationUpdate;
    @Autowired
    private AllianceUpdate allianceUpdate;

    @Override
    protected String requestUrl(Integer entityId){
        return String.format("https://esi.evetech.net/latest/characters/%s/?datasource=tranquility", entityId);
    }

    @Override
    protected void updateFromRestTemplate(CharacterEntity model, CharacterPublic apiTemplate) {
        model.updateFromRestTemplate(apiTemplate);
    }

    @Override
    @Transactional
    protected void loadForeignKeyModels(CharacterEntity model) {
        model.setCorporationEntity(corporationUpdate.getModelNoCommit(model.getCorporationIdTransient()));
        model.setAllianceEntity(allianceUpdate.getModelNoCommit(model.getAllianceIdTransient()));
    }

    @Override
    protected Class<CharacterEntity> getModelClass() { return CharacterEntity.class; }

    @Override
    protected Class<CharacterPublic> apiModel() {
        return CharacterPublic.class;
    }
}
