package com.example.shoppingCart.mapper;

import com.example.shoppingCart.entity.Cart;
import com.example.shoppingCart.entity.Product;
import com.example.shoppingCart.entity.User;
import com.example.shoppingCart.model.CartModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-25T19:37:02+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartModel cartToCartModel(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartModel cartModel = new CartModel();

        cartModel.setUserId( cartUserUserId( cart ) );
        cartModel.setProductId( cartProductProductId( cart ) );
        cartModel.setProductName( cartProductProductName( cart ) );
        Long productPrice = cartProductProductPrice( cart );
        if ( productPrice != null ) {
            cartModel.setProductPrice( productPrice.doubleValue() );
        }
        if ( cart.getQuantity() != null ) {
            cartModel.setQuantity( cart.getQuantity() );
        }

        return cartModel;
    }

    @Override
    public Cart cartModelToCart(CartModel cartModel) {
        if ( cartModel == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setQuantity( cartModel.getQuantity() );

        return cart;
    }

    private Long cartUserUserId(Cart cart) {
        if ( cart == null ) {
            return null;
        }
        User user = cart.getUser();
        if ( user == null ) {
            return null;
        }
        Long userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }

    private Long cartProductProductId(Cart cart) {
        if ( cart == null ) {
            return null;
        }
        Product product = cart.getProduct();
        if ( product == null ) {
            return null;
        }
        Long productId = product.getProductId();
        if ( productId == null ) {
            return null;
        }
        return productId;
    }

    private String cartProductProductName(Cart cart) {
        if ( cart == null ) {
            return null;
        }
        Product product = cart.getProduct();
        if ( product == null ) {
            return null;
        }
        String productName = product.getProductName();
        if ( productName == null ) {
            return null;
        }
        return productName;
    }

    private Long cartProductProductPrice(Cart cart) {
        if ( cart == null ) {
            return null;
        }
        Product product = cart.getProduct();
        if ( product == null ) {
            return null;
        }
        Long productPrice = product.getProductPrice();
        if ( productPrice == null ) {
            return null;
        }
        return productPrice;
    }
}
