package org.example.demo.service;

import org.example.demo.entity.Tag;
import org.example.demo.model.request.CreateTagRequest;

import java.util.List;

public interface TagService {

    List<Tag> getListTag();

    Tag createTag(CreateTagRequest request);

    Tag updateTag(long id,CreateTagRequest request);

    void enableTag(long id);

    void deleleTag(long id);

}
