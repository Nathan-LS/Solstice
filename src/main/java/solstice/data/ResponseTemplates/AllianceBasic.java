package solstice.data.ResponseTemplates;

import solstice.data.entity.AllianceEntity;

public class AllianceBasic {
    private Integer id;
    private String name;
    private String ticker;

    public AllianceBasic(AllianceEntity allianceEntity){
        id = allianceEntity.getId();
        name = allianceEntity.getName();
        //ticker = allianceEntity.getTicker();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

}
