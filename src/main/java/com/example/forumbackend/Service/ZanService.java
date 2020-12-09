package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forumbackend.Domain.Zan;
import com.example.forumbackend.Mapper.ZanMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ZanService {
    @Autowired
    private ZanMapper zanMapper;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @Value("${logic.deleted}")
    private static Integer DELETED;

    @Value("${logic.undeleted}")
    private static Integer UNDELETED;

    //向zan表中添加一条记录
    public Zan addzan(Integer rid, Integer uid){
        Zan zan=new Zan();
        zan.setRID(rid);
        zan.setUID(uid);
        zan.setStatus(UNDELETED);
        zanMapper.insert(zan);
        return zan;
    }

    public Zan findByRID_UID(Integer rid,Integer uid){
        QueryWrapper<Zan> qw=new QueryWrapper<>();
        qw.eq("zan_resource_id",rid)
                .eq("zan_uid",uid)
                .eq("zan_status",UNDELETED);
        return  zanMapper.selectOne(qw);
    }

    public void Delete(Integer zid){
        UpdateWrapper<Zan> qw=new UpdateWrapper<>();
        qw.eq("zan_id",zid).set("zan_status",DELETED);
    }

    public void DeleteBYRID_UID(Integer rid,Integer uid){
        UpdateWrapper<Zan> uw=new UpdateWrapper<>();
        uw.eq("zan_resource_id",rid)
                .eq("zan_uid",uid)
                .eq("zan_status",UNDELETED)
                .set("zan_status",DELETED);
    }

    public List<Zan> getzansbyRID(Integer rid,Integer pageindex,Integer pagesize){
        Page<Zan> page=new Page<>(pageindex,pagesize);
        QueryWrapper<Zan> qw=new QueryWrapper<>();
        qw.eq("zan_resource_id",rid)
            .eq("zan_status",UNDELETED);
        zanMapper.selectPage(page,qw);
        return page.getRecords();
    }
}
