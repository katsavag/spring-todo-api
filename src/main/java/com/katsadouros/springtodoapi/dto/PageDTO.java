package com.katsadouros.springtodoapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public final class PageDTO<T> {

    List<T> data;
    int page;
    int size;
    int totalPages;

}
