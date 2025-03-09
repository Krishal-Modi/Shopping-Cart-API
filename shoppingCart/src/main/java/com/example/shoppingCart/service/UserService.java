package com.example.shoppingCart.service;

import com.example.shoppingCart.entity.Product;
import com.example.shoppingCart.entity.User;
import com.example.shoppingCart.enums.ProductCategory;
import com.example.shoppingCart.repository.ProductRepository;
import com.example.shoppingCart.repository.UserRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public User addCustomer(User user){
        return userRepository.save(user);
    }

    public String deleteUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return "User deleted successfully.";
        } else {
            return "User not found.";
        }
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            if (updatedUser.getFirstName() != null) existingUser.setFirstName(updatedUser.getFirstName());
            if (updatedUser.getLastName() != null) existingUser.setLastName(updatedUser.getLastName());
            if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
            if (updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());
            if (updatedUser.getPhoneNumber() != null) existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            if (updatedUser.getAddress() != null) existingUser.setAddress(updatedUser.getAddress());
            if (updatedUser.getDob() != null) existingUser.setDob(updatedUser.getDob());
            return userRepository.save(existingUser);
        }).orElse(null);
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
}
