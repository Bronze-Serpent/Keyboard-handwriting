package com.ivancha.validation;

import com.ivancha.dto.UserReferring;
import com.ivancha.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserExistValidator implements ConstraintValidator<UserExist, UserReferring> {

    private final UserRepository userRepository;


    @Override
    public boolean isValid(UserReferring value, ConstraintValidatorContext context) {
        return userRepository.findById(value.userId()).isPresent();
    }
}
