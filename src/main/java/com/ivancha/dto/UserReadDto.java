package com.ivancha.dto;

public record UserReadDto(Integer id,
                          String name,
                          PasswordReadDto passwordReadDto)
{ }
