package com.example.shoppingCart.controller;

import com.example.shoppingCart.model.ProductModel;
import com.example.shoppingCart.model.UserModel;
import com.example.shoppingCart.service.ProductService;
import com.example.shoppingCart.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "User", description = "Shopping Cart Operations")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    // Save Product
    @PostMapping("/addNewUser")
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel userModel) {
        UserModel savedUserModel = userService.addCustomer(userModel);
        return new ResponseEntity<>(savedUserModel, HttpStatus.OK);
    }


    // Delete Product By Id
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String response = userService.deleteUserById(id);
        if (response.equals("User deleted successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    // Update Product By Name
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel updatedUserModel) {
        UserModel updatedUser = userService.updateUser(id, updatedUserModel);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }


    // Get Product By Category
    @GetMapping("/category")
    public ResponseEntity<List<ProductModel>> getByCategory(@RequestParam String category) {
        List<ProductModel> products = userService.findProductByCategory(category);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public List<ProductModel> searchProducts(@RequestParam(required = false) String search) {
        return productService.searchProducts(search);
    }
}