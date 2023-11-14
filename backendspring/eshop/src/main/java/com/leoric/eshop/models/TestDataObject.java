package com.leoric.eshop.models;

import jakarta.persistence.*;

@Entity
public class TestDataObject {

    @Id
    private Long id;

    @Column
    private String username;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
