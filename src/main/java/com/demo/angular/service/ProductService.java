package com.demo.angular.service;

import com.demo.angular.model.Account;
import com.demo.angular.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {

    List<Product> getAllProducts();

    Product saveProduct(Product product);

    Product getProduct(Long id);

    Product updateProduct(Product product);

    void deleteProduct(Long id);

    List<String> getAllBrands();

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByPriceRange(Double upperBound);

    List<Product> getProductsByPriceLessThan(Double maxPrice);

    List<Product> getProductsByPriceGreaterThan(Double minPrice);

    List<Product> getProductsByBrandAndPriceGreaterThan(String brand,Double upperBound);

    List<Product> getProductsByBrandAndPriceLessThan(String brand,Double upperBound);

    List<Product> searchProductsByName(String keyword);

    List<Product> searchProductsByBrand(String keyword);

    List<Product> searchProductsByColor(String keyword);

    List<Product> searchByFields(String name, Double price, Integer amount, String color,String brand);

    Page<Product> getProducts(String keyword,int page, int size);





}
