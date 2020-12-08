package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.forumbackend.Domain.Purchase;
import com.example.forumbackend.Domain.Upfile;
import com.example.forumbackend.Mapper.UpfileMapper;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpFileService {
    @Autowired
    private UpfileMapper upfileMapper;

    @Autowired
    private UpFileService upFileService;
    @Autowired
    private PurchaseService purchaseService;

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

    public Integer getcount(){
        return upfileMapper.selectCount(null);
    }

    @Transactional
    public ResponseResult<Upfile>  download(Integer fid,Integer uid){
        Upfile upfile=upFileService.findByID(fid);
        if(upfile==null)
            return Response.makeRsp(ResultCode.RESOURCE_NOT_EXIST.code, "下载文件不存在");
        Purchase purchase=purchaseService.findByUIDRID(uid,upfile.getResourceid());
        if(purchase==null){
            return  Response.makeRsp(ResultCode.RESOURCE_NOT_PURCHASED.code, "还未购买资源");
        }
        else return Response.makeOKRsp(upfile);
    }
}
