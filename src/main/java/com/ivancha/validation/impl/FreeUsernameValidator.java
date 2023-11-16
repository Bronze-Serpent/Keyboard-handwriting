package com.ivancha.validation.impl;

import com.ivancha.dto.NicknameContainer;
import com.ivancha.repository.UserRepository;
import com.ivancha.validation.NicknameIsFree;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FreeUsernameValidator implements ConstraintValidator<NicknameIsFree, NicknameContainer> {

    private final UserRepository userRepository;


    @Override
    public boolean isValid(NicknameContainer value, ConstraintValidatorContext context) {

        return userRepository.findByNickname(value.nickname()).isEmpty();
    }
}
