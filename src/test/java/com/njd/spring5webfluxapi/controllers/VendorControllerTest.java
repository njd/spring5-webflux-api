package com.njd.spring5webfluxapi.controllers;

import com.njd.spring5webfluxapi.domain.Vendor;
import com.njd.spring5webfluxapi.repository.CategoryRepository;
import com.njd.spring5webfluxapi.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class VendorControllerTest {

    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @BeforeEach
    void setUp() {
        vendorRepository = BDDMockito.mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    void list() {
        BDDMockito.given(vendorRepository.findAll())
            .willReturn(Flux.just(
                Vendor.builder().firstName("Fred").lastName("Flintstone").build(),
                Vendor.builder().firstName("Barney").lastName("Rubble").build()
            ));

        webTestClient.get()
            .uri("/api/v1/vendors")
            .exchange()
            .expectBodyList(Vendor.class)
            .hasSize(2);
    }

    @Test
    void getById() {
        Vendor expected = Vendor.builder().firstName("Bam Bam").lastName("Rubble").build();

        BDDMockito.given(vendorRepository.findById("111"))
            .willReturn(Mono.just(expected));

        webTestClient.get()
            .uri("/api/v1/vendors/111")
            .exchange()
            .expectBody(Vendor.class)
            .isEqualTo(expected);
    }
}