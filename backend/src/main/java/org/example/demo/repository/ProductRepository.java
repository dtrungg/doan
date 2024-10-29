package org.example.demo.repository;

import org.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "Select * from Product order by id desc limit :number",nativeQuery = true)
    List<Product> getListNewest(int number);

    @Query(value = "Select * from Product order by price limit 8 ",nativeQuery = true)
    List<Product> getListByPrice();

    @Query(value ="Select * from Product where category_id = :id order by rand() limit 4",nativeQuery = true)
    List<Product> findRelatedProduct(long id);

    @Query(value ="Select * from Product where category_id = :id",nativeQuery = true)
    List<Product> getListProductByCategory(long id);

    @Query(value = "Select * from Product where category_id = :id and price between :min and :max",nativeQuery = true)
    List<Product> getListProductByPriceRange(long id,int min,int max);

    @Query(value= "Select p from Product p where p.name like %:keyword% order by id desc")
    List<Product> searchProduct(String keyword);

}