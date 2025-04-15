package org.example.gerenciamentoprodutographql.product.resolver;

import lombok.RequiredArgsConstructor;
import org.example.gerenciamentoprodutographql.product.model.Product;
import org.example.gerenciamentoprodutographql.product.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductResolver {
    private final ProductService productService;

    @QueryMapping
    public List<Product> products() {
        return productService.getAllProducts();
    }

    @QueryMapping
    public Product productById(@Argument String id) {
        return productService.getProductById(id);
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument String category) {
        return productService.getProductsByCategory(category);
    }

    @MutationMapping
    public Product createProduct(
            @Argument String name,
            @Argument String description,
            @Argument String category,
            @Argument double price,
            @Argument int stock) {
        return productService.createProduct(name, description, category, price, stock);
    }

    @MutationMapping
    public Product updateProduct(
            @Argument String id,
            @Argument String name,
            @Argument String description,
            @Argument String category,
            @Argument Double price,
            @Argument Integer stock) {
        return productService.updateProduct(id, name, description, category, price, stock);
    }

    @MutationMapping
    public boolean deleteProduct(@Argument String id) {
        return productService.deleteProduct(id);
    }
}
