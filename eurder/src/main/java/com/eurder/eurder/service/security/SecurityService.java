package com.eurder.eurder.service.security;

import com.eurder.eurder.service.exceptions.UnauthorizedException;
import com.eurder.eurder.service.exceptions.UnknownUserException;
import com.eurder.eurder.service.exceptions.WrongPasswordException;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class SecurityService {
    private final SecurityRepository securityRepository;

    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }
    public void validateAuthorization(String authorization,Feature feature){
        EmailPassword emailPassword = getEmailPassword(authorization);
        User user = securityRepository.getUser(emailPassword.getEmail());

        if(user == null){
            throw new UnknownUserException("Unknown user. Please try again...");
        }

        if (!user.doesPasswordMatch(emailPassword.getPassword())){
            throw new WrongPasswordException("Wrong password. Please try again...");
        }

        if(!user.canHaveAccessTo(feature)){
            throw new UnauthorizedException("Permission denied. Please contact your administrator.");
        }
    }

    private EmailPassword getEmailPassword(String authorization){
        String decodedEmailAndPassword = new String(Base64.getDecoder().decode(authorization.substring("Basic ".length())));
        String email = decodedEmailAndPassword.substring(0,decodedEmailAndPassword.indexOf(":"));
        String password = decodedEmailAndPassword.substring(decodedEmailAndPassword.indexOf(":") + 1);
        return new EmailPassword(email,password);
    }

    public void addUser(User user){
        securityRepository.addUser(user);
    }
}
