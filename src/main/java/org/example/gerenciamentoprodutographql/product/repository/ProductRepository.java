package org.example.gerenciamentoprodutographql.product.repository;

import lombok.RequiredArgsConstructor;
import org.example.gerenciamentoprodutographql.product.model.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private static final String KEY_PREFIX = "product:";
    private static final String INDEX_KEY = "product:all";

    private final RedisTemplate<String, Object> redisTemplate;

    public List<Product> findAll() {
        Set<Object> productIds = redisTemplate.opsForSet().members(INDEX_KEY);
        if (productIds == null || productIds.isEmpty()) {
            return new ArrayList<>();
        }

        List<Product> products = new ArrayList<>();
        for (Object id : productIds) {
            String productKey = KEY_PREFIX + id.toString();
            Product product = (Product) redisTemplate.opsForValue().get(productKey);
            if (product != null) {
                products.add(product);
            }
        }

        return products;
    }

    public Product findById(String id) {
        return (Product) redisTemplate.opsForValue().get(KEY_PREFIX + id);
    }

    public List<Product> findByCategory(String category) {
        List<Product> allProducts = findAll();
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (Objects.equals(product.getCategory(), category)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(java.util.UUID.randomUUID().toString());
        }

        redisTemplate.opsForValue().set(KEY_PREFIX + product.getId(), product);
        redisTemplate.opsForSet().add(INDEX_KEY, product.getId());

        return product;
    }

    public boolean deleteById(String id) {
        redisTemplate.opsForSet().remove(INDEX_KEY, id);
        return redisTemplate.delete(KEY_PREFIX + id);
    }
}