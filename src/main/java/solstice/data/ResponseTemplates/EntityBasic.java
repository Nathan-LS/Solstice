package solstice.data.ResponseTemplates;

import com.fasterxml.jackson.annotation.JsonInclude;
import solstice.data.entity.AllianceEntity;
import solstice.data.entity.CharacterEntity;
import solstice.data.entity.CorporationEntity;
import org.springframework.lang.Nullable;


public class EntityBasic {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CharacterBasic character;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CorporationBasic corporation;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AllianceBasic alliance;

    public EntityBasic(@Nullable CharacterEntity characterEntity, @Nullable CorporationEntity corporationEntity, @Nullable AllianceEntity allianceEntity){
        if (characterEntity != null) {
            character = new CharacterBasic(characterEntity);
        }
        if (corporationEntity != null) {
            corporation = new CorporationBasic(corporationEntity);
        }
        if (allianceEntity != null) {
            alliance = new AllianceBasic(allianceEntity);
        }
    }

    public CharacterBasic getCharacter() {
        return character;
    }

    public CorporationBasic getCorporation() {
        return corporation;
    }

    public AllianceBasic getAlliance() {
        return alliance;
    }
}
