package org.example.demo.service.impl;

import org.example.demo.entity.Tag;
import org.example.demo.exception.NotFoundException;
import org.example.demo.model.request.CreateTagRequest;
import org.example.demo.repository.TagRepository;
import org.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getListTag() {
        return tagRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Tag createTag(CreateTagRequest request) {
        Tag tag = new Tag();
        tag.setName(request.getName());
        tag.setEnable(false);
        tagRepository.save(tag);
        return tag;
    }

    @Override
    public Tag updateTag(long id, CreateTagRequest request) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Foud Tag"));
        tag.setName(request.getName());
        tagRepository.save(tag);
        return tag;
    }

    @Override
    public void deleleTag(long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Foud Tag"));
        tagRepository.delete(tag);
    }

    @Override
    public void enableTag(long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found Tag With Id: " + id));
        if (tag.isEnable()) {
            tag.setEnable(false);
        } else {
            tag.setEnable(true);
        }
        tagRepository.save(tag);
    }

}
