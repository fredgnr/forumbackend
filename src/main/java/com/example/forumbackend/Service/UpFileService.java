package com.example.forumbackend.Service;

import com.example.forumbackend.Domain.Upfile;
import com.example.forumbackend.Mapper.UpfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpFileService {
    @Autowired
    private UpfileMapper upfileMapper;

    public void addupfile(Upfile upfile){
        upfileMapper.insert(upfile);
    }
}
