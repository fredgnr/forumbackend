package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.forumbackend.Domain.User_Info;
import com.example.forumbackend.Mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public void insertinfo(User_Info userInfo){
        userInfoMapper.insert(userInfo);
    }

    public void addintro(Integer uid,String intro){
        UpdateWrapper<User_Info> uw=new UpdateWrapper<>();
        uw.eq("user_id",uid).set("user_Intro",intro);
        userInfoMapper.update(null,uw);
    }

    public User_Info findByUID(Integer uid){
        QueryWrapper<User_Info> qw=new QueryWrapper<>();
        qw.eq("user_id",uid);
        return userInfoMapper.selectOne(qw);
    }
    public void addzan(Integer uid){
        userInfoMapper.addzan(uid);
    }
    public void subzan(Integer uid){
        userInfoMapper.subzan(uid);
    }

    public void addpoint(Integer uid,Integer point){
        userInfoMapper.addpoint(point,uid);
    }

    public void addbalance(Integer uid,Integer extra){
        userInfoMapper.addbalance(extra,uid);
    }

    public void update(User_Info userInfo){
        userInfoMapper.updateById(userInfo);
    }

}
