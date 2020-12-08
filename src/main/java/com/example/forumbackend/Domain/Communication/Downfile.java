package com.example.forumbackend.Domain.Communication;

import com.example.forumbackend.Domain.Upfile;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Downfile {
    private Upfile upfile;
    private Byte[] bytes;
}
