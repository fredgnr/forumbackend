package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.Communication.Downfile;
import com.example.forumbackend.Domain.ForumResource;
import com.example.forumbackend.Domain.Purchase;
import com.example.forumbackend.Domain.Section;
import com.example.forumbackend.Domain.Upfile;
import com.example.forumbackend.Service.PurchaseService;
import com.example.forumbackend.Service.ResourceService;
import com.example.forumbackend.Service.SectionService;
import com.example.forumbackend.Service.UpFileService;
import com.example.forumbackend.Utils.CookieUtil;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.spi.CopyOnWrite;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private PurchaseService purchaseService;



    @PostMapping("/upload")
    @Transactional
    @ApiOperation(value = "上传文件")
    public ResponseResult<Upfile> upload(
            HttpServletRequest request,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) Integer price,
            @RequestParam Integer sectionid,
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
        resource.setSectionID(sectionid);
        Integer uid=cookieUtil.getuid(request);
        resource.setUID(uid);
        resource.setZan(0);
        resource.setLastReplyUID(null);
        resource.setType(filetype);
        resource.setPrice(price!=null?price:0);
        resource.setCreatedtime(LocalDateTime.now());
        resource.setLastreplytime(null);
        resourceService.addresource(resource);

        //生成文件存储路径
        String format = sdf.format(new Date(System.currentTimeMillis()));
        String folder = basepath + format;
        String oldname=file.getOriginalFilename();
        String newfilename= UUID.randomUUID().toString()+oldname.substring(oldname.lastIndexOf("."), oldname.length());
        File newfile=new  File(folder,newfilename);

        Upfile upfile=new Upfile();

        upfile.setResourceid(resource.getRID());
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

    @PutMapping("/changeinfo")
    @Transactional
    @ApiOperation(value = "修改文件信息")
    public ResponseResult<Upfile> changeinfo(
            HttpServletRequest request,
            @ApiParam("文件fid") Integer fid,
            @RequestParam(required = false) String introduction,
            @RequestParam(required = false) String keywords,
            @RequestParam(required = false) String title
    ){
        Upfile upfile=upFileService.findByID(fid);
        if(upfile==null){
            return Response.makeRsp(ResultCode.FILE_NOT_EXIST.code, "此文件不存在");
        }
        ForumResource forumResource=resourceService.findresourceByrid(upfile.getResourceid());
        if(!forumResource.getUID().equals(cookieUtil.getuid(request))){
            return Response.makeRsp(ResultCode.FILE_NOT_BELONGS_TO_YOU.code, "修改的资源不是由本账号上传");
        }
        upFileService.changeinfo(fid,introduction,keywords,title);
        return Response.makeOKRsp();
    }

    @GetMapping("/all")
    @Transactional
    public ResponseResult<Integer> getallcount(){
        return Response.makeOKRsp(upFileService.getcount());
    }

    @GetMapping("/byuser")
    @Transactional
    public ResponseResult<Integer> getbyuser(Integer uid){
        return Response.makeOKRsp(resourceService.getfilecountbyuid(uid));
    }

    @GetMapping("/download")
    public ResponseResult<Upfile> download(HttpServletRequest request, HttpServletResponse response, Integer fid){
        Integer uid=cookieUtil.getuid(request);
        /*Upfile upfile=upFileService.findByID(fid);
        if(upfile==null)
            return Response.makeRsp(ResultCode.RESOURCE_NOT_EXIST.code, "下载文件不存在");
        Purchase purchase=purchaseService.findByUIDRID(uid,upfile.getResourceid());
        if(purchase==null){
            return  Response.makeRsp(ResultCode.RESOURCE_NOT_PURCHASED.code, "还未购买资源");
        }*/
        ResponseResult<Upfile> result=upFileService.download(fid,uid);
        if(result.getData()==null)
            return  result;
        Downfile downfile=new Downfile();
        Upfile upfile=result.getData();
        downfile.setUpfile(upfile);
        try (InputStream inputStream=new FileInputStream(new File(upfile.getPath()));
             OutputStream outputStream=response.getOutputStream();){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename="+upfile.getFilename());
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

}
