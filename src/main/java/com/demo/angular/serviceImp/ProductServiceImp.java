package com.demo.angular.serviceImp;

import com.demo.angular.model.Product;
import com.demo.angular.repository.ProductRepository;
import com.demo.angular.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<String> getAllBrands() {
        return productRepository.getAllBrands();
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByPriceRange(Double upperBound) {
        return productRepository.getProductsByPriceRange(upperBound);
    }

    @Override
    public List<Product> getProductsByPriceLessThan(Double maxPrice) {
        return productRepository.findByPriceLessThan(maxPrice);
    }

    @Override
    public List<Product> getProductsByPriceGreaterThan(Double minPrice) {
        return productRepository.findByPriceGreaterThan(minPrice);
    }

    @Override
    public List<Product> getProductsByBrandAndPriceGreaterThan(String brand, Double upperBound) {
        return productRepository.getProductsByBrandAndPriceGreaterThan(brand, upperBound);
    }

    @Override
    public List<Product> getProductsByBrandAndPriceLessThan(String brand, Double upperBound) {
        return productRepository.getProductsByBrandAndPriceLessThan(brand, upperBound);
    }





    @Override
    public List<Product> searchProductsByName(String keyword) {
        // Sử dụng repository để tìm kiếm sản phẩm theo từ khóa
        return productRepository.findByKeywordByName(keyword);
    }


    @Override
    public List<Product> searchProductsByBrand(String keyword) {
        // Sử dụng repository để tìm kiếm sản phẩm theo từ khóa
        return productRepository.findByKeywordByBrand(keyword);
    }

    @Override
    public List<Product> searchProductsByColor(String keyword) {
        // Sử dụng repository để tìm kiếm sản phẩm theo từ khóa
        return productRepository.findByKeywordByColor(keyword);
    }

    @Override
    public List<Product> searchByFields(String name, Double price, Integer amount, String color, String brand) {
        return productRepository.findByFields(name, price, amount, color, brand);
    }

    @Override
    public Page<Product> getProducts(String keyword,int page, int size) {
        return productRepository.findByKeyword(keyword,PageRequest.of(page, size));
    }


}
