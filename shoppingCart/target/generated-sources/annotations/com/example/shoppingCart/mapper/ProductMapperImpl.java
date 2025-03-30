package com.example.shoppingCart.mapper;

import com.example.shoppingCart.entity.Product;
import com.example.shoppingCart.model.ProductModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-29T21:59:38+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductModel productToProductModel(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductModel productModel = new ProductModel();

        productModel.setProductName( product.getProductName() );
        productModel.setProductCategory( product.getProductCategory() );
        productModel.setProductDescription( product.getProductDescription() );
        productModel.setProductPrice( product.getProductPrice() );
        productModel.setProductRating( product.getProductRating() );
        productModel.setProductId( product.getProductId() );

        return productModel;
    }

    @Override
    public Product productModelToProduct(ProductModel productModel) {
        if ( productModel == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductName( productModel.getProductName() );
        product.setProductCategory( productModel.getProductCategory() );
        product.setProductDescription( productModel.getProductDescription() );
        product.setProductPrice( productModel.getProductPrice() );
        product.setProductRating( productModel.getProductRating() );
        product.setProductId( productModel.getProductId() );

        return product;
    }

    @Override
    public Product updateProductModel(ProductModel productModel, Product product) {
        if ( productModel == null ) {
            return product;
        }

        product.setProductId( productModel.getProductId() );
        product.setProductName( productModel.getProductName() );
        product.setProductCategory( productModel.getProductCategory() );
        product.setProductDescription( productModel.getProductDescription() );
        product.setProductPrice( productModel.getProductPrice() );
        product.setProductRating( productModel.getProductRating() );

        return product;
    }

    @Override
    public List<ProductModel> productListToProductModelList(List<Product> productList) {
        if ( productList == null ) {
            return null;
        }

        List<ProductModel> list = new ArrayList<ProductModel>( productList.size() );
        for ( Product product : productList ) {
            list.add( productToProductModel( product ) );
        }

        return list;
    }
}
