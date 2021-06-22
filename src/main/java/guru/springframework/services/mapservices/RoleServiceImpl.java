package guru.springframework.services.mapservices;

import guru.springframework.domain.security.Role;
import guru.springframework.services.security.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("map")
public class RoleServiceImpl extends AbstractMapService {


    @Override
    protected void loadDomainObjects() {
    }
}
