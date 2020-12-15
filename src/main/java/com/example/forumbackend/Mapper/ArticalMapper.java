package com.example.forumbackend.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.forumbackend.Domain.Artical;
import com.example.forumbackend.Domain.Utils.ArticalResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticalMapper  extends BaseMapper<Artical> {

    public List<ArticalResource> search(Integer pageindex, Integer pagesize,
                                        Boolean latest, Boolean hottest, Boolean latestreplied,
                                        List<String> strings);
}
