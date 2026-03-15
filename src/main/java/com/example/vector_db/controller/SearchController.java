package com.example.vector_db.controller;

import com.example.vector_db.model.SearchRequest;
import com.example.vector_db.model.SearchResult;
import com.example.vector_db.service.SearchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public List<SearchResult> search(@RequestBody SearchRequest request) {

        return searchService.search(request);
    }
}