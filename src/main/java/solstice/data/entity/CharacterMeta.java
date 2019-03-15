package solstice.data.entity;

import javax.persistence.*;


@Entity
@Table(name="characterMeta")
public class CharacterMeta extends AbstractMeta<CharacterEntity>{

    protected CharacterMeta(){}

    CharacterMeta(CharacterEntity c){
        setId(c.getId());
        setLinkedEntity(c);
    }

    @Override
    public Boolean needDataBasedUpdate() {
        return linkedEntity.getName() == null;
    }

    @Override
    protected Integer updateIntervalHours() {
        return 24;
    }
}
