package org.example.demo.repository;

import org.example.demo.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    @Query(value = "Select * from Blog order by id desc limit :limit",nativeQuery = true)
    List<Blog> getListNewest(int limit);

}
