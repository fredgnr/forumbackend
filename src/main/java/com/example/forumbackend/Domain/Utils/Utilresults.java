package com.example.forumbackend.Domain.Utils;

import com.example.forumbackend.Utils.ResponseUitls.ResponseResult;
import lombok.Data;

@Data
public class Utilresults<T,X> {
    ResponseResult<T> responseResult;
    X data;
}
