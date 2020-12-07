package com.example.forumbackend.Utils.ResponseUitls;

public enum ResultCode {
    SUCCESS(102),
    PASSWORDWRONG(103),
    ACCOUNTNOTEXIST(104),
    LOGOUT_SUCCESS(105),
    LOGOUT_FAILED(106),
    UID_WRONG(107),//使用了其他用户的uid请求信息
    ACCOUNT_DUPLICATED(108),
    EMAIL_DUPLICATED(109),
    UPLOAD_FAILED(110),
    SECTION_NOT_EXIST(111),
    RESOURCE_NOT_EXIST(112),
    RESOURCE_ZAN_ALREADY(113),//此资源已被用户点赞
    FILE_NOT_BELONGS_TO_YOU(114),//试图修改不属于自己上传的资源的信息
    FILE_NOT_EXIST(115),//资源不存在
    INTERNAL_SERVER_ERROR(500);

    public final int code;

    ResultCode(int code) {
        this.code = code;
    }
}
