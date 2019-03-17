package solstice.data.service.updater;

import org.springframework.transaction.annotation.Transactional;
import solstice.data.RestTemplates.AbstractRestTemplate;
import solstice.data.entity.AbstractModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
public abstract class AbstractUpdater<T extends AbstractModel, S extends AbstractRestTemplate> {
    abstract protected String requestUrl(Integer entityId);
    abstract protected Class<S> apiModel();
    abstract protected void loadForeignKeyModels(T model);
    abstract protected T getOrCreate(Integer id);
    abstract protected void updateFromRestTemplate(T model, S apiTemplate);
    abstract T repoSave(T model);

    protected Optional<T> getOrCreate(Optional<Integer> identifier){
        if (identifier.isPresent()){
            return Optional.of(getOrCreate(identifier.get()));
        }else{
            return Optional.empty();
        }
    }

    protected void doApiUpdate(T model) throws ResponseStatusException {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<S> r = restTemplate.exchange(requestUrl(model.getId()),
                    HttpMethod.GET, null, apiModel());
            if (r.getStatusCodeValue() == 200) {
                model.getMeta().setValid();
                updateFromRestTemplate(model, r.getBody());
                loadForeignKeyModels(model);
                model.getMeta().setUpdated();
            } else {
                throw new ResponseStatusException(r.getStatusCode(), "Response from ESI when updating.");
            }
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                model.getMeta().setInvalid();
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            } else {
                throw new ResponseStatusException(ex.getStatusCode(), "Response from ESI when updating.");
            }
        } catch (RestClientException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred when updating data from ESI. ESI could be down.");
        }
    }

    protected T checkDoUpdate(T model) throws ResponseStatusException{
        try {
            model.getMeta().ensureValid();
            if (model.needApiUpdate()) {
                doApiUpdate(model);
            }
        }finally{
            model.getMeta().queryIncrement();
        }
        return model;
    }

    public T getModelNoCommit(Integer id) throws ResponseStatusException{
        return checkDoUpdate(getOrCreate(id));
    }

    public Optional<T> getModelNoCommit(Optional<Integer> id) throws ResponseStatusException{
        try{
            return Optional.of(getModelNoCommit(id.get()));
        }catch (NoSuchElementException ex){
            return Optional.empty();
        }
    }

    public T getModel(Integer id) throws ResponseStatusException {
        if (id == null){
            return null;
        }
        return repoSave(checkDoUpdate(getOrCreate(id)));
    }

    public T getModel(T entity)throws ResponseStatusException {
        if (entity == null){
            return null;
        }
        else {
            return getModel(entity.getId());
        }
    }

}
