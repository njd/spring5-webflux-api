package com.njd.spring5webfluxapi.controllers;

import com.njd.spring5webfluxapi.domain.Category;
import com.njd.spring5webfluxapi.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {

    WebTestClient webTestClient;
    CategoryRepository categoryRepository;
    CategoryController categoryController;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);

        categoryController = new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void list() {
        BDDMockito.given(categoryRepository.findAll())
            .willReturn(Flux.just(
                Category.builder().description("Category1").build(),
                Category.builder().description("Category2").build()
            ));

        webTestClient.get()
            .uri("/api/v1/categories")
            .exchange()
            .expectBodyList(Category.class)
            .hasSize(2);
    }

    @Test
    void getById() {
    }
}