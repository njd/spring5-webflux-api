package com.njd.spring5webfluxapi.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Vendor {

    @Id
    private String id;

    private String firstName;
    private String lastName;

    public Vendor() {
    }

    public Vendor(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
