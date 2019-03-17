package solstice.data.entity;

import solstice.data.RestTemplates.CorporationPublic;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Optional;


@Entity
@Table(name="corporationEntity")
public class CorporationEntity extends AbstractModel<CorporationMeta>{
    private String name;                        //required

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    private AllianceEntity allianceEntity;      //optional
    @Transient
    private Integer allianceIdTransient;        //optional

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    private CharacterEntity ceoEntity;          //required
    @Transient
    private Integer ceoIdTransient;             //required

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    private CharacterEntity creatorEntity;      //required
    @Transient
    private Integer creatorIdTransient;         //required

    private ZonedDateTime dateFounded;         //optional
    @Lob
    private String description;                //optional

    //todo faction id/entity

    private Integer homeStationId;              //optional
    private Integer memberCount;                //required
    private Long shares;                     //optional
    private Float taxRate;                      //required
    private String ticker;                      //required
    private String url;                         //optional
    private Boolean warEligible;                //optional

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "corporationEntity", orphanRemoval = true)
//    private List<CharacterEntity> characterEntities = new ArrayList<CharacterEntity>();

    protected CorporationEntity(){}

    public CorporationEntity(Integer id) {
        this.id = id;
        linkedMeta = new CorporationMeta(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateFromRestTemplate(CorporationPublic t) {
        allianceIdTransient = t.getAlliance_id();
        ceoIdTransient = t.getCeo_id();
        creatorIdTransient = t.getCreator_id();
        dateFounded = t.getDate_founded();
        //description = t.getDescription();
        homeStationId = t.getHome_station_id();
        memberCount = t.getMember_count();
        name = t.getName();
        shares = t.getShares();
        taxRate = t.getTax_rate();
        ticker = t.getTicker();
        url = t.getUrl();
        warEligible = t.getWar_eligible();
    }

    public AllianceEntity getAllianceEntity() {
        return allianceEntity;
    }

    public Optional<Integer> getAllianceIdTransient() {
        return Optional.ofNullable(allianceIdTransient);
    }

    public CharacterEntity getCeoEntity() {
        return ceoEntity;
    }

    public Integer getCeoIdTransient() {
        return ceoIdTransient;
    }

    public CharacterEntity getCreatorEntity() { return creatorEntity; }

    public Integer getCreatorIdTransient() {
        return creatorIdTransient;
    }

    public ZonedDateTime getDateFounded() {
        return dateFounded;
    }

    public String getDescription() {
        return description;
    }

    public Integer getHomeStationId() {
        return homeStationId;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public Long getShares() {
        return shares;
    }

    public Float getTaxRate() { return taxRate; }

    public String getTicker() {
        return ticker;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getWarEligible() {
        return warEligible;
    }

    public void setAllianceEntity(Optional<AllianceEntity> allianceEntity) {
        this.allianceEntity = allianceEntity.isPresent() ? allianceEntity.get() : null;
    }

    public void setCeoEntity(CharacterEntity ceoEntity) {
        this.ceoEntity = ceoEntity;
    }

    public void setCreatorEntity(CharacterEntity creatorEntity) {
        this.creatorEntity = creatorEntity;
    }
}
