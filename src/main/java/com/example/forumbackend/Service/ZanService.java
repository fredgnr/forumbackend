package com.example.forumbackend.Service;

import com.example.forumbackend.Mapper.ZanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ZanService {
    @Autowired
    private ZanMapper zanMapper;
    @Autowired
    private ResourceService resourceService;

    public void addzan(Integer rid, Integer uid, LocalDateTime time){

    }
}
