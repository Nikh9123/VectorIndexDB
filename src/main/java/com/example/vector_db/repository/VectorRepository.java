package com.example.vector_db.repository;

import com.example.vector_db.model.VectorRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VectorRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public VectorRepository(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    public void save(VectorRecord record) {
        String sql = "INSERT INTO vectors (id, vector, metadata) VALUES (?, ?::jsonb, ?::jsonb)";
        try {
            jdbcTemplate.update(sql,
                    record.getId(),
                    objectMapper.writeValueAsString(record.getVector()),
                    objectMapper.writeValueAsString(record.getMetadata()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing vector or metadata", e);
        }
    }

    public VectorRecord findById(String id) {
        String sql = "SELECT * FROM vectors WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> mapRowToVectorRecord(rs), id);
    }

    public List<VectorRecord> findAll() {
        String sql = "SELECT * FROM vectors";
        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToVectorRecord(rs));
    }

    public void deleteById(String id) {
        String sql = "DELETE FROM vectors WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private VectorRecord mapRowToVectorRecord(java.sql.ResultSet rs) throws java.sql.SQLException {
        try {
            String id = rs.getString("id");
            List<Double> vector = objectMapper.readValue(rs.getString("vector"), new TypeReference<List<Double>>() {});
            Map<String, Object> metadata = objectMapper.readValue(rs.getString("metadata"), new TypeReference<Map<String, Object>>() {});
            return new VectorRecord(id, vector, metadata);
        } catch (JsonProcessingException e) {
            throw new java.sql.SQLException("Error deserializing JSONB columns", e);
        }
    }
}
