package com.njd.spring5webfluxapi.controllers;

import com.njd.spring5webfluxapi.domain.Category;
import com.njd.spring5webfluxapi.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

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
        BDDMockito.given(categoryRepository.findById("1"))
            .willReturn(Mono.just(Category.builder().description("Category 1").build()));

        webTestClient.get()
            .uri("/api/v1/categories/1")
            .exchange()
            .expectBody(Category.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    void testCreateCategory() {
        BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
            .willReturn(Flux.just(Category.builder().build()));

        Mono<Category> categoryMono = Mono.just(Category.builder().description("Some Category").build());

        webTestClient.post()
            .uri("/api/v1/categories")
            .body(categoryMono, Category.class)
            .exchange()
            .expectStatus()
            .isCreated();
    }
}



