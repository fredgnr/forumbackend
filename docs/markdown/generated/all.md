# springboot利用swagger构建api文档


<a name="overview"></a>
## 概览
简单优雅的restfun风格


### 版本信息
*版本* : 1.0


### URI scheme
*域名* : 127.0.0.1:8080  
*基础路径* : /


### 标签

* chat-controller : Chat Controller
* resource-controller : Resource Controller
* 回复API(测试完成) : Reply Controller
* 文件API(完成测试) : Upfile Controller
* 文章相关API : Artical Controller
* 点赞相关api(测试完成) : Zan Controller
* 用户信息(测试完成) : User Controller
* 登录登出(测试完成) : Login Controller
* 购买资源 : Purchase Controller




<a name="paths"></a>
## 资源

<a name="chat-controller_resource"></a>
### Chat-controller
Chat Controller


<a name="creategroupusingpost"></a>
#### 创建群聊
```
POST /chat/CreateGroup
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**introduce**  <br>*可选*|群介绍|string|
|**Query**|**name**  <br>*可选*|群名称|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«ChatGroup»](#e30ca2e1fbb092c982560abcffcf3bd6)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/CreateGroup
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "createtime" : "2020-12-22 01:35:00",
    "gid" : 0,
    "introduce" : "string",
    "name" : "string"
  },
  "msg" : "string"
}
```


<a name="addgroupusingpost"></a>
#### 加群
```
POST /chat/addgroup
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**GID**  <br>*可选*|群id|integer (int32)|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功|无内容|
|**126**|群不存在|无内容|
|**200**|OK|[ResponseResult«boolean»](#0a2261a69c68d1053ed2559c393cc527)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/addgroup
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : true,
  "msg" : "string"
}
```


<a name="getgroupchatusingget"></a>
#### 获取某群中最近的特定数量的聊天记录
```
GET /chat/getgroupchat
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**gid**  <br>*可选*|gid|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|pagesize|integer (int32)|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功|无内容|
|**127**|未加群|无内容|
|**200**|OK|[ResponseResult«List«Chat»»](#d4fd7cedcb8c5dc04f176888e43cbb73)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/getgroupchat
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createTime" : "2020-12-22 01:35:00",
    "groupID" : 0,
    "id" : 0,
    "message" : "message test",
    "mtype" : 0,
    "receiveUID" : 0,
    "sendUID" : 0
  } ],
  "msg" : "string"
}
```


<a name="getgroupchat2usingget"></a>
#### 分页获取某群的聊天记录中早于某条特定记录的记录
```
GET /chat/getgroupchatbefore
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**cid**  <br>*可选*|cid|integer (int32)|
|**Query**|**gid**  <br>*可选*|gid|integer (int32)|
|**Query**|**size**  <br>*可选*|size|integer (int32)|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功|无内容|
|**127**|未加群|无内容|
|**200**|OK|[ResponseResult«List«Chat»»](#d4fd7cedcb8c5dc04f176888e43cbb73)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/getgroupchatbefore
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createTime" : "2020-12-22 01:35:00",
    "groupID" : 0,
    "id" : 0,
    "message" : "message test",
    "mtype" : 0,
    "receiveUID" : 0,
    "sendUID" : 0
  } ],
  "msg" : "string"
}
```


<a name="getgroupchatbytimeusingget"></a>
#### 获取与某用户存在聊天记录的群的列表，按照最后发送时间来确定
```
GET /chat/getgroupchatbytime
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|pageindex|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|pagesize|integer (int32)|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«int»»](#6f832e606553701536847de28df30e6d)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/getgroupchatbytime
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ 0 ],
  "msg" : "string"
}
```


<a name="getlistusingget"></a>
#### 获取论坛群总数量
```
GET /chat/getgroupitemlist
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**GID**  <br>*可选*|GID|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«GroupItem»»](#010091f7244d323dc6c6282d31d5c2db)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/getgroupitemlist
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "gid" : 0,
    "id" : 0,
    "uid" : 0
  } ],
  "msg" : "string"
}
```


<a name="getprivatechatusingget"></a>
#### 获取与某用户最近的特定条数的聊天记录
```
GET /chat/getprivatechat
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pagesize**  <br>*可选*|pagesize|integer (int32)|
|**Query**|**senduid**  <br>*可选*|senduid|integer (int32)|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«Chat»»](#d4fd7cedcb8c5dc04f176888e43cbb73)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/getprivatechat
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createTime" : "2020-12-22 01:35:00",
    "groupID" : 0,
    "id" : 0,
    "message" : "message test",
    "mtype" : 0,
    "receiveUID" : 0,
    "sendUID" : 0
  } ],
  "msg" : "string"
}
```


<a name="getprivatechat2usingget"></a>
#### 分页获取与某用户的聊天记录中早于某条特定记录的记录
```
GET /chat/getprivatechatbefore
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**CID**  <br>*可选*|CID|integer (int32)|
|**Query**|**senduid**  <br>*可选*|senduid|integer (int32)|
|**Query**|**size**  <br>*可选*|size|integer (int32)|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«Chat»»](#d4fd7cedcb8c5dc04f176888e43cbb73)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/getprivatechatbefore
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createTime" : "2020-12-22 01:35:00",
    "groupID" : 0,
    "id" : 0,
    "message" : "message test",
    "mtype" : 0,
    "receiveUID" : 0,
    "sendUID" : 0
  } ],
  "msg" : "string"
}
```


