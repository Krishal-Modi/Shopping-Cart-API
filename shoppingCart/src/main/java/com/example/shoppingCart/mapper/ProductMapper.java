package com.example.shoppingCart.mapper;

import com.example.shoppingCart.entity.Product;
import com.example.shoppingCart.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Entity To Model
    @Mapping(target="productName", source="productName")
    @Mapping(target="productCategory", source="productCategory")
    @Mapping(target="productDescription", source="productDescription")
    @Mapping(target="productPrice", source="productPrice")
    @Mapping(target="productRating", source="productRating")
    ProductModel productToProductModel(Product product);

    // Model To Entity
    @Mapping(target="productName", source="productName")
    @Mapping(target="productCategory", source="productCategory")
    @Mapping(target="productDescription", source="productDescription")
    @Mapping(target="productPrice", source="productPrice")
    @Mapping(target="productRating", source="productRating")
    Product productModelToProduct(ProductModel productModel);

    // Model To Entity
    Product updateProductModel(ProductModel productModel, @MappingTarget Product product);
}