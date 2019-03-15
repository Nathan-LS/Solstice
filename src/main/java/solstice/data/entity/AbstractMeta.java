package solstice.data.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Clock;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class AbstractMeta<T extends AbstractModel> {
    @NotNull
    @Id
    Integer id;

    @OneToOne
    @PrimaryKeyJoinColumn
    T linkedEntity;

    @NotNull
    ZonedDateTime lastUpdate = ZonedDateTime.now(Clock.systemUTC());

    @NotNull
    ZonedDateTime lastQuery = ZonedDateTime.now(Clock.systemUTC());

    @NotNull
    Boolean validEntity = true;

    @NotNull
    Integer requestCount = 1;

    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setInvalid() { this.validEntity = false; }

    public void setValid() { this.validEntity = true; }

    public Integer getRequestCount() {
        return requestCount;
    }

    void setLinkedEntity(T entity){ linkedEntity = entity; }

    public T getLinkedEntity(){return linkedEntity;}

    public void ensureValid() throws ResponseStatusException {
        if (!validEntity){
            String name = linkedEntity.getClass().getSimpleName();
            String s = String.format("You are attempting to query a non existent instance of %s with ID of: %s.", name, linkedEntity.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, s);
        }
    }

    public Boolean needUpdate(){
        if (needDataBasedUpdate()){
            return true;
        }
        else{
            return needTimeBasedUpdate();
        }
    }

    protected Boolean needTimeBasedUpdate(){
        ZonedDateTime updateTime = ZonedDateTime.now(Clock.systemUTC()).plusHours(updateIntervalHours());
        return lastUpdate.isAfter(updateTime);
    }

    public void queryIncrement(){
        lastQuery = ZonedDateTime.now(Clock.systemUTC());
        requestCount++;
    }

    public void setUpdated(){
        lastUpdate = ZonedDateTime.now(Clock.systemUTC());
    }

    //defines an update required if critical information is missing i.e. model name
    abstract public Boolean needDataBasedUpdate();

    abstract protected Integer updateIntervalHours();
}
