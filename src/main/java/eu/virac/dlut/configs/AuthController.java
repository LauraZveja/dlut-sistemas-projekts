package eu.virac.dlut.configs;

import eu.virac.dlut.models.helpers.LoginDTO;
import eu.virac.dlut.services.IUserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final IUserManageService userManage;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IUserManageService userManage) {
        this.authenticationManager = authenticationManager;
        this.userManage = userManage;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = userManage.saveUserToken(((LdapUserDetailsImpl) authentication.getPrincipal()).getDn());

            return new ResponseEntity<>(token, HttpStatusCode.valueOf(200));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Login failed", HttpStatusCode.valueOf(401));
        }
    }
}