package solstice.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="allianceMeta")
class AllianceMeta extends AbstractMeta<AllianceEntity>{

    AllianceMeta(){}

    AllianceMeta(AllianceEntity c){
        setId(c.getId());
        setLinkedEntity(c);
    }

    @Override
    public Boolean needDataBasedUpdate() {
        return getLinkedEntity().getName() == null;
    }

    @Override
    protected Integer updateIntervalHours() {
        return 24;
    }
}
