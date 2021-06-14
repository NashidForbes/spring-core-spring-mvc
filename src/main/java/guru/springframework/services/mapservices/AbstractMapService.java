package guru.springframework.services.mapservices;

import guru.springframework.domain.DomainObject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractMapService {

    protected Map<Integer, DomainObject> domainMap;

    public AbstractMapService() {
        this.domainMap = new HashMap<Integer, DomainObject>();
        loadDomainObjects();
    }


    public List<DomainObject> listAll() {
        return new ArrayList<DomainObject>(domainMap.values());
    }


    public DomainObject getById(Integer id) {
        return domainMap.get(id);
    }


    public DomainObject saveOrUpdate(DomainObject domainObject) {
        if (domainObject.getId() == null) {
            domainObject.setId(getNextId());
        }
        domainMap.put(domainObject.getId(), domainObject);
        return domainMap.get(domainObject.getId());
    }


    public void deleteById(Integer id) {
        log.info(DomainObject.class.getName() + " list initial size: " +
                String.valueOf(domainMap.size()));
        domainMap.remove(id);
        log.info("Deleted " + DomainObject.class.getName() + " id: " + id);
        log.info(DomainObject.class.getName() + " list post-delete size: " +
                String.valueOf(domainMap.size()));
    }

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    protected abstract void loadDomainObjects();
}
