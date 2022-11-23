package ar.edu.unrn.userservice.generic;

import ar.edu.unrn.userservice.security.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

public class GenericRest<S extends GenericService<E, Long>, E extends BaseEntity> {

    final static Logger LOG = LoggerFactory.getLogger(GenericRest.class);

    protected S service;

    protected S getService() {
        return service;
    }


    public GenericRest(S service) {
        this.service = service;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<E> create(@RequestBody E entity) {
        LOG.info("create {}", entity);
        service.save(entity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody E entity) {
        LOG.info("update {}", entity);
        service.save(entity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable Long id) throws IOException {
        LOG.info("delete {}", id);
        E entity = service.getEntityById(id);
        service.delete(entity);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<E>> findAll() {
        List<E> all = service.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@PathVariable Long id) {
        E entity = service.getEntityById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}

