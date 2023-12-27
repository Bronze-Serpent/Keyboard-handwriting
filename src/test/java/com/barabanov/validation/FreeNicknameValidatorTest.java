package com.barabanov.validation;

import com.barabanov.entity.User;
import com.barabanov.repository.UserRepository;
import com.barabanov.validation.impl.FreeNicknameValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class FreeNicknameValidatorTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private FreeNicknameValidator freeNicknameValidator;

    @Test
    public void shouldValidateObject() {

        String nickname = "test username";
        doReturn(Optional.empty())
                .when(userRepository).findByNickname(nickname);

        var validResult = freeNicknameValidator.isValid(nickname, null);

        assertThat(validResult).isTrue();
    }


    @Test
    public void shouldNotBeValidated() {

        String nickname = "test username";
        doReturn(Optional.of(new User()))
                .when(userRepository).findByNickname(nickname);

        var validResult = freeNicknameValidator.isValid(nickname, null);

        assertThat(validResult).isFalse();
    }
}
