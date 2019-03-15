package solstice.data.service.updater;

import solstice.data.RestTemplates.CorporationPublic;
import solstice.data.entity.CorporationEntity;
import solstice.data.repository.CorporationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CorporationUpdate extends AbstractUpdater <CorporationEntity, CorporationPublic>{
    @Autowired
    private CorporationRepository corporationRepository;

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
        loadAlliance(model);
    }

    @Override
    protected CorporationEntity getOrCreate(Integer id) {
        return corporationRepository.findOneById(id).orElse(new CorporationEntity(id));
    }

    @Override
    CorporationEntity repoSave(CorporationEntity model) {
        return corporationRepository.save(model);
    }

    protected void loadAlliance(CorporationEntity CorporationEntity) {

    }
}
