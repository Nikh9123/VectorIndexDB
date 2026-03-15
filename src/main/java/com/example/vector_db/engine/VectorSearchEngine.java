package com.example.vector_db.engine;

import com.example.vector_db.model.SearchRecord;
import com.example.vector_db.model.SearchResult;
import com.example.vector_db.model.VectorRecord;
import com.example.vector_db.repository.VectorRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Component
public class VectorSearchEngine {

    private final VectorRepository vectorRepository;

    public VectorSearchEngine(VectorRepository vectorRepository) {
        this.vectorRepository = vectorRepository;
    }

    public List<SearchResult> search(List<Double> queryVector, int topK) {

        List<VectorRecord> vectors = vectorRepository.findAll();

        PriorityQueue<SearchRecord>heap = new PriorityQueue<>();

        for (VectorRecord record : vectors) {

            double score = SimilarityUtils.cosineSimilarity(
                    queryVector,
                    record.getVector()
            );

            SearchRecord searchRecord = new SearchRecord(record, score);

            if(heap.size() < topK){
                heap.add(searchRecord);
            }
            else if(score > heap.peek().getScore()){
                heap.poll();
                heap.add(searchRecord);
            }
        }

        List<SearchResult> finalResults = new ArrayList<>();

        while(!heap.isEmpty()){
            SearchRecord sr = heap.poll();
            finalResults.add(new SearchResult(sr.getRecord().getId(), sr.getScore()));
        }

        return finalResults;
    }
}