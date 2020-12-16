package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Domain.Purchase;
import com.example.forumbackend.Domain.Upfile;
import com.example.forumbackend.Mapper.UpfileMapper;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UpFileService {
    @Autowired
    private UpfileMapper upfileMapper;

    @Autowired
    private UpFileService upFileService;
    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private ResourceService resourceService;

    public Upfile findByID(Integer fid){
        return upfileMapper.selectById(fid);
    }

    public Upfile findByRID(Integer rid){
        QueryWrapper<Upfile> qw=new QueryWrapper<>();
        qw.eq("upfile_resource_id",rid);
        return upfileMapper.selectOne(qw);
    }

    public void addupfile(Upfile upfile){
        upfileMapper.insert(upfile);
    }

    public void changeinfo(Integer fid,String intro,String keywords,String title){
        UpdateWrapper<Upfile> uw=new UpdateWrapper<>();
        uw.eq("upfile_id",fid);
        Upfile upfile=new Upfile();
        if(intro!=null){
            //uw.set("upfile_intro",intro);
            upfile.setIntro(intro);
        }
        if(keywords!=null){
            //uw.set("upfile_keywords",keywords);
            upfile.setKeywords(keywords);
        }
        if(title!=null){
            //uw.set("upfile_title",title);
            upfile.setTitle(title);
        }
        upfileMapper.update(upfile,uw);
    }

    public Integer getcount(){
        return upfileMapper.selectCount(null);
    }

    @Transactional
    public ResponseResult<Upfile>  download(Integer fid,Integer uid){
        Upfile upfile=upFileService.findByID(fid);
        if(upfile==null)
            return Response.makeRsp(ResultCode.RESOURCE_NOT_EXIST.code, "下载文件不存在");
        ForumResource forumResource=resourceService.findresourceByrid(upfile.getResourceid());
        if(forumResource.getUID().equals(uid))
                    return Response.makeOKRsp(upfile);
        Purchase purchase=purchaseService.findByUIDRID(uid,upfile.getResourceid());
        if(purchase==null){
            return  Response.makeRsp(ResultCode.RESOURCE_NOT_PURCHASED.code, "还未购买资源");
        }
        else return Response.makeOKRsp(upfile);
    }

    public void add_purchasetime(Integer rid){
        upfileMapper.add_purchasetime(rid);
    }

    public List<Upfile> search(Integer pageindex,Integer pagesize,
                         Boolean latest,Boolean hottest,List<String> strings){
        QueryWrapper<Upfile> qw=new QueryWrapper<>();
        qw.orderByDesc(latest!=null&&latest.equals(true),"created_time");
        qw.orderByDesc(hottest!=null&&hottest.equals(true),"purchase_time");
        if(strings!=null&&strings.size()>0) {
            for (String str : strings) {
                qw.or().like("upfile_title", str);
                qw.or().like("upfile_filename", str);
                qw.or().like("upfile_keywords", str);
                qw.or().like("upfile_intro", str);
            }
        }
        Page<Upfile> page=new Page<>(pageindex,pagesize);
        upfileMapper.selectPage(page,qw);
        return page.getRecords();
    }

    public Integer searchcount(
                               Boolean latest,Boolean hottest,List<String> strings){
        QueryWrapper<Upfile> qw=new QueryWrapper<>();
        qw.orderByDesc(latest!=null&&latest.equals(true),"created_time");
        qw.orderByDesc(hottest!=null&&hottest.equals(true),"purchase_time");
        if(strings!=null&&strings.size()>0) {
            for (String str : strings) {
                qw.or().like("upfile_title", str);
                qw.or().like("upfile_filename", str);
                qw.or().like("upfile_keywords", str);
                qw.or().like("upfile_intro", str);
            }
        }
        return upfileMapper.selectCount(qw);
    }

}
