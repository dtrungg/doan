package org.example.demo.service.impl;

import org.example.demo.entity.Blog;
import org.example.demo.entity.Image;
import org.example.demo.entity.Tag;
import org.example.demo.entity.User;
import org.example.demo.exception.NotFoundException;
import org.example.demo.model.request.CreateBlogRequest;
import org.example.demo.repository.BlogRepository;
import org.example.demo.repository.ImageRepository;
import org.example.demo.repository.TagRepository;
import org.example.demo.repository.UserRepository;
import org.example.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Blog> getList() {
        return blogRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Blog getBlog(long id){
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        return blog;
    }

    @Override
    public Blog createBlog(CreateBlogRequest request) {
        Blog blog = new Blog();
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        blog.setImage(image);
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new NotFoundException("Not Found User"));
        blog.setUser(user);
        blog.setCreateAt(new Timestamp(System.currentTimeMillis()));
        Set<Tag> tags = new HashSet<>();
        for(Long tagId : request.getTags()){
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new NotFoundException("Not Found Tag"));
            tags.add(tag);
        }
        blog.setTags(tags);
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public Blog updateBlog(long id, CreateBlogRequest request) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        blog.setTitle(request.getTitle());
        blog.setDescription(request.getDescription());
        blog.setContent(request.getContent());
        Image image = imageRepository.findById(request.getImageId()).orElseThrow(() -> new NotFoundException("Not Found Image"));
        blog.setImage(image);
        Set<Tag> tags = new HashSet<>();
        for(Long tagId : request.getTags()){
            Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new NotFoundException("Not Found Tag"));
            tags.add(tag);
        }
        blog.setTags(tags);
        blogRepository.save(blog);
        return blog;
    }

    @Override
    public void deleteBlog(long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Blog"));
        blog.getTags().remove(this);
        blogRepository.delete(blog);
    }

    @Override
    public List<Blog> getListNewest(int limit) {
        List<Blog> list = blogRepository.getListNewest(limit);
        return list;
    }


}
