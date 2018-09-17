package com.example.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resource {

    private Long id;
    private String name;
    private String path;
    private Long size;
}