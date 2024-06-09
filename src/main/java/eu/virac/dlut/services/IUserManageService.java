package eu.virac.dlut.services;

public interface IUserManageService {
    String saveUserToken(String userDn);
    boolean isUserTokenOk(String token);
    String getToken(String userDn);

}