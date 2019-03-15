package solstice.data.entity;

import solstice.data.RestTemplates.AbstractRestTemplate;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="allianceEntity")
public class AllianceEntity extends AbstractModel<AllianceMeta>{
    private String name;

    protected AllianceEntity(){}

    public AllianceEntity(Integer id) {
        this.id = id;
        linkedMeta = new AllianceMeta(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void updateFromRestTemplate(AbstractRestTemplate t) {
        return;
    }
}
