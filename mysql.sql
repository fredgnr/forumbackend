/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/12/3 13:22:32                           */
/*==============================================================*/
use forum;

drop table if exists Chat;
drop table if exists GroupItem;
drop table if exists chatgroup;

drop table if exists zan;
drop table if exists upfile;
drop table if exists article;
drop table if exists reply;
drop table if exists purchase;
drop table if exists resources;
drop table if exists section;
drop table if exists user_info;
drop table if exists Users;


/*==============================================================*/
/* Table: Chat                                                  */
/*==============================================================*/
create table Chat
(
   CID                  int not null  auto_increment,
   receiveUID           int,
   sendUID              int,
   GroupID              int,
   message              varchar(1000),
   mtype                int,
   CreateTime           datetime,
   primary key (CID)
);

/*==============================================================*/
/* Table: GroupItem                                             */
/*==============================================================*/
create table GroupItem
(
   ID                   int not null  auto_increment,
   uid                  int,
   GID                  int,
   primary key (ID)
);
/*==============================================================*/
/* Table: Users                                                 */
/*==============================================================*/
create table Users
(
   uid                  int not null  auto_increment,
   user_account         varchar(20) not null,
   user_name            varchar(20) not null,
   user_password        varchar(20) not null,
   user_email           varchar(40) not null,
   primary key (uid)
);

/*==============================================================*/
/* Table: chatgroup                                             */
/*==============================================================*/
create table chatgroup
(
   GID                  int not null  auto_increment,
   Groupname            varchar(50),
   Introduce            varchar(500),
   create_time			datetime,
   primary key (GID)
);


/*==============================================================*/
/* Table: user_info                                             */
/*==============================================================*/
create table user_info
(
   info_id              int not null  auto_increment,
   user_id              int,
   user_intro           varchar(500),
   user_zan             int,
   user_point           int,
   user_balance         int,
   primary key (info_id)
);
/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   artical_id          int  auto_increment not null,
   article_resource_id  int  not null,
   article_title        varchar(200) not null,
   article_keywords     varchar(100) ,
   article_intro        varchar(300) not null,
   article_content      longtext not null,
   article_view         int not null,
   created_time datetime,
   last_reply_time datetime,
   primary key(artical_id )
);

/*==============================================================*/
/* Table: reply                                                 */
/*==============================================================*/
create table reply
(
   reply_id             int not null  auto_increment,
   reply_resource_id    int,
   reply_uid            int,
   reply_content        varchar(2000),
   reply_time           datetime,
   primary key (reply_id)
);

/*==============================================================*/
/* Table: resources                                             */
/*==============================================================*/
create table resources
(
   resource_id          int not null  auto_increment,
   resource_section_id  int,
   resource_user_id     int,
   resource_zan 		int,
   resource_last_reply_uid int,
   resource_type        int,
   resource_created_time datetime,
   resource_price       int,
   resource_last_reply_time datetime,
   primary key (resource_id)
);

/*==============================================================*/
/* Table: section                                               */
/*==============================================================*/
create table section
(
   section_id           int not null  auto_increment,
   section_name         varchar(40),
   section_count        int,
   primary key (section_id)
);

/*==============================================================*/
/* Table: upfile                                                */
/*==============================================================*/
create table upfile
(
   upfile_id            int not null  auto_increment,
   upfile_resource_id   int,
   upfile_path          varchar(200),
   upfile_title         varchar(200),
   upfile_filename      varchar(200),
   upfile_keywords      varchar(200),
   upfile_intro         varchar(300),
   purchase_time		int,
   created_time datetime,
   primary key (upfile_id)
);



/*==============================================================*/
/* Table: zan                                                   */
/*==============================================================*/
create table zan
(
   zan_id               int not null  auto_increment,
   zan_resource_id      int,
   zan_uid              int,
   zan_status           int,
   primary key (zan_id)
);

create table purchase(
	purchase_id         int not null  auto_increment,
    purchase_resource_id      int,
    purchase_user_id 				int,
    purchase_price				int,
    purchase_time    			datetime,
    primary key(purchase_id)
);

alter table article add constraint FK_Reference_5 foreign key (artical_id)
      references resources (resource_id) on delete restrict on update restrict;

alter table reply add constraint FK_Reference_7 foreign key (reply_resource_id)
      references resources (resource_id) on delete restrict on update restrict;

alter table reply add constraint FK_Reference_8 foreign key (reply_uid)
      references Users (uid) on delete restrict on update restrict;

alter table resources add constraint FK_Reference_2 foreign key (resource_section_id)
      references section (section_id) on delete restrict on update restrict;

alter table resources add constraint FK_Reference_3 foreign key (resource_user_id)
      references Users (uid) on delete restrict on update restrict;

alter table resources add constraint FK_Reference_4 foreign key (resource_last_reply_uid)
      references Users (uid) on delete restrict on update restrict;

alter table upfile add constraint FK_Reference_6 foreign key (upfile_resource_id)
      references resources (resource_id) on delete restrict on update restrict;

alter table user_info add constraint FK_Reference_1 foreign key (user_id)
      references Users (uid) on delete restrict on update restrict;

alter table zan add constraint FK_Reference_10 foreign key (zan_uid)
      references Users (uid) on delete restrict on update restrict;

alter table zan add constraint FK_Reference_9 foreign key (zan_resource_id)
      references resources (resource_id) on delete restrict on update restrict;
      
alter table purchase add constraint FK_Reference_11 foreign key (purchase_resource_id)
      references resources (resource_id) on delete restrict on update restrict;

alter table purchase add constraint FK_Reference_12 foreign key (purchase_user_id)
      references  Users (uid) on delete restrict on update restrict;
      


#test
alter table Chat add constraint FK_Reference_a foreign key (receiveUID)
      references Users (uid) on delete restrict on update restrict;

alter table Chat add constraint FK_Reference_b foreign key (sendUID)
      references Users (uid) on delete restrict on update restrict;

alter table Chat add constraint FK_Reference_c foreign key (GroupID)
      references chatgroup (GID) on delete restrict on update restrict;

alter table GroupItem add constraint FK_Reference_d foreign key (uid)
      references Users (uid) on delete restrict on update restrict;

alter table GroupItem add constraint FK_Reference_e foreign key (GID)
      references chatgroup (GID) on delete restrict on update restrict;
insert into section(section_name,section_count) value("JAVA",0);