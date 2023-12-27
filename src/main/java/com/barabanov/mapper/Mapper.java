package com.barabanov.mapper;

public interface Mapper <F, T> {

    T map(F object);
}
