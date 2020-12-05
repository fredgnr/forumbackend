package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Domain.Section;
import com.example.forumbackend.Domain.Upfile;
import com.example.forumbackend.Service.ResourceService;
import com.example.forumbackend.Service.SectionService;
import com.example.forumbackend.Service.UpFileService;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/upfile")
@Api(tags = "文件API")
public class UpfileController {

    @Value("${web.upload-path}")
    private String basepath;

    @Value("${resourcetype.file}")
    private Integer filetype;

    @Autowired
    private SimpleDateFormat sdf;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UpFileService upFileService;


    private static Integer getuid(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies)
            if(cookie.getName().equals("UID"))
                return new Integer(cookie.getValue());
        return null;
    }


    @PostMapping("/upload")
    @Transactional
    public ResponseResult<Upfile> upload(
            HttpServletRequest request,
            @RequestParam MultipartFile file,
            @RequestParam Integer sectionid,
            @RequestParam(required = false) Integer price,
            @RequestParam String introduction,
            @RequestParam String keywords,
            @RequestParam String title){
        if(file.isEmpty()){
            return Response.makeRsp(ResultCode.UPLOAD_FAILED.code, "上传失败，请重传");
        }
        Section section=sectionService.findByID(sectionid);
        if(section==null){
            return Response.makeRsp(ResultCode.SECTION_NOT_EXIST.code,"板块不存在");
        }
        ForumResource resource=new ForumResource();
        resource.setResource_Section_ID(sectionid);
        Integer uid=getuid(request);
        resource.setResource_user_id(uid);
        resource.setResource_zan(0);
        resource.setResource_last_reply_uid(null);
        resource.setResource_type(filetype);
        resource.setResource_price(price!=null?price:0);
        resource.setResource_created_time(LocalDateTime.now());
        resource.setResource_last_reply_time(null);
        resourceService.addresource(resource);

        //生成文件存储路径
        String format = sdf.format(new Date(System.currentTimeMillis()));
        String folder = basepath + format;
        String oldname=file.getOriginalFilename();
        String newfilename= UUID.randomUUID().toString()+oldname.substring(oldname.lastIndexOf("."), oldname.length());
        File newfile=new  File(folder,newfilename);

        Upfile upfile=new Upfile();

        upfile.setResourceid(resource.getResource_ID());
        upfile.setFilename(file.getOriginalFilename());
        upfile.setPath(newfile.getPath());
        upfile.setIntro(introduction);
        upfile.setTitle(title);
        upfile.setKeywords(keywords);
        try {
            File parent=newfile.getParentFile();
            if(!parent.exists())
                parent.mkdirs();
            file.transferTo(newfile);
            upFileService.addupfile(upfile);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.makeRsp(ResultCode.UPLOAD_FAILED.code, "上传失败，请重传");
        }
        return Response.makeOKRsp(upfile);
    }

}
