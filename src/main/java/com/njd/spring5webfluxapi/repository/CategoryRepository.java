package com.njd.spring5webfluxapi.repository;

import com.njd.spring5webfluxapi.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
    
}
