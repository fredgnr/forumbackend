package com.example.forumbackend.Service;

import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    public void addresource(ForumResource forumResource){
        resourceMapper.insert(forumResource);
    }
}
