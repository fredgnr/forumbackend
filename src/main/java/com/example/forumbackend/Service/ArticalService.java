package com.example.forumbackend.Service;

import com.example.forumbackend.Mapper.ArticalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticalService {
    @Autowired
    private ArticalMapper articalMapper;
}
