package solstice.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="corporationMeta")
public class CorporationMeta extends AbstractMeta<CorporationEntity>{

    CorporationMeta(){}

    CorporationMeta(CorporationEntity c){
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
