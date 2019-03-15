package solstice.data.ResponseTemplates;

import solstice.data.entity.CharacterEntity;


public class CharacterBasic {
    private Integer id;
    private String name;

    public CharacterBasic(CharacterEntity characterEntity){
        id = characterEntity.getId();
        name = characterEntity.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
