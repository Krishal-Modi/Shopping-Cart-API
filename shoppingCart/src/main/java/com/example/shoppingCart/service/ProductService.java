package com.example.shoppingCart.service;


import com.example.shoppingCart.entity.Product;
import com.example.shoppingCart.enums.ProductCategory;
import com.example.shoppingCart.repository.ProductRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;

    // Adding The Product
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    // Getting All Products
    public List<Product> fetchProducts(){
        return productRepository.findAll();
    }


    // Get Product By ID
    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }


    // Get Product By Name
    public List<Product> findProductByName(String productName){
        return productRepository.findByProductName(productName);
    }


    // Get Product By Category

    //    public List<Product> findProductByCategory(String categoryName) {
    //        ProductCategory category = ProductCategory.valueOf(categoryName.toUpperCase()); // Convert String to Enum
    //        return productRepository.findByProductCategory(ProductCategory.valueOf(String.valueOf(category)));
    //    }

    public List<Product> findProductByCategory(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return Collections.emptyList(); // Return empty if input is null or empty
        }
        categoryName = categoryName.trim().toUpperCase(); // Normalize input
        ProductCategory closestCategory = getBestMatchingCategory(categoryName);
        if (closestCategory != null) {
            return productRepository.findByProductCategory(closestCategory);
        }
        return Collections.emptyList(); // Return empty list if no match found
    }

    // Find the best matching category using both Levenshtein Distance and Partial Match
    private ProductCategory getBestMatchingCategory(String inputCategory) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        ProductCategory bestMatch = null;
        int minDistance = Integer.MAX_VALUE;
        for (ProductCategory category : ProductCategory.values()) {
            String categoryName = category.name();
            // Direct match (ignoring underscores and case differences)
            if (categoryName.replace("_", "").contains(inputCategory.replace("_", ""))) {
                return category; // If it contains the input, return immediately
            }
            // Check for typos (Levenshtein Distance)
            int distance = levenshtein.apply(categoryName, inputCategory);
            if (distance < minDistance && distance <= 3) { // Allowing up to 3-character difference
                minDistance = distance;
                bestMatch = category;
            }
        }
        return bestMatch; // Return best match or null if nothing is close enough
    }


    // Delete Product By ID
    public boolean deleteProductById(Long id) {
        if (productRepository.existsById(id))
        {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }


    // Update Product By ID
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setProductName(updatedProduct.getProductName());
            product.setProductCategory(updatedProduct.getProductCategory());
            product.setProductDescription(updatedProduct.getProductDescription());
            product.setProductPrice(updatedProduct.getProductPrice());
            product.setProductRating(updatedProduct.getProductRating());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }


}