<a name="getprivatechatbytimeusingget"></a>
#### 获取与某用户存在的私聊的人的列表，按照最后发送时间来确定
```
GET /chat/getprivatechatbytime
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|pageindex|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|pagesize|integer (int32)|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«int»»](#6f832e606553701536847de28df30e6d)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/getprivatechatbytime
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ 0 ],
  "msg" : "string"
}
```


<a name="getprivatecountusingget"></a>
#### 获取某用户的存在的私聊的人的数量
```
GET /chat/getprivatecount
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/getprivatecount
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="searchusingget_1"></a>
#### 通过GID搜索群
```
GET /chat/searchgroupbyid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**gid**  <br>*可选*|gid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«ChatGroup»](#e30ca2e1fbb092c982560abcffcf3bd6)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/searchgroupbyid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "createtime" : "2020-12-22 01:35:00",
    "gid" : 0,
    "introduce" : "string",
    "name" : "string"
  },
  "msg" : "string"
}
```


<a name="searchusingget_2"></a>
#### 模糊搜索群
```
GET /chat/searchgroupbystring
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|pageindex|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|pagesize|integer (int32)|
|**Query**|**str**  <br>*可选*|str|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«ChatGroup»»](#99f690e40505fb2bd447c2d40ce02ad9)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/searchgroupbystring
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createtime" : "2020-12-22 01:35:00",
    "gid" : 0,
    "introduce" : "string",
    "name" : "string"
  } ],
  "msg" : "string"
}
```


<a name="sendgroupusingpost"></a>
#### 发送群聊
```
POST /chat/sendgroup
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**GID**  <br>*可选*|群id|integer (int32)|
|**Query**|**message**  <br>*可选*|发送内容|string|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功发送|无内容|
|**127**|未加群|无内容|
|**200**|OK|[ResponseResult«Chat»](#4b20c52ffaf425ee794978c9584e73a9)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/sendgroup
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "createTime" : "2020-12-22 01:35:00",
    "groupID" : 0,
    "id" : 0,
    "message" : "message test",
    "mtype" : 0,
    "receiveUID" : 0,
    "sendUID" : 0
  },
  "msg" : "string"
}
```


<a name="sendprivateusingpost"></a>
#### 发送私聊信息
```
POST /chat/sendprivate
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**message**  <br>*可选*|发送内容|string|
|**Query**|**receivedid**  <br>*可选*|发送对象uid|integer (int32)|
|**Body**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功发送|无内容|
|**124**|发送对象不存在|无内容|
|**129**|不能自己给自己发|无内容|
|**200**|OK|[ResponseResult«Chat»](#4b20c52ffaf425ee794978c9584e73a9)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/chat/sendprivate
```


###### 请求 body
```json
{ }
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "createTime" : "2020-12-22 01:35:00",
    "groupID" : 0,
    "id" : 0,
    "message" : "message test",
    "mtype" : 0,
    "receiveUID" : 0,
    "sendUID" : 0
  },
  "msg" : "string"
}
```


<a name="resource-controller_resource"></a>
### Resource-controller
Resource Controller


<a name="getbyridusingget"></a>
#### getbyrid
```
GET /resource/getbyrid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**rid**  <br>*可选*|rid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«ForumResource»](#2306c090eda0ace4811155e2b2961fd9)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/resource/getbyrid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "createdtime" : "2020-11-22 12:23:11",
    "lastReplyUID" : 0,
    "lastreplytime" : "string",
    "price" : 0,
    "rid" : 0,
    "sectionID" : 0,
    "type" : 0,
    "uid" : 0,
    "zan" : 0
  },
  "msg" : "string"
}
```


<a name="d82234eacdbf261314d48de6d9878afe"></a>
### 回复API(测试完成)
Reply Controller


<a name="getrepliesbyridusingget"></a>
#### 获取某资源的评论
```
GET /reply/repliesbyrid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|pageindex|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|pagesize|integer (int32)|
|**Query**|**rid**  <br>*可选*|资源RID|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«Reply»»](#718a368ab83d8e8ae937319dbdec1ae5)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/reply/repliesbyrid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "content" : "string",
    "id" : 0,
    "rid" : 0,
    "time" : "2020-11-22 12:23:11",
    "uid" : 0
  } ],
  "msg" : "string"
}
```


<a name="replycountbyridusingget"></a>
#### 获取某资源评论数量
```
GET /reply/replycountbyrid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**rid**  <br>*可选*|资源RID|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/reply/replycountbyrid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="replyresourceusingpost"></a>
#### 评论资源
```
POST /reply/replyresource
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**content**  <br>*可选*|评论内容|string|
|**Query**|**rid**  <br>*可选*|资源RID|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功获取|无内容|
|**112**|资源不存在|无内容|
|**200**|OK|[ResponseResult«Reply»](#c01fdf3f9b88d406145d821dd854bd06)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/reply/replyresource
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "content" : "string",
    "id" : 0,
    "rid" : 0,
    "time" : "2020-11-22 12:23:11",
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="6d224b9313097b60c13b5abca723c5ca"></a>
### 文件API(完成测试)
Upfile Controller


<a name="getallcountusingget"></a>
#### 获得论坛所有文件数量
```
GET /upfile/allcount
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/allcount
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="changeinfousingput"></a>
#### 修改文件信息
```
PUT /upfile/changeinfo
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**fid**  <br>*可选*|文件fid|integer (int32)|
|**Query**|**introduction**  <br>*可选*|introduction|string|
|**Query**|**keywords**  <br>*可选*|keywords|string|
|**Query**|**title**  <br>*可选*|title|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«Upfile»](#65a4589c7cc60c7cdc4dc8c0ce8a6f4a)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/changeinfo
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "fileid" : 0,
    "filename" : "test.zip",
    "intro" : "test intro",
    "keywords" : "java",
    "purchasetime" : 0,
    "resourceid" : 0,
    "title" : "test title"
  },
  "msg" : "string"
}
```


