package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forumbackend.Domain.Artical;
import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Mapper.ArticalMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.acl.LastOwnerException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticalService {
    @Autowired
    private ArticalMapper articalMapper;

    @Autowired
    private ResourceService resourceService;

    public void insert(Artical artical){
        articalMapper.insert(artical);
    }
    public void update(Artical artical){
        articalMapper.updateById(artical);
    }

    public Integer getcount(){
        return articalMapper.selectCount(null);
    }

    public List<Artical> getarticals(Integer pageindex, Integer pagesize){
        Page<Artical> page=new Page<Artical>(pageindex,pagesize);
        articalMapper.selectPage(page,null);
        return page.getRecords();
    }

    public Artical findBYRID(Integer rid){
        QueryWrapper<Artical> qw=new QueryWrapper<>();
        qw.eq("article_resource_id",rid);
        return articalMapper.selectOne(qw);
    }

    public List<Artical> findByRIDlist(List<Integer> rids){
        QueryWrapper<Artical> qw=new QueryWrapper<>();
        qw.in("article_resource_id",rids);
        return articalMapper.selectList(qw);
    }

    public void updateview(List<Integer> aids){
        UpdateWrapper<Artical> uw=new UpdateWrapper<>();
        uw.in("artical_id",aids).setSql(" article_view=article_view+1 ");
        articalMapper.update(null,uw);
    }

    public void updateview(Integer aids){
        UpdateWrapper<Artical> uw=new UpdateWrapper<>();
        uw.eq("artical_id",aids).setSql(" article_view=article_view+1 ");
        articalMapper.update(null,uw);
    }

    public void setlastrepliedtime(Integer rid, LocalDateTime time){
        UpdateWrapper<Artical> uw=new UpdateWrapper<>();
        uw.eq("article_resource_id",rid);
        uw.set("resource_last_reply_time",time);
    }

    public List<Artical> search(Integer pageindex,Integer pagesize,
                              Boolean latest, Boolean hottest,Boolean latestreplied,
                              List<String> strings){
        QueryWrapper<Artical> qw=new QueryWrapper<>();
        if(strings!=null&&strings.size()>0){
            for(String str:strings){
                qw.or().like("article_title",str);
                qw.or().like("article_keywords",str);
                qw.or().like("article_intro",str);
                qw.or().like("article_content",str);
            }
        }
        if(latest!=null&&latest.equals(true))
            qw.orderByDesc("created_time");
        qw.orderByDesc(hottest!=null&&hottest.equals(true),"article_view");
        qw.orderByDesc(latestreplied!=null&&latestreplied.equals(true),"last_reply_time");
        Page<Artical> page=new Page<>(pageindex,pagesize);
        articalMapper.selectPage(page,qw);
        return page.getRecords();
    }

    public Integer searchcount(
                                Boolean latest, Boolean hottest,Boolean latestreplied,
                                List<String> strings){
        QueryWrapper<Artical> qw=new QueryWrapper<>();
        if(strings!=null&&strings.size()>0){
            for(String str:strings){
                qw.or().like("article_title",str);
                qw.or().like("article_keywords",str);
                qw.or().like("article_intro",str);
                qw.or().like("article_content",str);
            }
        }
        if(latest!=null&&latest.equals(true))
            qw.orderByDesc("created_time");
        qw.orderByDesc(hottest!=null&&hottest.equals(true),"article_view");
        qw.orderByDesc(latestreplied!=null&&latestreplied.equals(true),"last_reply_time");
       return articalMapper.selectCount(qw);
    }

}
