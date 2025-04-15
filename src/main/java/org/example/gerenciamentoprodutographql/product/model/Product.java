package org.example.gerenciamentoprodutographql.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable {
    private String id;
    private String name;
    private String description;
    private String category;
    private double price;
    private int stock;

    public Product(String name, String description, String category, double price, int stock) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
}