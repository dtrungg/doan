package org.example.demo.repository;

import org.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value ="Select * from Orders where user_id = :id order by id desc",nativeQuery = true)
    List<Order> getOrderByUser(long id);
}