<a name="downloadusingget"></a>
#### 下载文件(完成测试)
```
GET /upfile/download
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**fid**  <br>*可选*|要下载文件的FID|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功下载|无内容|
|**112**|下载文件不存在|无内容|
|**118**|还未购买资源|无内容|
|**200**|OK|[ResponseResult«Upfile»](#65a4589c7cc60c7cdc4dc8c0ce8a6f4a)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/download
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "fileid" : 0,
    "filename" : "test.zip",
    "intro" : "test intro",
    "keywords" : "java",
    "purchasetime" : 0,
    "resourceid" : 0,
    "title" : "test title"
  },
  "msg" : "string"
}
```


<a name="getfilesusingget"></a>
#### 分页查询文件的资源信息（Resource）
```
GET /upfile/files
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|页码号|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|页大小|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«ForumResource»»](#40c17371c2d3c0df11edd8708ee94302)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/files
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createdtime" : "2020-11-22 12:23:11",
    "lastReplyUID" : 0,
    "lastreplytime" : "string",
    "price" : 0,
    "rid" : 0,
    "sectionID" : 0,
    "type" : 0,
    "uid" : 0,
    "zan" : 0
  } ],
  "msg" : "string"
}
```


<a name="getbyridusingget_1"></a>
#### 获取文件信息
```
GET /upfile/getbyrid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**rid**  <br>*可选*|所查询文件的RID|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«Upfile»](#65a4589c7cc60c7cdc4dc8c0ce8a6f4a)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/getbyrid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "fileid" : 0,
    "filename" : "test.zip",
    "intro" : "test intro",
    "keywords" : "java",
    "purchasetime" : 0,
    "resourceid" : 0,
    "title" : "test title"
  },
  "msg" : "string"
}
```


<a name="getfilecountbyuidusingget"></a>
#### 获取某一用户上传的文件数量
```
GET /upfile/getfilecountbyuid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/getfilecountbyuid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="getfilesbyuidusingget"></a>
#### 获取某一用户上传的文件
```
GET /upfile/getfilesbyuid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|页码号|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|页大小|integer (int32)|
|**Query**|**uid**  <br>*可选*|所查询用户的UID|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«ForumResource»»](#40c17371c2d3c0df11edd8708ee94302)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/getfilesbyuid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createdtime" : "2020-11-22 12:23:11",
    "lastReplyUID" : 0,
    "lastreplytime" : "string",
    "price" : 0,
    "rid" : 0,
    "sectionID" : 0,
    "type" : 0,
    "uid" : 0,
    "zan" : 0
  } ],
  "msg" : "string"
}
```


<a name="searchusingget_3"></a>
#### search
```
GET /upfile/search
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**hottest**  <br>*可选*|是否为最火文件|boolean|
|**Query**|**latest**  <br>*可选*|是否为最新文件|boolean|
|**Query**|**pageindex**  <br>*可选*|页码号|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|页大小|integer (int32)|
|**Body**|**strings**  <br>*可选*|搜索关键词|< string > array|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«ForumResource»»](#40c17371c2d3c0df11edd8708ee94302)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/search
```


###### 请求 body
```json
[ "string" ]
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createdtime" : "2020-11-22 12:23:11",
    "lastReplyUID" : 0,
    "lastreplytime" : "string",
    "price" : 0,
    "rid" : 0,
    "sectionID" : 0,
    "type" : 0,
    "uid" : 0,
    "zan" : 0
  } ],
  "msg" : "string"
}
```


<a name="searchcountusingget_1"></a>
#### searchcount
```
GET /upfile/searchcount
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**hottest**  <br>*可选*|是否为最火文件|boolean|
|**Query**|**latest**  <br>*可选*|是否为最新文件|boolean|
|**Body**|**strings**  <br>*可选*|搜索关键词|< string > array|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/searchcount
```


###### 请求 body
```json
[ "string" ]
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="uploadusingpost_1"></a>
#### 上传文件(测试完成)
```
POST /upfile/upload
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**introduction**  <br>*可选*|introduction|string|
|**Query**|**keywords**  <br>*可选*|keywords|string|
|**Query**|**price**  <br>*可选*|price|integer (int32)|
|**Query**|**sectionid**  <br>*可选*|sectionid|integer (int32)|
|**Query**|**title**  <br>*可选*|title|string|
|**FormData**|**file**  <br>*可选*|文件本身|file|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|上传成功|无内容|
|**110**|上传失败，请重传|无内容|
|**111**|板块不存在|无内容|
|**121**|上传文件为空|无内容|
|**200**|OK|[ResponseResult«Upfile»](#65a4589c7cc60c7cdc4dc8c0ce8a6f4a)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `multipart/form-data`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/upfile/upload
```


