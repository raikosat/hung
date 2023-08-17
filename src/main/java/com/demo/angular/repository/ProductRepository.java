package com.demo.angular.repository;

import com.demo.angular.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT DISTINCT brand FROM Product")
    List<String> getAllBrands();

    // Trong interface ProductRepository
    @Query("SELECT p FROM Product p WHERE p.price < :upperBound OR p.price >= 500")
    List<Product> getProductsByPriceRange(@Param("upperBound") Double upperBound);



    /*@Query(value = "SELECT * FROM product;", nativeQuery = true)
    List<String> getAllBrands();*/
    List<Product> findByBrand(String brand);

    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice")
    List<Product> findByPriceLessThan(@Param("maxPrice") Double maxPrice);

    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice")
    List<Product> findByPriceGreaterThan(@Param("minPrice") Double minPrice);

    @Query("SELECT p FROM Product p WHERE p.brand = :brand AND p.price < :upperBound")
    List<Product> getProductsByBrandAndPriceLessThan(@Param("brand") String brand, @Param("upperBound") Double upperBound);

    @Query("SELECT p FROM Product p WHERE p.brand = :brand AND p.price >= :upperBound")
    List<Product> getProductsByBrandAndPriceGreaterThan(@Param("brand") String brand, @Param("upperBound") Double upperBound);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% ")
    List<Product> findByKeywordByName(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE p.color LIKE %:keyword% ")
    List<Product> findByKeywordByColor(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE p.brand LIKE %:keyword%")
    List<Product> findByKeywordByBrand(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE (:name IS NULL OR p.name LIKE %:name%) AND (:price IS NULL OR p.price = :price) AND (:amount IS NULL OR p.amount = :amount) AND (:color IS NULL OR p.color LIKE %:color%) AND (:brand IS NULL OR p.brand LIKE %:brand%) ")
    List<Product> findByFields(@Param("name") String name,@Param("price") Double price,@Param("amount") Integer amount,@Param("color") String color,@Param("brand") String brand);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.color LIKE %:keyword% OR p.brand LIKE %:keyword% ")
    Page<Product> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
