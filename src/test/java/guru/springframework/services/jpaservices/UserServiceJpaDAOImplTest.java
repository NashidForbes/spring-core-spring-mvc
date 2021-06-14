package guru.springframework.services.jpaservices;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.User;
import guru.springframework.services.interfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class UserServiceJpaDAOImplTest {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testSaveOfUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        User savedUser = userService.saveOrUpdate(user);

        //assert savedUser.getId() != null;
        //assert savedUser.getEncryptedPassword() != null;
        assertNotEquals(null, savedUser.getId());

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());

    }
}
