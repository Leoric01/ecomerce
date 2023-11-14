package com.leoric.eshop.models;

import jakarta.persistence.*;

@Entity
public class Chair {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
