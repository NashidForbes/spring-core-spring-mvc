package guru.springframework.services.security;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl
        implements EncryptionService {

    private StrongPasswordEncryptor strongEncrypter;

    @Autowired
    public void setStrongEncrypter(StrongPasswordEncryptor strongEncrypter) {
        this.strongEncrypter = strongEncrypter;
    }

    @Override
    public String encryptString(String input) {
        return strongEncrypter.encryptPassword(input);
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return strongEncrypter.checkPassword(plainPassword, encryptedPassword);
    }
}
