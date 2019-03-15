package solstice.data.ResponseTemplates;

import solstice.data.entity.CorporationEntity;

public class CorporationBasic {
    private Integer id;
    private String name;
    private String ticker;

    public CorporationBasic(CorporationEntity corporationEntity){
        id = corporationEntity.getId();
        name = corporationEntity.getName();
        ticker = corporationEntity.getTicker();
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
