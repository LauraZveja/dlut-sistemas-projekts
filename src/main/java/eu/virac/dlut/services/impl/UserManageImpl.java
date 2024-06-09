package eu.virac.dlut.services.impl;

import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.UserToken;
import eu.virac.dlut.repos.IUserTokenRepo;
import eu.virac.dlut.services.IUserManageService;
import org.springframework.beans.factory.annotation.Value;


@Service
public class UserManageImpl implements IUserManageService {

    private final IUserTokenRepo tokenRepo;
    private static final long EXPIRATION_TIME = 864_000_000;

    @Value("${secret.key}")
    private String secretKey;

    @Autowired
    public UserManageImpl(IUserTokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    @Override
    public String saveUserToken(String userDn) {

        if (!tokenRepo.existsByDn(userDn)) {
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
            String token = getToken(userDn);
            return (tokenRepo.save(new UserToken(userDn, token, expirationDate))).getToken();
        } else {
            UserToken tokenFromDb = tokenRepo.findByDn(userDn);
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
            String token = getToken(userDn);
            tokenFromDb.setExpired(expirationDate);
            tokenFromDb.setToken(token);
            return (tokenRepo.save(tokenFromDb)).getToken();

        }
    }

    @Override
    public boolean isUserTokenOk(String token) {
        UserToken tokenFromDB = tokenRepo.findByToken(token);

        return tokenFromDB != null &&
                getToken(tokenFromDB.getDn()).equals(token) &&
                tokenFromDB.getExpired().after(new Date(System.currentTimeMillis()));
    }

    @Override
    public String getToken(String userDn) {
        return Base64.getEncoder().encodeToString((new SecretKeySpec(secretKey.getBytes(), "HmacSHA256")).getEncoded());
    }


}
