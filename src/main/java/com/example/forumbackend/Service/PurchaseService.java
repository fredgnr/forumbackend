package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.forumbackend.Domain.Purchase;
import com.example.forumbackend.Mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseMapper purchaseMapper;

    public Purchase findByUIDRID(Integer uid,Integer rid){
        QueryWrapper<Purchase> qw=new QueryWrapper<>();
        qw.eq("purchase_resource_id",rid)
                .eq("purchase_user_id",uid);
        return purchaseMapper.selectOne(qw);
    }

    public void addpur(Purchase purchase){
        purchaseMapper.insert(purchase);
    }
    public Integer getcountbyuid(Integer uid){
        QueryWrapper<Purchase> qw=new QueryWrapper<>();
        qw.eq("purchase_user_id",uid);
        return purchaseMapper.selectCount(qw);
    }

    public List<Purchase> getpurchasesbyuid(Integer uid,Integer pageindex,Integer pagesize){
        Page<Purchase> page=new Page<>(pageindex,pagesize);
        QueryWrapper<Purchase> qw=new QueryWrapper<>();
        qw.eq("purchase_user_id",uid);
        purchaseMapper.selectPage(page,qw);
        return page.getRecords();
    }

    public Integer getcountbyrid(Integer rid){
        QueryWrapper<Purchase> qw=new QueryWrapper<>();
        qw.eq("purchase_resource_id",rid);
        return purchaseMapper.selectCount(qw);
    }

    public List<Purchase> getpurchasesbyrid(Integer uid,Integer pageindex,Integer pagesize){
        Page<Purchase> page=new Page<>(pageindex,pagesize);
        QueryWrapper<Purchase> qw=new QueryWrapper<>();
        qw.eq("purchase_resource_id",uid);
        purchaseMapper.selectPage(page,qw);
        return page.getRecords();
    }
}
