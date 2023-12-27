package com.barabanov.dto;

public record UserReadDto(Integer id,
                          String nickname,
                          PasswordReadDto passwordReadDto)
{ }
