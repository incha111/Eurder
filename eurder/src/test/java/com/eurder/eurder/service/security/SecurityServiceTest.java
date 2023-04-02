package com.eurder.eurder.service.security;

import com.eurder.eurder.service.exceptions.UnknownUserException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class SecurityServiceTest {
    private SecurityService securityService;

    @BeforeEach
    void setUp() {
        securityService = new SecurityService(new SecurityRepository());
    }

    @Test
    void validateAuthorization_whenAUserIsNotFound_thenAnExceptionIsThrown() {
        //GIVEN
        String authorization = "Basic Y3VzdG9tZXJAZ21haWwuY29tOjEyMw==";//customer@gmail.com:123 (unknown user)

        //WHEN
        Throwable exception = catchThrowable(() -> securityService.validateAuthorization(authorization, Feature.CREATE_ORDER));

        //THEN
        Assertions.assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Unknown user. Please try again...");
    }
    @Test
    void validateAuthorization_whenPasswordDoesNoMatch_thenAnExceptionIsThrown() {
        //GIVEN
        String authorization = "Basic YWRtaW5AZXVyZGVyLmNvbToxMjQ=";//admin@eurder.com:124 (wrong password)

        //WHEN
        Throwable exception = catchThrowable(() -> securityService.validateAuthorization(authorization, Feature.ADD_ITEM));

        //THEN
        Assertions.assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Wrong password. Please try again...");
    }

    @Test
    void validateAuthorization_whenUserHasNoAccessToFeature_thenAnExceptionIsThrown() {
        //GIVEN
        String authorization = "Basic YWRtaW5AZXVyZGVyLmNvbToxMjM=";//admin@eurder.com:123 (known user with right password, but no access to feature)

        //WHEN
        Throwable exception = catchThrowable(() -> securityService.validateAuthorization(authorization, Feature.CREATE_ORDER));

        //THEN
        Assertions.assertThat(exception)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Permission denied. Please contact your administrator.");
    }

    @Test
    void validateAuthorization_whenUserHasValidCredentialsAndValidAccess_thenNoExceptionThrown() {
        //GIVEN
        String authorization = "Basic YWRtaW5AZXVyZGVyLmNvbToxMjM=";//admin@eurder.com:123 (known user with right password and valid access to feature)

        //WHEN
        securityService.validateAuthorization(authorization, Feature.ADD_ITEM);

        //THEN
        Assertions.assertThatNoException();

    }

}