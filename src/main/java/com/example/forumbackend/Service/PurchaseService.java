package com.example.forumbackend.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.forumbackend.Domain.Purchase;
import com.example.forumbackend.Mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
