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
    INTERNAL_SERVER_ERROR(500);

    public final int code;

    ResultCode(int code) {
        this.code = code;
    }
}
