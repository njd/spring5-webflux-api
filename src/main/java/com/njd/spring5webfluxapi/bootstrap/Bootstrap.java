package com.njd.spring5webfluxapi.bootstrap;

import com.njd.spring5webfluxapi.domain.Category;
import com.njd.spring5webfluxapi.domain.Vendor;
import com.njd.spring5webfluxapi.repository.CategoryRepository;
import com.njd.spring5webfluxapi.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count().block() == 0) {
            log.info("Populating categories...");

            categoryRepository.save(Category.builder()
                .description("Fruits").build()).block();

            categoryRepository.save(Category.builder()
                .description("Nuts").build()).block();

            categoryRepository.save(Category.builder()
                .description("Breads").build()).block();

            categoryRepository.save(Category.builder()
                .description("Meats").build()).block();

            log.info("Loaded {} categories", categoryRepository.count().block());

            vendorRepository.save(Vendor.builder()
                .firstName("Bernard").lastName("Sumner").build()).block();

            vendorRepository.save(Vendor.builder()
                .firstName("Ian").lastName("Curtis").build()).block();

            vendorRepository.save(Vendor.builder()
                .firstName("Stephen").lastName("Morris").build()).block();

            vendorRepository.save(Vendor.builder()
                .firstName("Gillian").lastName("Gilbert").build()).block();

            log.info("Loaded {} vendors", vendorRepository.count().block());
        }
    }
}

// {"_id":{"$oid":"5f340a0d27654c33a6786326"},"firstName":"Bernard","lastName":"Sumner","_class":"com.njd.spring5webfluxapi.domain.Vendor"}