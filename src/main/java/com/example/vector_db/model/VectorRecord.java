package com.example.vector_db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class VectorRecord {
    private String id ;
    private List<Double>vector ; //3 dimensional
    private Map<String, Object>metadata ;
}
