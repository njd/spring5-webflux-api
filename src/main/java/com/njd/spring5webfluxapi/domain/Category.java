package com.njd.spring5webfluxapi.domain;

import lombok.Builder;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Category {
    @Id
    private String id;

    private String description;

    public Category() {
    }

    public Category(String id, String description) {
        this.id = id;
        this.description = description;
    }
}
