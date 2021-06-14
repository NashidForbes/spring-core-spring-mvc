package guru.springframework.services.mapservices;

import guru.springframework.domain.User;
import guru.springframework.domain.DomainObject;
import guru.springframework.domain.User;
import guru.springframework.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Profile("map")
public class UserServiceImpl extends AbstractMapService implements UserService {
    public UserServiceImpl() {
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User user) {
        if (user.getId() == null) {
            user.setId(getNextId());
        }
        domainMap.put(user.getId(), user);
        return (User) domainMap.get(user.getId());
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Users initial size: " + String.valueOf(domainMap.size()));
        domainMap.remove(id);
        log.info("Deleted User id: " + id);
        log.info("Users post-delete size: " + String.valueOf(domainMap.size()));
    }

    private Integer getNextId() {
        Integer nextInt = domainMap.size() + 1;
        return nextInt;
    }

    @Override
    protected void loadDomainObjects() {

    }
}
