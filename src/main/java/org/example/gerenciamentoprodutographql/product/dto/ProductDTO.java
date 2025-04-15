package org.example.gerenciamentoprodutographql.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer stock;
}
