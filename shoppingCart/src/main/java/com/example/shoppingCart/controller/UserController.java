package com.example.shoppingCart.controller;

import com.example.shoppingCart.entity.Product;
import com.example.shoppingCart.entity.User;
import com.example.shoppingCart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/addNewUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User savedUser = userService.addCustomer(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String response = userService.deleteUserById(id);
        if (response.equals("User deleted successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User updatedUserData = userService.updateUser(id, updatedUser);
        if (updatedUserData != null) {
            return new ResponseEntity<>(updatedUserData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        }
    }


    // Get Product By Name
    @GetMapping("/getByName/{name}")
    public List<Product> getByName(@PathVariable String name){
        return userService.findProductByName(name);
    }

    // Get Product By Category
    @GetMapping("/category")
    public ResponseEntity<List<Product>> getByCategory(@RequestParam String category) {
        List<Product> products = userService.findProductByCategory(category);

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList()); // Return 404 if no products found
        }

        return ResponseEntity.ok(products);
    }
}
