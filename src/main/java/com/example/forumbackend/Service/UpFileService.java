package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.forumbackend.Domain.Upfile;
import com.example.forumbackend.Mapper.UpfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpFileService {
    @Autowired
    private UpfileMapper upfileMapper;

    public Upfile findByID(Integer fid){
        return upfileMapper.selectById(fid);
    }

    public void addupfile(Upfile upfile){
        upfileMapper.insert(upfile);
    }

    public void changeinfo(Integer fid,String intro,String keywords,String title){
        UpdateWrapper<Upfile> uw=new UpdateWrapper<>();
        uw.eq("upfile_id",fid);
        if(intro!=null){
            uw.set("upfile_intro",intro);
        }
        if(keywords!=null){
            uw.set("upfile_keywords",keywords);
        }
        if(title!=null){
            uw.set("upfile_title",title);
        }
        upfileMapper.update(null,uw);
    }
}
