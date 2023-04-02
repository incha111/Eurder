package com.eurder.eurder.service.security;

import com.eurder.eurder.service.exceptions.UnauthorizedException;
import com.eurder.eurder.service.exceptions.UnknownUserException;
import com.eurder.eurder.service.exceptions.WrongPasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Base64;

import static java.lang.String.format;

@Service
public class SecurityService {
    private final Logger logger = LoggerFactory.getLogger(SecurityService.class);
    private final SecurityRepository securityRepository;

    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }
    public void validateAuthorization(String authorization,Feature feature){
        EmailPassword emailPassword = getEmailPassword(authorization);
        User user = securityRepository.getUser(emailPassword.getEmail());

        if(user == null){
            logger.error(format("Unknown user tries to log in with email: '%s' and password '%s'",emailPassword.getEmail(),emailPassword.getPassword()));
            throw new UnknownUserException("Unknown user. Please try again...");
        }

        if (!user.doesPasswordMatch(emailPassword.getPassword())){
            logger.error(format("Password does not match for user %s %s", user.getFirstname(),user.getLastname()));
            throw new WrongPasswordException("Wrong password. Please try again...");
        }

        if(!user.canHaveAccessTo(feature)){
            logger.error(format("User %s %s does not have access to %s", user.getFirstname(),user.getLastname(), feature));
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
