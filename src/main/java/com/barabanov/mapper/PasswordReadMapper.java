package com.barabanov.mapper;

import com.barabanov.dto.PasswordReadDto;
import com.barabanov.entity.Password;


public class PasswordReadMapper implements Mapper<Password, PasswordReadDto> {

    @Override
    public PasswordReadDto map(Password object) {
        return new PasswordReadDto(
                object.getId(),
                object.getValue(),
                object.getTimeBetweenPresses(),
                object.getKeyPressTime()
                );
    }
}
