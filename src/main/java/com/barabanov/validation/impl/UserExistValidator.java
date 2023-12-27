package com.barabanov.validation.impl;

import com.barabanov.validation.UserExist;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class UserExistValidator implements ConstraintValidator<UserExist, Integer> {

//    private final UserRepository userRepository;


    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
//        return userRepository.findById(value).isPresent();
        return true; // TODO: 19.11.2023 проблема с созданием объекта этого класса
    }
}
