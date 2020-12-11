package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forumbackend.Domain.User;
import com.example.forumbackend.Domain.User_Info;
import com.example.forumbackend.Mapper.UserInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public void insertinfo(User_Info userInfo){
        userInfoMapper.insert(userInfo);
    }

    public void addintro(Integer uid,String intro){
        UpdateWrapper<User_Info> uw=new UpdateWrapper<>();
        uw.eq("user_id",uid);
        User_Info userInfo=new User_Info();
        userInfo.setUserIntro(intro);
        userInfoMapper.update(userInfo,uw);
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

    public List<User_Info> findAll(){
        return userInfoMapper.selectList(null);
    }

    public List<User_Info> findAlldesc(){
        QueryWrapper<User_Info> qw=new QueryWrapper<>();
        qw.orderByDesc("user_point");
        return userInfoMapper.selectList(qw);
    }

    public List<User_Info> finddescbypage(Integer pageindex,Integer pagesize){
        QueryWrapper<User_Info> qw=new QueryWrapper<>();
        Page<User_Info> page=new Page<>(pageindex,pagesize);
        qw.orderByDesc("user_point");
        userInfoMapper.selectPage(page,qw);
        return page.getRecords();
    }

    public void addpointbyrid(Integer point, Integer rid){
        userInfoMapper.addpointbyrid(point,rid);
    };
}
