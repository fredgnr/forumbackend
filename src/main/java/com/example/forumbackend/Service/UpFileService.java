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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
