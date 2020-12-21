package com.example.forumbackend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.forumbackend.Domain.ChatRoom.Chat;
import com.example.forumbackend.Mapper.ChatMapper;
import com.example.forumbackend.Service.ChatService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
class ForumbackendApplicationTests {

    @Value("${chatype.group}")
    private Integer GroupType;

    @Value("${chatype.private}")
    private Integer PrivateType;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private ChatService chatService;

    @Test
    void contextLoads() {
    }

    @Test
    void testtime() throws IOException {
        System.out.println(LocalDateTime.now());
        String str="E:\\test\\test.txt";
        File dest=new File(str);
        dest.getParentFile().mkdirs();
        dest.createNewFile();
    }

    @Test
    void test2(){
        String test="test1"+File.separator+"test2";
        System.out.println(test);
    }

    @Test
    void test3(){
        QueryWrapper<Chat> qw=new QueryWrapper<>();
        qw.eq("receiveUID",1);
        qw.eq("mtype",PrivateType);
        qw.select("max(CreateTime) as CreateTime,sendUID");
        qw.groupBy("sendUID");
        qw.orderByDesc("CreateTime");
        List<Chat> list=chatMapper.selectList(qw);
        for(Chat chat:list)
            System.out.println(chat);
    }

    @Test
    public void test4(){
       chatService.getprivatechatbytime(1,0,20);
    }
}
