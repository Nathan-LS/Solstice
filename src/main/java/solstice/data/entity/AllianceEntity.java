package solstice.data.entity;

import solstice.data.RestTemplates.AbstractRestTemplate;
import solstice.data.RestTemplates.AlliancePublic;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;


@Entity
@Table(name="allianceEntity")
public class AllianceEntity extends AbstractModel<AllianceMeta>{
    private Integer creator_corporation_id;
    private Integer creator_id;
    private ZonedDateTime date_founded;
    private Integer executor_corporation_id;
    private Integer faction_id;
    private String name;
    private String ticker;

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

    public void updateFromRestTemplate(AlliancePublic t) {
        name = t.getName();
        ticker = t.getTicker();
    }

    public String getTicker() {
        return ticker;
    }
}
