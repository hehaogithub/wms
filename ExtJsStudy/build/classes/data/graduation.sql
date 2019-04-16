---------------------------------------------------------
-- Export file for user HHE                            --
-- Created by Administrator on 2019/1/25 星期五, 11:47:37 --
---------------------------------------------------------

set define off
spool graduation.log

prompt
prompt Creating table OPERATE
prompt ======================
prompt
create table OPERATE
(
  operate_id     NUMBER(10) not null,
  operate_type   NUMBER(1),
  operate_time   DATE,
  product_name   VARCHAR2(20),
  repertory      VARCHAR2(10),
  location       NUMBER(3),
  product_type   VARCHAR2(20),
  product_number VARCHAR2(8)
)
;
comment on table OPERATE
  is '出入库操作表';
comment on column OPERATE.operate_id
  is '操作编号';
comment on column OPERATE.operate_type
  is '操作类型：0入库/1出库';
comment on column OPERATE.operate_time
  is '操作时间';
comment on column OPERATE.product_name
  is '产品名称';
comment on column OPERATE.repertory
  is '仓库名称';
comment on column OPERATE.location
  is '货架号';
comment on column OPERATE.product_type
  is '产品类型';
comment on column OPERATE.product_number
  is '产品数量';
alter table OPERATE
  add constraint OPERATE_KEY primary key (OPERATE_ID);

prompt
prompt Creating table PRODUCT
prompt ======================
prompt
create table PRODUCT
(
  product_id         NUMBER(8) not null,
  product_name       VARCHAR2(20) not null,
  product_number     NUMBER(5) not null,
  product_type       VARCHAR2(10) not null,
  product_singprice  NUMBER(20) not null,
  product_totalprice NUMBER(20) not null,
  product_buydate    DATE not null,
  product_factory    VARCHAR2(20) not null,
  product_indate     DATE not null
)
;
comment on column PRODUCT.product_id
  is '产品编号';
comment on column PRODUCT.product_name
  is '产品名称';
comment on column PRODUCT.product_number
  is '产品数量';
comment on column PRODUCT.product_type
  is '产品类型';
comment on column PRODUCT.product_singprice
  is '产品单价';
comment on column PRODUCT.product_totalprice
  is '产品总价';
comment on column PRODUCT.product_buydate
  is '产品购买时间';
comment on column PRODUCT.product_factory
  is '产品生产厂家';
comment on column PRODUCT.product_indate
  is '产品入库时间';
alter table PRODUCT
  add constraint PRODUCT_KEY primary key (PRODUCT_ID);

prompt
prompt Creating table REPERTORY
prompt ========================
prompt
create table REPERTORY
(
  repertory_id        NUMBER(10) not null,
  repertory_name      VARCHAR2(10) not null,
  repertory_locations NUMBER(4) not null
)
;
comment on column REPERTORY.repertory_id
  is '仓库编号';
comment on column REPERTORY.repertory_name
  is '仓库名';
comment on column REPERTORY.repertory_locations
  is '仓库货架数';
alter table REPERTORY
  add constraint REPERTORY_PKEY primary key (REPERTORY_ID);

prompt
prompt Creating table REPERTORY_USER
prompt =============================
prompt
create table REPERTORY_USER
(
  id           NUMBER(10) not null,
  repertory_id NUMBER(10) not null,
  user_id      NUMBER(10) not null
)
;
comment on column REPERTORY_USER.id
  is '编号';
comment on column REPERTORY_USER.repertory_id
  is '仓库编号';
comment on column REPERTORY_USER.user_id
  is '用户编号';

prompt
prompt Creating table RESOURE
prompt ======================
prompt
create table RESOURE
(
  resource_id   NUMBER(8) not null,
  resource_url  VARCHAR2(100) not null,
  resource_name VARCHAR2(20),
  leaf          NUMBER(1),
  pid           NUMBER(8)
)
;
comment on table RESOURE
  is '资源表（页面菜单）';
comment on column RESOURE.resource_id
  is '资源编号';
comment on column RESOURE.resource_url
  is '资源路径';
comment on column RESOURE.resource_name
  is '资源名';
