
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



