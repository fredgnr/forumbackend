package com.example.forumbackend.Contoller;

import com.example.forumbackend.Domain.Normal.Artical;
import com.example.forumbackend.Domain.Normal.ForumResource;
import com.example.forumbackend.Service.ArticalService;
import com.example.forumbackend.Service.ResourceService;
import com.example.forumbackend.Service.UserInfoService;
import com.example.forumbackend.Utils.CookieUtil;
import com.example.forumbackend.Utils.ResponseUitls.Response;
import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import com.example.forumbackend.Utils.ResponseUitls.ResultCode;
import io.swagger.annotations.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/artical")
@CrossOrigin
@Api(tags = "文章相关API")
public class ArticalController {
    @Value("${web.picture-path}")
    private String picturebase;

    @Autowired
    private CookieUtil cookieUtil;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ArticalService articalService;

    @Autowired
    private UserInfoService userInfoService;

    @Value("${resourcetype.artical}")
    private Integer articaltype;

    @Value("${points.viewartical}")
    private Integer pointsviewartical;

    @Value("${points.uploadartical}")
    private Integer pointsuploadartical;


    @GetMapping("/pciture")
    @ApiOperation("获得照片")
    public ResponseResult<String> getpic(@RequestParam@ApiParam(value = "请求文章的作者uid") Integer uid, String picname
                                        , HttpServletResponse response){
        try (InputStream inputStream=new FileInputStream(new File(picturebase+File.separator+uid,picname));
             OutputStream outputStream=response.getOutputStream();){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename="+picname);
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.makeOKRsp();
    }
    @PostMapping("/picture")
    @ApiOperation("上传图片")
    @ApiResponses({
            @ApiResponse(code = 500,message = "上传失败"),
            @ApiResponse(code = 102,message = "上传成功")
    })
    public ResponseResult<String> upload(HttpServletRequest request,
                                         @ApiParam(value = "文件列表") @RequestParam MultipartFile[] files){
        Integer uid=cookieUtil.getuid(request);
        List<String> pictures=new ArrayList<>();
        try {
                for(MultipartFile multipartFile:files) {
                    String filename=multipartFile.getOriginalFilename();
                    if(filename==null)
                        return Response.makeErrRsp("上传失败");
                    String folder=picturebase+File.separator+uid;
                    File picfile=new File(folder,filename);
                    File parentfile=picfile.getParentFile();
                    if(!parentfile.exists())
                        parentfile.mkdirs();
                    multipartFile.transferTo(picfile);
                    pictures.add(picfile.getAbsolutePath());
                }
        }
            catch (IOException e){
                e.printStackTrace();
                for(String path:pictures){
                    File newfile=new File(path);
                    if(newfile.exists())
                        newfile.delete();
                }
                return Response.makeErrRsp("上传失败");
            }
        return Response.makeOKRsp("上传成功");
    }

    @PostMapping("/artical")
    @Transactional
    @ApiOperation("上传文章")
    public ResponseResult<ForumResource> uploadartical(HttpServletRequest request,
                              @ApiParam("文章类，view和ID和RID置为null")@RequestBody Artical artical){
        Integer uid=cookieUtil.getuid(request);
        ForumResource resource=new ForumResource();
        resource.setUID(uid);
        resource.setZan(0);
        resource.setLastReplyUID(null);
        resource.setType(articaltype);
        resource.setPrice(0);
        resource.setCreatedtime(LocalDateTime.now());
        resource.setLastreplytime(null);
        resourceService.addresource(resource);
        artical.setResourceID(resource.getRID());
        artical.setView(0);
        artical.setCreatedtime(resource.getCreatedtime());
        artical.setLastreplytime(null);
        articalService.insert(artical);
        userInfoService.addpointbyrid(pointsuploadartical,resource.getRID());
        return Response.makeOKRsp(resource);
    }

    @PutMapping("/artical")
    @Transactional
    @ApiOperation(value = "修改文章（只上传需要修改的字段，不需要的修改的字段留成null）")
    @ApiResponses({
            @ApiResponse(code = 123,message = "文章修改失败"),
            @ApiResponse(code = 102,message = "修改成功")
    })
    public ResponseResult<Boolean> refineartical(HttpServletRequest request,
                                                 @RequestBody Artical artical){
        artical.setView(null);
        Integer uid=cookieUtil.getuid(request);
        ForumResource resource=resourceService.findresourceByrid(artical.getResourceID());
        if(resource==null||!resource.getRID().equals(artical.getResourceID())){
            return Response.makeRsp(ResultCode.ARTICAL_CHANGE_FAILED.code, "文章修改失败");
        }
        artical.setResourceID(null);
        articalService.update(artical);
        return Response.makeOKRsp(true);
    }

    @GetMapping("/getarticalcount")
    @Transactional
    @ApiOperation("查询文章总数量")
    public ResponseResult<Integer> getarticalcount(){
        return Response.makeOKRsp(articalService.getcount());
    }

    @GetMapping("/getarticalresources")
    @Transactional
    @ApiOperation("查询文章ForumResource")
    public ResponseResult<List<ForumResource>> getarticalresources(
            @RequestParam @ApiParam(value = "页码号") Integer pageindex,
            @RequestParam @ApiParam(value = "页大小")Integer pagesize){
        return Response.makeOKRsp(resourceService.getarticals(pageindex,pagesize));
    }

    @GetMapping("/getarticalcountbyuid")
    @Transactional
    @ApiOperation("查询某用户文章总数量")
    public ResponseResult<Integer> getarticalcountbyuid(Integer uid){
        return Response.makeOKRsp(resourceService.getarticalcountbyuid(uid));
    }

    @GetMapping("/getarticalresourcesbyuid")
    @Transactional
    @ApiOperation("查询某用户的文章ForumResource")
    public ResponseResult<List<ForumResource>> getarticalresourcesbyuid(
            Integer uid,
            @RequestParam @ApiParam(value = "页码号") Integer pageindex,
            @RequestParam @ApiParam(value = "页大小")Integer pagesize){
        return Response.makeOKRsp(resourceService.getarticalsbyuid(uid,pageindex,pagesize));
    }

    @GetMapping("/articalbyrid")
    @Transactional
    public ResponseResult<Artical> getarticalbyrid(Integer rid){
        Artical artical=articalService.findBYRID(rid);
        articalService.updateview(artical.getID());
        userInfoService.addpointbyrid(pointsviewartical,rid);
        return Response.makeOKRsp(artical);
    }

    @GetMapping("/articalsbyrids")
    @Transactional
    public ResponseResult<List<Artical>> getarticalsbyrids(@RequestBody List<Integer> rids){
        List<Artical> articals=articalService.findByRIDlist(rids);
        List<Integer> aids=new ArrayList<>();
        for(Artical artical:articals) {
            aids.add(artical.getID());
            userInfoService.addpointbyrid(pointsviewartical,artical.getResourceID());
        }
        articalService.updateview(aids);
        return Response.makeOKRsp(articals);
    }

    @GetMapping("/search")
    @Transactional
    public ResponseResult<List<ForumResource>> search(
            @RequestParam @ApiParam(value = "页码号") Integer pageindex,
            @RequestParam @ApiParam(value = "页大小")Integer pagesize,
            @RequestParam(required = false) @ApiParam(value = "是否为最新文章" ) Boolean latest,
            @RequestParam(required = false) @ApiParam(value = "是否为最火文章") Boolean hottest,
            @RequestParam(required = false) @ApiParam(value = "是否为最近被回复的文章") Boolean latestreplied,
            @RequestBody(required = false) @ApiParam(value = "搜索关键词")List<String> strings){
        List<Artical> articals=articalService.search(pageindex,pagesize,latest,hottest,latestreplied,strings);
        List<Integer> rids=new ArrayList<>();
        for (Artical artical:articals){
            rids.add(artical.getResourceID());
        }
        List<ForumResource> resources=resourceService.getbyrids(rids);
        return Response.makeOKRsp(resources);
    }

    @GetMapping("/searchcount")
    @Transactional
    public ResponseResult<Integer> searchcount(
            @RequestParam(required = false) @ApiParam(value = "是否为最新文章" ) Boolean latest,
            @RequestParam(required = false) @ApiParam(value = "是否为最火文章") Boolean hottest,
            @RequestParam(required = false) @ApiParam(value = "是否为最近被回复的文章") Boolean latestreplied,
            @RequestBody(required = false) @ApiParam(value = "搜索关键词")List<String> strings){
        return Response.makeOKRsp(articalService.searchcount(latest,hottest,latestreplied,strings));
    }

}
