package org.example.gerenciamentoprodutographql.product.service;

import lombok.RequiredArgsConstructor;
import org.example.gerenciamentoprodutographql.product.model.Product;
import org.example.gerenciamentoprodutographql.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product createProduct(String name, String description, String category, double price, int stock) {
        Product product = new Product(name, description, category, price, stock);
        return productRepository.save(product);
    }

    public Product updateProduct(String id, String name, String description, String category, Double price, Integer stock) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return null;
        }

        if (name != null) {
            product.setName(name);
        }

        if (description != null) {
            product.setDescription(description);
        }

        if (category != null) {
            product.setCategory(category);
        }

        if (price != null) {
            product.setPrice(price);
        }

        if (stock != null) {
            product.setStock(stock);
        }

        return productRepository.save(product);
    }

    public boolean deleteProduct(String id) {
        return productRepository.deleteById(id);
    }
}
