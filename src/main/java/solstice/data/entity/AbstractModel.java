package solstice.data.entity;

import solstice.data.RestTemplates.AbstractRestTemplate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractModel <T extends AbstractMeta>{
    @NotNull
    @Id
    Integer id;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "linkedEntity", orphanRemoval = true, optional = false)
    T linkedMeta;

    public Integer getId() { return this.id; }
    public T getMeta(){ return linkedMeta; }
    public void updateFromRestTemplate(AbstractRestTemplate t){}
    public Boolean needApiUpdate(){ return linkedMeta.needUpdate(); }
}