comment on column RESOURE.leaf
  is '是否是叶子:0不是/1是';
comment on column RESOURE.pid
  is '父亲编号';
alter table RESOURE
  add constraint RESOURCE_PKEY primary key (RESOURCE_ID);

prompt
prompt Creating table ROLE
prompt ===================
prompt
create table ROLE
(
  role_id   NUMBER(10) not null,
  role_name VARCHAR2(20)
)
;
comment on table ROLE
  is '角色表';
comment on column ROLE.role_id
  is '角色编号';
comment on column ROLE.role_name
  is '角色姓名';
alter table ROLE
  add constraint ROLE_PKEY primary key (ROLE_ID);

prompt
prompt Creating table ROLE_RESOURCE
prompt ============================
prompt
create table ROLE_RESOURCE
(
  role_id     NUMBER(10) not null,
  resource_id NUMBER(10) not null
)
;
comment on table ROLE_RESOURCE
  is '角色资源表';
comment on column ROLE_RESOURCE.role_id
  is '角色编号';
comment on column ROLE_RESOURCE.resource_id
  is '资源编号';
alter table ROLE_RESOURCE
  add constraint ROLE_RESOURCE_PKEY primary key (ROLE_ID, RESOURCE_ID);

prompt
prompt Creating table SCORE
prompt ====================
prompt
create table SCORE
(
  sid       NUMBER(8) not null,
  sname     VARCHAR2(10),
  ssex      VARCHAR2(2),
  scomputer NUMBER(2),
  senglish  NUMBER(2)
)
;
alter table SCORE
  add constraint SCORE_PRI primary key (SID);

prompt
prompt Creating table USERS
prompt ====================
prompt
create table USERS
(
  user_id           NUMBER(10) not null,
  user_name         VARCHAR2(20) not null,
  user_loginaccount VARCHAR2(20) not null,
  user_password     VARCHAR2(20) not null,
  user_phone        NUMBER(11) not null,
  repertory_id      NUMBER(10) not null,
  role_id           NUMBER(10)
)
;
comment on column USERS.user_id
  is '用户编号，主键';
comment on column USERS.user_name
  is '用户姓名';
comment on column USERS.user_loginaccount
  is '登录账户';
comment on column USERS.user_password
  is '登录密码';
comment on column USERS.user_phone
  is '用户联系方式';
comment on column USERS.repertory_id
  is '用户所属仓库，外键';
comment on column USERS.role_id
  is '用户所属角色，外键';
alter table USERS
  add constraint USER_KEY primary key (USER_ID);
alter table USERS
  add constraint USER_FKEY1 foreign key (ROLE_ID)
  references ROLE (ROLE_ID);
alter table USERS
  add constraint USER_FKY2 foreign key (REPERTORY_ID)
  references REPERTORY (REPERTORY_ID);

prompt
prompt Creating sequence OPERATE_SEQ
prompt =============================
prompt
create sequence OPERATE_SEQ
minvalue 1000
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence PRODUCT_SEQ
prompt =============================
prompt
create sequence PRODUCT_SEQ
minvalue 1000
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence REPERTORY_SEQ
prompt ===============================
prompt
create sequence REPERTORY_SEQ
minvalue 0
maxvalue 9999999999999999999999999999
start with 0
increment by 1
cache 20;

prompt
prompt Creating sequence RESOURCE_SEQ
prompt ==============================
prompt
create sequence RESOURCE_SEQ
minvalue 100000
maxvalue 9999999999999999999999999999
start with 100000
increment by 1
cache 20;

prompt
prompt Creating sequence ROLE_SEQ
prompt ==========================
prompt
create sequence ROLE_SEQ
minvalue 1000
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
cache 20;

prompt
prompt Creating sequence SCORE_SEQ
prompt ===========================
prompt
create sequence SCORE_SEQ
minvalue 15058200
maxvalue 9999999999999999999999999999
start with 15058260
increment by 1
cache 20;

prompt
prompt Creating sequence USER_SEQ
prompt ==========================
prompt
create sequence USER_SEQ
minvalue 20190101
maxvalue 9999999999999999999999999999
start with 20190101
increment by 1
cache 20;


spool off
