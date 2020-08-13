package com.njd.spring5webfluxapi.repository;

import com.njd.spring5webfluxapi.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
