package org.example.demo.service;

import org.example.demo.entity.Blog;
import org.example.demo.model.request.CreateBlogRequest;

import java.util.List;

public interface BlogService {

    List<Blog> getList();

    List<Blog> getListNewest(int limit);

    Blog getBlog(long id);

    Blog createBlog(CreateBlogRequest request);

    Blog updateBlog(long id,CreateBlogRequest request);

    void deleteBlog(long id);

}
