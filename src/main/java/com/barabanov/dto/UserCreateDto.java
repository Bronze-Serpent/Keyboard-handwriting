package com.barabanov.dto;

import com.barabanov.validation.NicknameIsFree;

public record UserCreateDto(
        @NicknameIsFree
        String nickname
){}
