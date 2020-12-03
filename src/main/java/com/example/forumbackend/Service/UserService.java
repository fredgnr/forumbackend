package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.forumbackend.Domain.User;
import com.example.forumbackend.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> findAll(){
        return userMapper.selectList(null);
    }

    public User findByUID(Integer uid){
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("uid",uid);
        return userMapper.selectOne(qw);
    }

    public User findByAccount(String account){
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("user_account",account);
        return userMapper.selectOne(qw);
    }

    public List<User> findByFullName(String name){
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("user_name",name);
        return userMapper.selectList(qw);
    }

    public List<User> findByPartName(String name){
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.like("user_name",name);
        return userMapper.selectList(qw);
    }

    public User findByEmail(String email){
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("user_email",email);
        return userMapper.selectOne(qw);
    }

    public void changeName(Integer uid,String name){
        UpdateWrapper<User> uw=new UpdateWrapper<>();
        uw.eq("uid",uid).set("user_name",name);
        userMapper.update(null,uw);
    }

    public void changePassword(Integer uid,String password){
        UpdateWrapper<User> uw=new UpdateWrapper<>();
        uw.eq("uid",uid).set("user_password",password);
        userMapper.update(null,uw);
    }

    public void insertUser(User user){
        userMapper.insert(user);
    }

    public void deleteUser(Integer uid){
        userMapper.deleteById(uid);
    }
}
