package com.example.shoppingCart.controller;

import com.example.shoppingCart.entity.Product;
import com.example.shoppingCart.enums.ProductCategory;
import com.example.shoppingCart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shopkeeper")
public class ProductController {

    @Autowired
    private ProductService productService;


    // Adding The Product
    @PostMapping("/insert")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    // Getting All Products
    @GetMapping("/listAll")
    public List<Product> listAll(){
        return productService.fetchProducts();
    }

    // Get Product By ID
    @GetMapping("/getById/{id}")
    public Optional<Product> getById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    // Get Product By Name
    @GetMapping("/getByName/{name}")
    public List<Product> getByName(@PathVariable String name){
        return productService.findProductByName(name);
    }

    // Get Product By Category
//    @GetMapping("/category")
//    public ResponseEntity<List<Product>> getByCategory(@RequestParam String category) {
//        try {
//            ProductCategory productCategory = ProductCategory.valueOf(category.toUpperCase());
//            List<Product> products = productService.findProductByCategory(String.valueOf(productCategory));
//            return ResponseEntity.ok(products);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(Collections.emptyList());
//        }
//    }

    @GetMapping("/category")
    public ResponseEntity<List<Product>> getByCategory(@RequestParam String category) {
        List<Product> products = productService.findProductByCategory(category);

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList()); // Return 404 if no products found
        }

        return ResponseEntity.ok(products);
    }


    // Delete Product By ID
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        boolean product = productService.deleteProductById(id);

        if (product) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Product deleted successfully");
    }

    // Update Product By ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        try {
            Product product = productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
