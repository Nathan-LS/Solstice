package solstice.data.service.updater;

import solstice.data.RestTemplates.CharacterPublic;
import solstice.data.entity.CharacterEntity;
import solstice.data.entity.CorporationEntity;
import solstice.data.repository.CharacterRepository;
import solstice.data.repository.CorporationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CharacterUpdate extends AbstractUpdater <CharacterEntity, CharacterPublic>{
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CorporationRepository corporationRepository;

    @Override
    protected String requestUrl(Integer entityId){
        return String.format("https://esi.evetech.net/latest/characters/%s/?datasource=tranquility", entityId);
    }

    @Override
    protected void updateFromRestTemplate(CharacterEntity model, CharacterPublic apiTemplate) {
        model.updateFromRestTemplate(apiTemplate);
    }

    @Override
    protected Class<CharacterPublic> apiModel() {
        return CharacterPublic.class;
    }

    @Override
    protected void loadForeignKeyModels(CharacterEntity model) {
        loadCorporation(model);
        loadAlliance(model);
    }

    @Override
    protected CharacterEntity getOrCreate(Integer id) {
        return characterRepository.findOneById(id).orElse(new CharacterEntity(id));
    }

    @Override
    CharacterEntity repoSave(CharacterEntity model) {
        return characterRepository.save(model);
    }

    protected void loadCorporation(CharacterEntity characterEntity) {
        if (characterEntity.updateFkCorp()) {
            CorporationEntity c = corporationRepository.findOneById(characterEntity.getCorporationIdTransient()).
                    orElse(new CorporationEntity(characterEntity.getCorporationIdTransient()));
            characterEntity.setCorporationEntity(c);
        }
    }

    protected void loadAlliance(CharacterEntity characterEntity) {

    }
}
