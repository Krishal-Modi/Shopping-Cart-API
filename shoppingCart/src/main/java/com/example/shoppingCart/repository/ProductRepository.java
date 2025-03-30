package com.example.shoppingCart.repository;

import com.example.shoppingCart.entity.Product;
import com.example.shoppingCart.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Get Product By Name
    List<Product> findByProductName(String productName);

    // Get Product By Category
    List<Product> findByProductCategory(ProductCategory productCategory);

    // Get By Product Name or Category
    @Query(value = "SELECT * FROM products p WHERE " +
            "LOWER(p.product_name) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(p.product_category) LIKE LOWER(CONCAT('%', :search, '%'))",
            nativeQuery = true)
    List<Product> searchProducts(@Param("search") String search);


}
