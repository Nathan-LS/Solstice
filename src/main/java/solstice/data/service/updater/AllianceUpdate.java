package solstice.data.service.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solstice.data.RestTemplates.AlliancePublic;
import solstice.data.entity.AllianceEntity;
import solstice.data.repository.AllianceRepository;


@Service
public class AllianceUpdate extends AbstractUpdater <AllianceEntity, AlliancePublic>{
    @Autowired
    private AllianceRepository allianceRepository;

    @Override
    protected String requestUrl(Integer entityId){
        return String.format("https://esi.evetech.net/latest/alliances/%s/?datasource=tranquility", entityId);
    }

    @Override
    protected void updateFromRestTemplate(AllianceEntity model, AlliancePublic apiTemplate) {
        model.updateFromRestTemplate(apiTemplate);
    }

    @Override
    protected Class<AlliancePublic> apiModel() {
        return AlliancePublic.class;
    }

    @Override
    protected void loadForeignKeyModels(AllianceEntity model) { }

    @Override
    protected AllianceEntity getOrCreate(Integer id) {
        return allianceRepository.findOneById(id).orElse(new AllianceEntity(id));
    }

    @Override
    AllianceEntity repoSave(AllianceEntity model) {
        return allianceRepository.save(model);
    }

}
