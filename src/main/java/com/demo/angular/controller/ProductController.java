package com.demo.angular.controller;

import com.demo.angular.exception.ResourceNotFoundException;
import com.demo.angular.model.Product;
import com.demo.angular.repository.ProductRepository;
import com.demo.angular.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("http://localhost:4200/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductServiceImp productServiceImp;

    //get all products
    @GetMapping("/products")
    public ResponseEntity<Page<Product>>getProductsList( @RequestParam(name = "name", defaultValue = "") String name,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "5") int size) {

        Page<Product> products = productServiceImp.getProducts(name,page, size);
        return ResponseEntity.ok(products);

    }
    //create product
    @PostMapping("/products")
    public Product CreateProducts(@RequestBody Product product){
        return productRepository.save(product);
    }

    //get product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product= productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with id: "+id));
        return ResponseEntity.ok(product);

    }

    //update product by ID
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long id,@RequestBody Product productDetails ) {
        productDetails.setId(id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with id: " + id));

        Product productUpdate = product;
        productUpdate = productServiceImp.updateProduct(productDetails);
        return ResponseEntity.ok(productUpdate);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable Long id){
        Product product= productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not exist with id: "+id));
        productRepository.delete(product);
        return ResponseEntity.ok(product);

    }

//    @GetMapping("/products/search/{keyword}")
//    public ResponseEntity<Page<Product>> searchProductByKeywords(@RequestParam(name = "key", required = false) String keyword,
//                                                 @RequestParam(name = "page", defaultValue = "0") int page,
//                                                 @RequestParam(name = "size", defaultValue = "5") int size){
//        Page<Product> products = productServiceImp.search(keyword, page, size);
//        return ResponseEntity.ok().body(products);
//    }



}
