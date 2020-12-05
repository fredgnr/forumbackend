package com.example.forumbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootTest
class ForumbackendApplicationTests {

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


}