###### 请求 formData
```json
"file"
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "fileid" : 0,
    "filename" : "test.zip",
    "intro" : "test intro",
    "keywords" : "java",
    "purchasetime" : 0,
    "resourceid" : 0,
    "title" : "test title"
  },
  "msg" : "string"
}
```


<a name="225d7fa15fee4c1bbddd9354912a03c8"></a>
### 文章相关API
Artical Controller


<a name="uploadarticalusingpost"></a>
#### 上传文章
```
POST /artical/artical
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**sectionid**  <br>*可选*|sectionid|integer (int32)|
|**Body**|**artical**  <br>*可选*|文章类，view和ID和RID置为null|[Artical](#artical)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«ForumResource»](#2306c090eda0ace4811155e2b2961fd9)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/artical
```


###### 请求 body
```json
{
  "content" : "string",
  "createdtime" : "2020-11-22 12:23:11",
  "id" : 0,
  "introduction" : "string",
  "keywords" : "string",
  "lastreplytime" : "2020-11-22 12:23:11",
  "resourceID" : 0,
  "title" : "string",
  "view" : 0
}
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "createdtime" : "2020-11-22 12:23:11",
    "lastReplyUID" : 0,
    "lastreplytime" : "string",
    "price" : 0,
    "rid" : 0,
    "sectionID" : 0,
    "type" : 0,
    "uid" : 0,
    "zan" : 0
  },
  "msg" : "string"
}
```


<a name="refinearticalusingput"></a>
#### 修改文章（只上传需要修改的字段，不需要的修改的字段留成null）
```
PUT /artical/artical
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**artical**  <br>*必填*|artical|[Artical](#artical)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|修改成功|无内容|
|**123**|文章修改失败|无内容|
|**200**|OK|[ResponseResult«boolean»](#0a2261a69c68d1053ed2559c393cc527)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/artical
```


###### 请求 body
```json
{
  "content" : "string",
  "createdtime" : "2020-11-22 12:23:11",
  "id" : 0,
  "introduction" : "string",
  "keywords" : "string",
  "lastreplytime" : "2020-11-22 12:23:11",
  "resourceID" : 0,
  "title" : "string",
  "view" : 0
}
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : true,
  "msg" : "string"
}
```


<a name="getarticalbyridusingget"></a>
#### getarticalbyrid
```
GET /artical/articalbyrid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**rid**  <br>*可选*|rid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«Artical»](#61e3f0cb484fc54999013e7b63698085)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/articalbyrid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "content" : "string",
    "createdtime" : "2020-11-22 12:23:11",
    "id" : 0,
    "introduction" : "string",
    "keywords" : "string",
    "lastreplytime" : "2020-11-22 12:23:11",
    "resourceID" : 0,
    "title" : "string",
    "view" : 0
  },
  "msg" : "string"
}
```


<a name="getarticalsbyridsusingget"></a>
#### getarticalsbyrids
```
GET /artical/articalsbyrids
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**rids**  <br>*必填*|rids|< integer (int32) > array|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«Artical»»](#af4d76c2fd02cbff89dd897ee8435152)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/articalsbyrids
```


###### 请求 body
```json
[ 0 ]
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "content" : "string",
    "createdtime" : "2020-11-22 12:23:11",
    "id" : 0,
    "introduction" : "string",
    "keywords" : "string",
    "lastreplytime" : "2020-11-22 12:23:11",
    "resourceID" : 0,
    "title" : "string",
    "view" : 0
  } ],
  "msg" : "string"
}
```


<a name="getarticalcountusingget"></a>
#### 查询文章总数量
```
GET /artical/getarticalcount
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/getarticalcount
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="getarticalcountbyuidusingget"></a>
#### 查询某用户文章总数量
```
GET /artical/getarticalcountbyuid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/getarticalcountbyuid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="getarticalresourcesusingget"></a>
#### 查询文章ForumResource
```
GET /artical/getarticalresources
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|页码号|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|页大小|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«ForumResource»»](#40c17371c2d3c0df11edd8708ee94302)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/getarticalresources
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createdtime" : "2020-11-22 12:23:11",
    "lastReplyUID" : 0,
    "lastreplytime" : "string",
    "price" : 0,
    "rid" : 0,
    "sectionID" : 0,
    "type" : 0,
    "uid" : 0,
    "zan" : 0
  } ],
  "msg" : "string"
}
```


<a name="getarticalresourcesbyuidusingget"></a>
#### 查询某用户的文章ForumResource
```
GET /artical/getarticalresourcesbyuid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|页码号|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|页大小|integer (int32)|
|**Query**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«ForumResource»»](#40c17371c2d3c0df11edd8708ee94302)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/getarticalresourcesbyuid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createdtime" : "2020-11-22 12:23:11",
    "lastReplyUID" : 0,
    "lastreplytime" : "string",
    "price" : 0,
    "rid" : 0,
    "sectionID" : 0,
    "type" : 0,
    "uid" : 0,
    "zan" : 0
  } ],
  "msg" : "string"
}
```


<a name="getpicusingget"></a>
#### 获得照片
```
GET /artical/pciture
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**picname**  <br>*可选*|picname|string|
|**Query**|**uid**  <br>*可选*|请求文章的作者uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«string»](#2fcc07109ac56c98de62a28511fb6955)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/pciture
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : "string",
  "msg" : "string"
}
```


