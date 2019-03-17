package solstice.data.service.updater;

import org.springframework.transaction.annotation.Transactional;
import solstice.data.RestTemplates.CorporationPublic;
import solstice.data.entity.CharacterEntity;
import solstice.data.entity.CorporationEntity;
import solstice.data.repository.CorporationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CorporationUpdate extends AbstractUpdater <CorporationEntity, CorporationPublic>{
    @Autowired
    private CorporationRepository corporationRepository;
    @Autowired
    private CharacterUpdate characterUpdate;
    @Autowired
    private AllianceUpdate allianceUpdate;

    @Override
    protected String requestUrl(Integer entityId){
        return String.format("https://esi.evetech.net/latest/corporations/%s/?datasource=tranquility", entityId);
    }

    @Override
    protected void updateFromRestTemplate(CorporationEntity model, CorporationPublic apiTemplate) {
        model.updateFromRestTemplate(apiTemplate);
    }

    @Override
    protected Class<CorporationPublic> apiModel() {
        return CorporationPublic.class;
    }

    @Override
    protected void loadForeignKeyModels(CorporationEntity model) {
        //model.setCeoEntity(characterUpdate.getOrCreate(model.getCeoIdTransient()));
        //model.setCreatorEntity(characterUpdate.getOrCreate(model.getCreatorIdTransient()));
        //model.setAllianceEntity(allianceUpdate.getOrCreate(model.getAllianceIdTransient()));
    }

    @Override
    protected CorporationEntity getOrCreate(Integer id) {
        return corporationRepository.findOneById(id).orElse(new CorporationEntity(id));
    }

    @Override
    CorporationEntity repoSave(CorporationEntity model) {
        return corporationRepository.save(model);
    }

}
