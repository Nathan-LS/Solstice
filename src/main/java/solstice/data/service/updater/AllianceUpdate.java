package solstice.data.service.updater;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solstice.data.RestTemplates.AlliancePublic;
import solstice.data.entity.AllianceEntity;


@Service
@Transactional
public class AllianceUpdate extends AbstractUpdater <AllianceEntity, AlliancePublic>{
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
    protected Class<AllianceEntity> getModelClass() {
        return AllianceEntity.class;
    }
}
