package com.example.vector_db.model;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    private List<Double> vector;   // query vector

    private int topK;              // number of nearest results

    private Map<String, Object> filter; // optional metadata filter
}
