package solstice.data.entity;

import solstice.data.RestTemplates.CharacterPublic;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Entity
@Table(name="characterEntity")
public class CharacterEntity extends AbstractModel<CharacterMeta>{
    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CorporationEntity corporationEntity;

    @ManyToOne(cascade = CascadeType.MERGE)
    private AllianceEntity allianceEntity;

    private Integer ancestry_id;
    private ZonedDateTime birthday;
    private Integer bloodline_id;
    @Lob
    private String description;
    private Integer faction_id;
    private String gender;
    private Integer race_id;
    private Float security_status;

    @Transient
    private Integer corporationIdTransient;
    @Transient
    private Integer allianceIdTransient;

    protected CharacterEntity(){}

    public CharacterEntity(Integer id) {
        this.id = id;
        this.linkedMeta = new CharacterMeta(this);
    }

    public void setName(String name) { this.name = name; }

    public String getName() {
        return name;
    }

    public Integer getAllianceIdTransient() {
        return allianceIdTransient;
    }

    public void setCorporationEntity(CorporationEntity corporationEntity) {
        this.corporationEntity = corporationEntity;
    }

    public CorporationEntity getCorporationEntity() {
        return corporationEntity;
    }

    public void setCorporationIdTransient(Integer corporationIdTransient) {
        this.corporationIdTransient = corporationIdTransient;
    }

    public void setAllianceIdTransient(Integer allianceIdTransient) {
        this.allianceIdTransient = allianceIdTransient;
    }

    public Boolean updateFkCorp(){
        if (corporationIdTransient == null){
            corporationEntity = null;
            return false;
        }
        else{
            if (corporationEntity == null){
                return true;
            }
            return !corporationIdTransient.equals(corporationEntity.getId());
        }
    }

    public Boolean updateFkAlliance(){
        if (allianceIdTransient == null){
            allianceEntity = null;
            return false;
        }
        else{
            if (allianceEntity == null){
                return true;
            }
            return !allianceIdTransient.equals(allianceEntity.getId());
        }
    }

    public void updateFromRestTemplate(CharacterPublic t) {
        this.allianceIdTransient = t.getAlliance_id();
        this.ancestry_id = t.getAncestry_id();
        this.birthday = t.getBirthday();
        this.bloodline_id = t.getBloodline_id();
        this.corporationIdTransient = t.getCorporation_id();
        this.description = t.getDescription();
        this.faction_id = t.getFaction_id();
        this.gender = t.getGender();
        this.name = t.getName();
        this.race_id = t.getRace_id();
        this.security_status = t.getSecurity_status();
    }

    public Integer getAncestry_id() {
        return ancestry_id;
    }

    public ZonedDateTime getBirthday() {
        return birthday;
    }

    public Integer getBloodline_id() {
        return bloodline_id;
    }

    public Integer getCorporationIdEntity() {
        return corporationEntity == null ? null : corporationEntity.getId();
    }

    public Integer getCorporationIdTransient() {
        return corporationIdTransient;
    }

    public String getDescription() {
        return description;
    }

    public Integer getFaction_id() {
        return faction_id;
    }

    public String getGender() {
        return gender;
    }

    public Integer getRace_id() {
        return race_id;
    }

    public Float getSecurity_status() {
        return security_status;
    }
}
