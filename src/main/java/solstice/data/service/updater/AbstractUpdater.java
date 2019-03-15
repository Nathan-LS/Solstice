package solstice.data.service.updater;

import solstice.data.RestTemplates.AbstractRestTemplate;
import solstice.data.entity.AbstractModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

public abstract class AbstractUpdater<T extends AbstractModel, S extends AbstractRestTemplate> {
    abstract protected String requestUrl(Integer entityId);
    abstract protected Class<S> apiModel();
    abstract protected void loadForeignKeyModels(T model);
    abstract protected T getOrCreate(Integer id);
    abstract protected void updateFromRestTemplate(T model, S apiTemplate);
    abstract T repoSave(T model);

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

    public T getModel(Integer id) throws ResponseStatusException {
        T c = getOrCreate(id);
        try {
            c.getMeta().ensureValid();
            if (c.needApiUpdate()) {
                doApiUpdate(c);
            }
        } finally {
            c.getMeta().queryIncrement();
            c = repoSave(c);
        }
        return c;
    }
}
