package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.forumbackend.Domain.Section;
import com.example.forumbackend.Mapper.SectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    @Autowired
    private SectionMapper sectionMapper;

    public List<Section> findAll(){
        return sectionMapper.selectList(null);
    }

    public void addsection(Section section){
        sectionMapper.insert(section);
    }

    public Section findByID(Integer id){
        QueryWrapper<Section> qw=new QueryWrapper<>();
        qw.eq("section_id",id);
        return sectionMapper.selectOne(qw);
    }
}
