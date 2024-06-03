package eu.virac.dlut.utils;

import eu.virac.dlut.services.IUserManageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class TokenValidationUtil {

    public static ResponseEntity<?> handleRequest(IUserManageService userManage, HttpHeaders headers, RequestHandler handler) {
        if (userManage.isUserTokenOk(headers.getFirst("token"))) {
            return handler.handle();
        } else {
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @FunctionalInterface
    public interface RequestHandler {
        ResponseEntity<?> handle();
    }
}