package solstice.data.RestTemplates;

import java.time.ZonedDateTime;

public class CorporationPublic extends AbstractRestTemplate{
    private Integer alliance_id;
    private Integer ceo_id;
    private Integer creator_id;
    private ZonedDateTime date_founded;
    private String description;
    private Integer faction_id;
    private Integer home_station_id;
    private Integer member_count;
    private String name;
    private Long shares;
    private Float tax_rate;
    private String ticker;
    private String url;
    private Boolean war_eligible;

    public Integer getAlliance_id() {
        return alliance_id;
    }

    public Integer getCeo_id() {
        return ceo_id;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public ZonedDateTime getDate_founded() {
        return date_founded;
    }

    public String getDescription() {
        return description;
    }

    public Integer getFaction_id() {
        return faction_id;
    }

    public Integer getHome_station_id() {
        return home_station_id;
    }

    public Integer getMember_count() {
        return member_count;
    }

    public String getName() {
        return name;
    }

    public Long getShares() {
        return shares;
    }

    public Float getTax_rate() {
        return tax_rate;
    }

    public String getTicker() {
        return ticker;
    }

    public String getUrl() {
        return url;
    }

    public Boolean getWar_eligible() {
        return war_eligible;
    }
}