<a name="uploadusingpost"></a>
#### 上传图片
```
POST /artical/picture
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**FormData**|**files**  <br>*可选*|文件列表|< file > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|上传成功|无内容|
|**200**|OK|[ResponseResult«string»](#2fcc07109ac56c98de62a28511fb6955)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|
|**500**|上传失败|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/picture
```


###### 请求 formData
```json
"file"
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : "string",
  "msg" : "string"
}
```


<a name="searchusingget"></a>
#### search
```
GET /artical/search
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**hottest**  <br>*可选*|是否为最火文章|boolean|
|**Query**|**latest**  <br>*可选*|是否为最新文章|boolean|
|**Query**|**latestreplied**  <br>*可选*|是否为最近被回复的文章|boolean|
|**Query**|**pageindex**  <br>*可选*|页码号|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|页大小|integer (int32)|
|**Query**|**strings**  <br>*可选*|搜索关键词|< string > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«ForumResource»»](#40c17371c2d3c0df11edd8708ee94302)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/search
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "createdtime" : "2020-11-22 12:23:11",
    "lastReplyUID" : 0,
    "lastreplytime" : "string",
    "price" : 0,
    "rid" : 0,
    "sectionID" : 0,
    "type" : 0,
    "uid" : 0,
    "zan" : 0
  } ],
  "msg" : "string"
}
```


