package com.example.vector_db.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRecord implements Comparable<SearchRecord> {

    private VectorRecord record;   // original stored vector

    private double score;          // similarity score

    @Override
    public int compareTo(SearchRecord other) {

        // compare similarity scores
        // used by PriorityQueue to maintain MinHeap
        return Double.compare(this.score, other.score);
    }
}