package com.barabanov.mapper;

import com.barabanov.dto.UserCreateDto;
import com.barabanov.entity.User;


public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    @Override
    public User map(UserCreateDto object) {
        return User.builder()
                .nickname(object.nickname())
                .build();
    }
}
