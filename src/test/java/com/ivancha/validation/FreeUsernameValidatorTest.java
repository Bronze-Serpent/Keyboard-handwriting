package com.ivancha.validation;

import com.ivancha.dto.PasswordCreateDto;
import com.ivancha.dto.UserCreateDto;
import com.ivancha.entity.User;
import com.ivancha.repository.UserRepository;
import com.ivancha.validation.impl.FreeUsernameValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class FreeUsernameValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FreeUsernameValidator freeUsernameValidator;

    @Test
    public void shouldValidateObject() {

        String nickname = "test username";
        doReturn(Optional.empty())
                .when(userRepository).findByNickname(nickname);

        var data = new UserCreateDto(nickname);

        var validResult = freeUsernameValidator.isValid(data, null);

        assertThat(validResult).isTrue();
    }


    @Test
    public void shouldNotBeValidated() {

        String nickname = "test username";
        doReturn(Optional.of(new User()))
                .when(userRepository).findByNickname(nickname);

        var data = new UserCreateDto(nickname);

        var validResult = freeUsernameValidator.isValid(data, null);

        assertThat(validResult).isFalse();
    }
}
