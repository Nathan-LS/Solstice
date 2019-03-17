package solstice.data.RestTemplates;

import java.time.ZonedDateTime;

public class AlliancePublic extends AbstractRestTemplate{
    private Integer creator_corporation_id;
    private Integer creator_id;
    private ZonedDateTime date_founded;
    private Integer executor_corporation_id;
    private Integer faction_id;
    private String name;
    private String ticker;

    public Integer getCreator_corporation_id() {
        return creator_corporation_id;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public ZonedDateTime getDate_founded() {
        return date_founded;
    }

    public Integer getExecutor_corporation_id() {
        return executor_corporation_id;
    }

    public Integer getFaction_id() {
        return faction_id;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }
}
