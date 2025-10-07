package com.example.Data.Access.Object.demo.repository;

import org.springframework.core.io.ClassPathResource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static String fetchProductByСlientNameQuery;

    static {
        try {
            fetchProductByСlientNameQuery = read("get_products_by_customer_name.sql");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String read(String scriptFileName) throws Exception {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        }
    }

    public List<String> fetchProductByСlientName(String name) {
        var result = jdbcTemplate.queryForList(
                fetchProductByСlientNameQuery,
                Map.of("name",name.toLowerCase()),
                String.class);
        return result;
    }
}
