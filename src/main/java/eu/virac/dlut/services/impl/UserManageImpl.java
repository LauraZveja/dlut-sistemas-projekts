package eu.virac.dlut.services.impl;

import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.dlut.models.UserToken;
import eu.virac.dlut.repos.IUserTokenRepo;
import eu.virac.dlut.services.IUserManageService;

@Service
public class UserManageImpl implements IUserManageService {
    @Autowired
    private IUserTokenRepo tokenRepo;
    private static final long EXPIRATION_TIME = 864_000_000;
    private static final String SECRET_KEY = "DLUT2024_8h4274j234b3lu4&^$KN(";

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

        if (tokenFromDB == null) return false;
        String compareToken = getToken(tokenFromDB.getDn());

        if (!compareToken.equals(token) || tokenFromDB.getExpired().before(new Date(System.currentTimeMillis())))
            return false;

        else return true;
    }

    @Override
    public String getToken(String userDn) {
        return Base64.getEncoder().encodeToString((new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256")).getEncoded());
    }


}
