package solstice.data.RestTemplates;

import java.time.ZonedDateTime;

public class CharacterPublic extends AbstractRestTemplate{
    private Integer alliance_id;
    private Integer ancestry_id;
    private ZonedDateTime birthday;
    private Integer bloodline_id;
    private Integer corporation_id;
    private String description;
    private Integer faction_id;
    private String gender;
    private String name;
    private Integer race_id;
    private Float security_status;

    public Integer getAlliance_id() {
        return alliance_id;
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

    public Integer getCorporation_id() {
        return corporation_id;
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

    public String getName() {
        return name;
    }

    public Integer getRace_id() {
        return race_id;
    }

    public Float getSecurity_status() {
        return security_status;
    }

}