<a name="searchcountusingget"></a>
#### searchcount
```
GET /artical/searchcount
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**hottest**  <br>*可选*|是否为最火文章|boolean|
|**Query**|**latest**  <br>*可选*|是否为最新文章|boolean|
|**Query**|**latestreplied**  <br>*可选*|是否为最近被回复的文章|boolean|
|**Query**|**strings**  <br>*可选*|搜索关键词|< string > array(multi)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/artical/searchcount
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="ede2893b54bf53e611fab6de152b11c6"></a>
### 点赞相关api(测试完成)
Zan Controller


<a name="zan_resourceusingpost"></a>
#### 点赞某资源
```
POST /zan/addzan
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**rid**  <br>*可选*|rid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«Zan»](#4007e9bcd76c9e8104d0d22a80839973)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/zan/addzan
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "id" : 0,
    "rid" : 0,
    "status" : 0,
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="deletezanusingdelete"></a>
#### 取消点赞
```
DELETE /zan/deletezan
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**rid**  <br>*可选*|rid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«Zan»](#4007e9bcd76c9e8104d0d22a80839973)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/zan/deletezan
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "id" : 0,
    "rid" : 0,
    "status" : 0,
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="getrzanusingget"></a>
#### 获得资源的赞数量
```
GET /zan/getzancount
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**rid**  <br>*可选*|rid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/zan/getzancount
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="getrzansusingget"></a>
#### 获得具体点赞信息
```
GET /zan/getzans
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|pageindex|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|pagesize|integer (int32)|
|**Query**|**rid**  <br>*可选*|rid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«Zan»»](#ce985ad7d1f2ec7f4354e716a6c2b825)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/zan/getzans
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "id" : 0,
    "rid" : 0,
    "status" : 0,
    "uid" : 0
  } ],
  "msg" : "string"
}
```


<a name="if_zannusingget"></a>
#### 查询用户是否点赞了某资源
```
GET /zan/ifzan
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**rid**  <br>*可选*|rid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«boolean»](#0a2261a69c68d1053ed2559c393cc527)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/zan/ifzan
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : true,
  "msg" : "string"
}
```


<a name="0f8721a3f50e49971fd31bd0ad289bcb"></a>
### 用户信息(测试完成)
User Controller


<a name="changeintrousingput"></a>
#### 修改个人简介，账号从cookie中获取
```
PUT /user/changeintro
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**intro**  <br>*可选*|intro|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«User_Info»](#2fa1ffabb8c07cafb7a3ce222ff36c08)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/changeintro
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "infoID" : 0,
    "userBalance" : 0,
    "userID" : 0,
    "userIntro" : "string",
    "userPoint" : 0,
    "userZan" : 0
  },
  "msg" : "string"
}
```


<a name="changenameusingput"></a>
#### 修改姓名，具体账号从cookie中获取
```
PUT /user/changename
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**name**  <br>*可选*|name|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«User»](#1a0d4ca65a630c3c812d7e1c11e0cdd8)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/changename
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "account" : "string",
    "email" : "513317651@qq.com",
    "name" : "string",
    "password" : "string",
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="changepasswordusingput"></a>
#### 修改密码，账号从cookie中获取
```
PUT /user/changepassword
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**password**  <br>*可选*|password|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«User»](#1a0d4ca65a630c3c812d7e1c11e0cdd8)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/changepassword
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "account" : "string",
    "email" : "513317651@qq.com",
    "name" : "string",
    "password" : "string",
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="getuserinfousingget"></a>
#### 获取User_Info信息(测试完成)
```
GET /user/getinfo
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«User_Info»](#2fa1ffabb8c07cafb7a3ce222ff36c08)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/getinfo
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "infoID" : 0,
    "userBalance" : 0,
    "userID" : 0,
    "userIntro" : "string",
    "userPoint" : 0,
    "userZan" : 0
  },
  "msg" : "string"
}
```


<a name="getuserinfobyuidusingget"></a>
#### 根据uid获取User_Info信息，隐藏余额和infoID(测试完成)
```
GET /user/getinfobyuid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«User_Info»](#2fa1ffabb8c07cafb7a3ce222ff36c08)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/getinfobyuid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "infoID" : 0,
    "userBalance" : 0,
    "userID" : 0,
    "userIntro" : "string",
    "userPoint" : 0,
    "userZan" : 0
  },
  "msg" : "string"
}
```


<a name="getrankbyuidusingget"></a>
#### 获取某用户的排名
```
GET /user/getrankbyuid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/getrankbyuid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="getranksusingget"></a>
#### 获取排行榜某页所有人的UID
```
GET /user/getranks
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|pageindex|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|pagesize|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«int»»](#6f832e606553701536847de28df30e6d)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/getranks
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ 0 ],
  "msg" : "string"
}
```


<a name="getuserusingget"></a>
#### 获取自己的账户类(测试完成)
```
GET /user/getuser
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«User»](#1a0d4ca65a630c3c812d7e1c11e0cdd8)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/getuser
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "account" : "string",
    "email" : "513317651@qq.com",
    "name" : "string",
    "password" : "string",
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="getuserbyuidusingget"></a>
#### 根据UID获取User,隐藏密码邮箱(测试完成)
```
GET /user/getuserbyuid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**uid**  <br>*可选*|uid|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«User»](#1a0d4ca65a630c3c812d7e1c11e0cdd8)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/getuserbyuid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "account" : "string",
    "email" : "513317651@qq.com",
    "name" : "string",
    "password" : "string",
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="singupusingpost"></a>
#### 注册账号(已测试)
```
POST /user/singup
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Body**|**user**  <br>*必填*|user|[User](#user)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功注册|无内容|
|**108**|账号已被注册|无内容|
|**109**|邮箱已被注册|无内容|
|**200**|OK|[ResponseResult«User»](#1a0d4ca65a630c3c812d7e1c11e0cdd8)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/user/singup
```


###### 请求 body
```json
{
  "account" : "string",
  "email" : "513317651@qq.com",
  "name" : "string",
  "password" : "string",
  "uid" : 0
}
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "account" : "string",
    "email" : "513317651@qq.com",
    "name" : "string",
    "password" : "string",
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="32d6996c0017e502ff6b34ced4f6e4eb"></a>
### 登录登出(测试完成)
Login Controller


<a name="logintestusingget"></a>
#### 登录
```
GET /login/in
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**account**  <br>*可选*|账号|string|
|**Query**|**password**  <br>*可选*|密码|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功登录|无内容|
|**103**|密码错误|无内容|
|**104**|账号错误|无内容|
|**200**|OK|[ResponseResult«User»](#1a0d4ca65a630c3c812d7e1c11e0cdd8)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/login/in
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "account" : "string",
    "email" : "513317651@qq.com",
    "name" : "string",
    "password" : "string",
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="logoutusingget"></a>
#### 登出
```
GET /login/out
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功登出|无内容|
|**200**|OK|[ResponseResult«User»](#1a0d4ca65a630c3c812d7e1c11e0cdd8)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/login/out
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "account" : "string",
    "email" : "513317651@qq.com",
    "name" : "string",
    "password" : "string",
    "uid" : 0
  },
  "msg" : "string"
}
```


<a name="4498c428919ea5caeaa0656fa6f53488"></a>
### 购买资源
Purchase Controller


<a name="getcountbyuidusingget"></a>
#### 获取请求用户购买过的资源数量
```
GET /purchase/countbyuid
```


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«int»](#3fe2e2379dbf233eedc7933fe7cf413b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/purchase/countbyuid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : 0,
  "msg" : "string"
}
```


<a name="getpurchasesbyridusingget"></a>
#### 获取用户发布的某资源的购买记录
```
GET /purchase/getpurchasesbyrid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|页索引|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|页号码|integer (int32)|
|**Query**|**rid**  <br>*可选*|资源RID|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|请求成功|无内容|
|**115**|请求资源不存在|无内容|
|**120**|请求资源不属于此用户上传|无内容|
|**200**|OK|[ResponseResult«List«Purchase»»](#b0ed0b1a956a48a8fe19ad33e3cf851b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/purchase/getpurchasesbyrid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "PurchaseTime" : "string",
    "id" : 0,
    "price" : 0,
    "purchaseTime" : "string",
    "rid" : 0,
    "uid" : 0
  } ],
  "msg" : "string"
}
```


<a name="getpurchasesbyuidusingget"></a>
#### 获取用户的购买记录
```
GET /purchase/getpurchasesbyuid
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**pageindex**  <br>*可选*|页索引|integer (int32)|
|**Query**|**pagesize**  <br>*可选*|页号码|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[ResponseResult«List«Purchase»»](#b0ed0b1a956a48a8fe19ad33e3cf851b)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/purchase/getpurchasesbyuid
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : [ {
    "PurchaseTime" : "string",
    "id" : 0,
    "price" : 0,
    "purchaseTime" : "string",
    "rid" : 0,
    "uid" : 0
  } ],
  "msg" : "string"
}
```


<a name="purusingpost"></a>
#### 购买资源
```
POST /purchase/purchase
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**rid**  <br>*可选*|购买资源的RID|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**102**|成功购买|无内容|
|**112**|请求资源不存在|无内容|
|**116**|余额不足|无内容|
|**117**|此资源已购买|无内容|
|**125**|文章不需要购买|无内容|
|**200**|OK|[ResponseResult«Purchase»](#1f5e32f207f2f322123647894a71a4ea)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`


##### HTTP请求示例

###### 请求 path
```
/purchase/purchase
```


##### HTTP响应示例

###### 响应 200
```json
{
  "code" : 0,
  "data" : {
    "PurchaseTime" : "string",
    "id" : 0,
    "price" : 0,
    "purchaseTime" : "string",
    "rid" : 0,
    "uid" : 0
  },
  "msg" : "string"
}
```




<a name="definitions"></a>
## 定义

<a name="artical"></a>
### Artical
文章


|名称|说明|类型|
|---|---|---|
|**content**  <br>*可选*|**样例** : `"string"`|string|
|**createdtime**  <br>*可选*|资源上传时间  <br>**样例** : `"2020-11-22 12:23:11"`|string (date-time)|
|**id**  <br>*可选*|**样例** : `0`|integer (int32)|
|**introduction**  <br>*可选*|**样例** : `"string"`|string|
|**keywords**  <br>*可选*|**样例** : `"string"`|string|
|**lastreplytime**  <br>*可选*|最后评论时间  <br>**样例** : `"2020-11-22 12:23:11"`|string (date-time)|
|**resourceID**  <br>*可选*|**样例** : `0`|integer (int32)|
|**title**  <br>*可选*|**样例** : `"string"`|string|
|**view**  <br>*可选*|**样例** : `0`|integer (int32)|


<a name="chat"></a>
### Chat
聊天记录


|名称|说明|类型|
|---|---|---|
|**createTime**  <br>*可选*|发送时间  <br>**样例** : `"2020-12-22 01:35:00"`|string (date-time)|
|**groupID**  <br>*可选*|**样例** : `0`|integer (int32)|
|**id**  <br>*可选*|**样例** : `0`|integer (int32)|
|**message**  <br>*可选*|消息内容  <br>**样例** : `"message test"`|string|
|**mtype**  <br>*可选*|消息记录种类(群聊or私聊)  <br>**样例** : `0`|integer (int32)|
|**receiveUID**  <br>*可选*|**样例** : `0`|integer (int32)|
|**sendUID**  <br>*可选*|**样例** : `0`|integer (int32)|


<a name="chatgroup"></a>
### ChatGroup
聊天群类


|名称|说明|类型|
|---|---|---|
|**createtime**  <br>*可选*|群创建时间  <br>**样例** : `"2020-12-22 01:35:00"`|string (date-time)|
|**gid**  <br>*可选*|**样例** : `0`|integer (int32)|
|**introduce**  <br>*可选*|**样例** : `"string"`|string|
|**name**  <br>*可选*|**样例** : `"string"`|string|


<a name="forumresource"></a>
### ForumResource
资源


|名称|说明|类型|
|---|---|---|
|**createdtime**  <br>*可选*|资源上传时间  <br>**样例** : `"2020-11-22 12:23:11"`|string (date-time)|
|**lastReplyUID**  <br>*可选*|**样例** : `0`|integer (int32)|
|**lastreplytime**  <br>*可选*|最后评论时间  <br>**样例** : `"string"`|string (date-time)|
|**price**  <br>*可选*|**样例** : `0`|integer (int32)|
|**rid**  <br>*可选*|**样例** : `0`|integer (int32)|
|**sectionID**  <br>*可选*|**样例** : `0`|integer (int32)|
|**type**  <br>*可选*|**样例** : `0`|integer (int32)|
|**uid**  <br>*可选*|**样例** : `0`|integer (int32)|
|**zan**  <br>*可选*|**样例** : `0`|integer (int32)|


<a name="groupitem"></a>
### GroupItem
加入群记录


|名称|说明|类型|
|---|---|---|
|**gid**  <br>*可选*|**样例** : `0`|integer (int32)|
|**id**  <br>*可选*|**样例** : `0`|integer (int32)|
|**uid**  <br>*可选*|**样例** : `0`|integer (int32)|


<a name="purchase"></a>
### Purchase
用户购买记录


|名称|说明|类型|
|---|---|---|
|**PurchaseTime**  <br>*可选*|购买时间  <br>**样例** : `"string"`|string (date-time)|
|**id**  <br>*可选*|**样例** : `0`|integer (int32)|
|**price**  <br>*可选*|**样例** : `0`|integer (int32)|
|**purchaseTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**rid**  <br>*可选*|**样例** : `0`|integer (int32)|
|**uid**  <br>*可选*|**样例** : `0`|integer (int32)|


<a name="reply"></a>
### Reply
评论


|名称|说明|类型|
|---|---|---|
|**content**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `0`|integer (int32)|
|**rid**  <br>*可选*|**样例** : `0`|integer (int32)|
|**time**  <br>*可选*|评论时间  <br>**样例** : `"2020-11-22 12:23:11"`|string (date-time)|
|**uid**  <br>*可选*|**样例** : `0`|integer (int32)|


<a name="61e3f0cb484fc54999013e7b63698085"></a>
### ResponseResult«Artical»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[artical](#artical)"`|[Artical](#artical)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="e30ca2e1fbb092c982560abcffcf3bd6"></a>
### ResponseResult«ChatGroup»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[chatgroup](#chatgroup)"`|[ChatGroup](#chatgroup)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="4b20c52ffaf425ee794978c9584e73a9"></a>
### ResponseResult«Chat»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[chat](#chat)"`|[Chat](#chat)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="2306c090eda0ace4811155e2b2961fd9"></a>
### ResponseResult«ForumResource»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[forumresource](#forumresource)"`|[ForumResource](#forumresource)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="af4d76c2fd02cbff89dd897ee8435152"></a>
### ResponseResult«List«Artical»»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ "[artical](#artical)" ]`|< [Artical](#artical) > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="99f690e40505fb2bd447c2d40ce02ad9"></a>
### ResponseResult«List«ChatGroup»»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ "[chatgroup](#chatgroup)" ]`|< [ChatGroup](#chatgroup) > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="d4fd7cedcb8c5dc04f176888e43cbb73"></a>
### ResponseResult«List«Chat»»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ "[chat](#chat)" ]`|< [Chat](#chat) > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="40c17371c2d3c0df11edd8708ee94302"></a>
### ResponseResult«List«ForumResource»»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ "[forumresource](#forumresource)" ]`|< [ForumResource](#forumresource) > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="010091f7244d323dc6c6282d31d5c2db"></a>
### ResponseResult«List«GroupItem»»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ "[groupitem](#groupitem)" ]`|< [GroupItem](#groupitem) > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="b0ed0b1a956a48a8fe19ad33e3cf851b"></a>
### ResponseResult«List«Purchase»»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ "[purchase](#purchase)" ]`|< [Purchase](#purchase) > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="718a368ab83d8e8ae937319dbdec1ae5"></a>
### ResponseResult«List«Reply»»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ "[reply](#reply)" ]`|< [Reply](#reply) > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="ce985ad7d1f2ec7f4354e716a6c2b825"></a>
### ResponseResult«List«Zan»»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ "[zan](#zan)" ]`|< [Zan](#zan) > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="6f832e606553701536847de28df30e6d"></a>
### ResponseResult«List«int»»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ 0 ]`|< integer (int32) > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="1f5e32f207f2f322123647894a71a4ea"></a>
### ResponseResult«Purchase»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[purchase](#purchase)"`|[Purchase](#purchase)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="c01fdf3f9b88d406145d821dd854bd06"></a>
### ResponseResult«Reply»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[reply](#reply)"`|[Reply](#reply)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="65a4589c7cc60c7cdc4dc8c0ce8a6f4a"></a>
### ResponseResult«Upfile»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[upfile](#upfile)"`|[Upfile](#upfile)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="2fa1ffabb8c07cafb7a3ce222ff36c08"></a>
### ResponseResult«User_Info»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[user_info](#user_info)"`|[User_Info](#user_info)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="1a0d4ca65a630c3c812d7e1c11e0cdd8"></a>
### ResponseResult«User»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[user](#user)"`|[User](#user)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="4007e9bcd76c9e8104d0d22a80839973"></a>
### ResponseResult«Zan»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"[zan](#zan)"`|[Zan](#zan)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="0a2261a69c68d1053ed2559c393cc527"></a>
### ResponseResult«boolean»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `true`|boolean|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="3fe2e2379dbf233eedc7933fe7cf413b"></a>
### ResponseResult«int»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `0`|integer (int32)|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="2fcc07109ac56c98de62a28511fb6955"></a>
### ResponseResult«string»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"string"`|string|
|**msg**  <br>*可选*|**样例** : `"string"`|string|


<a name="upfile"></a>
### Upfile
上传的文件


|名称|说明|类型|
|---|---|---|
|**fileid**  <br>*可选*|**样例** : `0`|integer (int32)|
|**filename**  <br>*可选*|文件名  <br>**样例** : `"test.zip"`|string|
|**intro**  <br>*可选*|文件介绍  <br>**样例** : `"test intro"`|string|
|**keywords**  <br>*可选*|文件关键词  <br>**样例** : `"java"`|string|
|**purchasetime**  <br>*可选*|文件被购买次数  <br>**样例** : `0`|integer (int32)|
|**resourceid**  <br>*可选*|文件的资源ID  <br>**样例** : `0`|integer (int32)|
|**title**  <br>*可选*|文件标题  <br>**样例** : `"test title"`|string|


<a name="user"></a>
### User
用户类


|名称|说明|类型|
|---|---|---|
|**account**  <br>*可选*|**样例** : `"string"`|string|
|**email**  <br>*可选*|邮箱  <br>**样例** : `"513317651@qq.com"`|string|
|**name**  <br>*可选*|**样例** : `"string"`|string|
|**password**  <br>*可选*|**样例** : `"string"`|string|
|**uid**  <br>*可选*|**样例** : `0`|integer (int32)|


<a name="user_info"></a>
### User_Info
用户额外信息


|名称|说明|类型|
|---|---|---|
|**infoID**  <br>*可选*|info id  <br>**样例** : `0`|integer (int32)|
|**userBalance**  <br>*可选*|**样例** : `0`|integer (int32)|
|**userID**  <br>*可选*|**样例** : `0`|integer (int32)|
|**userIntro**  <br>*可选*|**样例** : `"string"`|string|
|**userPoint**  <br>*可选*|**样例** : `0`|integer (int32)|
|**userZan**  <br>*可选*|**样例** : `0`|integer (int32)|


<a name="zan"></a>
### Zan
赞


|名称|说明|类型|
|---|---|---|
|**id**  <br>*可选*|**样例** : `0`|integer (int32)|
|**rid**  <br>*可选*|**样例** : `0`|integer (int32)|
|**status**  <br>*可选*|**样例** : `0`|integer (int32)|
|**uid**  <br>*可选*|**样例** : `0`|integer (int32)|





