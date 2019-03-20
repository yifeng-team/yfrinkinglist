-- 系统数据字典表
drop table shop_sys_dict cascade constraints;
create table shop_sys_dict (
  id number(9) not null,
  status varchar2(128) not null,
  name varchar2(200),
  value varchar2(128),
  sort number(5) default 0,
  createat date default sysdate,
  updateat date default sysdate,
  constraint pk_shop_sys_dict primary key(id)
);
comment on table shop_sys_dict is '系统数据字典表';
comment on column shop_sys_dict.id is '主键';
comment on column shop_sys_dict.status is '编码';
comment on column shop_sys_dict.name is '名称';
comment on column shop_sys_dict.value is '值';
comment on column shop_sys_dict.sort is '排序';
comment on column shop_sys_dict.createat is '创建日期，默认为系统当前时间';
comment on column shop_sys_dict.updateat is '记录更新时间，默认为系统当前时间';

-- 交接商品表
drop table staff_opt_goods cascade constraints;
create table staff_opt_goods (
  siteDetailId  char(32) default lower(sys_guid()) not null ,
  store_code varchar2(120) not null,
  store_fullname varchar2(200) not null, -- 门店名称
  good_code varchar2(80) not null, -- 商品编号
  goods_name varchar2(160) not null, -- 商品名称
  common_name varchar2(200) not null, -- 通用名
  sort number(5) default 0,
  createat date default sysdate,
  constraint pk_staff_opt_goods primary key(siteDetailId),
  constraint uk_staff_opt_store_goods_code unique(store_code,good_code)
);
comment on table staff_opt_goods is '交接商品表';
comment on column staff_opt_goods.siteDetailId is '主键';
comment on column staff_opt_goods.store_code is '门店编码';
comment on column staff_opt_goods.store_fullname is '门店全称';
comment on column staff_opt_goods.good_code is '商品编号';
comment on column staff_opt_goods.goods_name is '商品名称';
comment on column staff_opt_goods.common_name is '通用名';
comment on column staff_opt_goods.sort is '排序';
comment on column staff_opt_goods.createat is '创建时间';

-- 物理区域表
drop table shop_physical_region cascade constraints;
create table shop_physical_region (
  id number(10) not null, -- 区域ID
  region_code varchar2(32) not null, -- 区域编码
  region_name varchar2(120) not null, -- 区域名称
  pid number(10), -- 父级区域ID
  region_level number(5), -- 层级
  region_type char(2), --  类型(公司:0，门店:1，药品区:2，货位:3，货架:4，摆放面:5，摆放层:6)
  memo varchar2(200), -- 区域描述
  sort number(10) default 0, -- 排序
  constraint shop_physical_region primary key(id),
  constraint uk_shop_pr_region_code unique(region_code)
);
comment on table shop_physical_region is '物理区域表';
comment on column shop_physical_region.id is '区域ID';
comment on column shop_physical_region.region_code is '区域编码';
comment on column shop_physical_region.region_name is '区域名称';
comment on column shop_physical_region.pid is '父级区域ID';
comment on column shop_physical_region.region_level is '层级';
comment on column shop_physical_region.region_type is '类型(公司:0，门店:1，药品区:2，货位:3，货架:4，摆放面:5，摆放层:6)';
comment on column shop_physical_region.memo is '描述';
comment on column shop_physical_region.sort is '排序';


-- 商品位置表
drop table shop_position_goods cascade constraints;
create table shop_position_goods (
  siteDetailId   char(32) default lower(sys_guid()) not null,
  store_code varchar2(120) not null,-- 门店编码
  region_code varchar2(32) not null, -- 物理区域编码，注意必须是层级别
  goods_code varchar2(32) not null, -- 商品编码
  horizontal_num number(5) default 0 not null, -- 水平摆放序号
  vertical_count number(5) default 0 not null, -- 商品摆放数量
  show_type char(2) default '1', --  是否显示库存(不显示:0 显示:1)
  goods_from varchar2(2) not null, -- 资料来源:(1-execl 2-空间导入 3-手动调整)
  sender_code varchar2(80) not null,  -- 修改人员
  createat date default sysdate not null, -- 创建日期
  updateat date default sysdate not null, -- 修改日期
  memo varchar2(64), -- 描述
  constraint shop_position_goods primary key(siteDetailId)
);
comment on table shop_position_goods is '商品位置表';
comment on column shop_position_goods.siteDetailId is '主键ID';
comment on column shop_position_goods.store_code is '门店编号';
comment on column shop_position_goods.region_code is '物理区域编码，注意必须是层级别';
comment on column shop_position_goods.goods_code is '商品编码';
comment on column shop_position_goods.horizontal_num is '水平摆放序号';
comment on column shop_position_goods.vertical_count is '商品摆放数量';
comment on column shop_position_goods.show_type is '是否显示库存（0-不显示 1-显示）';
comment on column shop_position_goods.memo is '描述';
comment on column shop_position_goods.goods_from is '资料来源:(1-execl 2-空间导入 3-手动调整)';
comment on column shop_position_goods.sender_code is '修改人员';
comment on column shop_position_goods.createat is '创建日期';
comment on column shop_position_goods.updateat is '修改日期';

-- 盘点计划表
drop table staff_plan cascade constraints;
create table staff_plan (
  siteDetailId char(32) default lower(sys_guid()) not null ,
  store_code varchar2(120) not null, -- 门店编码
  store_fullname varchar2(200) not null, -- 门店全称
  plan_number varchar2(32) not null,-- 计划编码与门店联合唯一（年月日流水号）
  logic_area_code varchar2(32) not null, -- 盘点区域
  logic_area_name varchar2(120), -- 逻辑区域名称
  receiver_code varchar2(80) not null, -- 接班人
  receiver_name varchar2(120),-- 接班人姓名
  sender_code varchar2(80) not null, -- 交班人
  sender_name varchar2(120),-- 交班人姓名
  creater_code varchar2(80) not null,-- 创建者
  total_count number(5) default 0 not null, -- 此区域共计多少个商品
  total_differcount number(5) default 0 not null, -- 此区域共计多少个差异商品
  already_count number(5) default 0 not null, --已盘点的商品数
  plan_state char(2) default '0' not null,-- 计划状态:(0-盘点中 1-待提交 2-待提交确认 3-不同意交接 4-已关闭 5-已取消 6-已交接确认)
  sender_submit_date date, -- 交班人提交时间
  receiver_conform_date date , -- 接班人确认时间
  createat date default sysdate not null,
  updateat date default sysdate not null,
  constraint pk_staff_plan primary key(siteDetailId),
  constraint uk_staff_plan_store_number unique(store_code, plan_number)
);
comment on table staff_plan is '盘点计划表';
comment on column staff_plan.siteDetailId is '主键';
comment on column staff_plan.store_code is '门店编码';
comment on column staff_plan.store_fullname is '冗余门店全称';
comment on column staff_plan.plan_number is '计划编码与门店联合唯一（年月日流水号）:20171107001';
comment on column staff_plan.logic_area_code is '逻辑区域ID';
comment on column staff_plan.logic_area_name is '逻辑区域名称';
comment on column staff_plan.receiver_code is '接班人';
comment on column staff_plan.receiver_name is '接班人姓名';
comment on column staff_plan.sender_code is '交班人';
comment on column staff_plan.sender_name is '交班人姓名';
comment on column staff_plan.creater_code is '创建人';
comment on column staff_plan.total_count is '此区域共计多少个商品';
comment on column staff_plan.total_differcount is '此区域共计多少个差异商品';
comment on column staff_plan.already_count is '已经盘点的商品种类数';
comment on column staff_plan.plan_state is '计划状态:(0-盘点中 1-待提交 2-待提交确认 3-不同意交接 4-已关闭 5-已取消 6-已交接确认)';
comment on column staff_plan.sender_submit_date is '交班人提交时间';
comment on column staff_plan.receiver_conform_date is '接班人确认时间';
comment on column staff_plan.createat is '创建日期，默认为系统当前时间';
comment on column staff_plan.updateat is '记录更新时间，默认为系统当前时间';

-- 盘点任务表
drop table staff_plan_task cascade constraints;
create table staff_plan_task (
  siteDetailId   char(32) default lower(sys_guid()) not null ,
  store_code varchar2(120) not null, -- 门店编码
  plan_number varchar2(32) not null, -- 计划编号
  logic_area_code varchar2(32) not null, -- 逻辑区域编码
  physical_region_code varchar2(32) not null, -- 物理区域编码
  good_code varchar2(80) not null, -- 商品编号
  horizontal_num number(5) default 0 not null, -- 水平摆放序号
  vertical_count number(13,3) not null,
  sender_code varchar2(80) not null, -- 交班人编码
  receiver_code varchar2(80) not null, -- 接班人编码
  creater_code varchar2(80) not null, -- 创建者编码
  plan_state char(2) default '0' not null,-- 任务状态:(0-未处理 1-无差异 2-有差异 3-认可差异)
  createat date default sysdate not null,
  updateat date default sysdate not null,
  batch_code varchar2(80),
  constraint pk_staff_plan_task primary key(siteDetailId)
);
comment on table staff_plan_task is '盘点计划明细表';
comment on column staff_plan_task.siteDetailId is '主键';
comment on column staff_plan_task.store_code is '门店编码';
comment on column staff_plan_task.plan_number is '计划编号';
comment on column staff_plan_task.logic_area_code is '逻辑区域编码';
comment on column staff_plan_task.physical_region_code is '物理区域编码';
comment on column staff_plan_task.good_code is '商品编号';
comment on column staff_plan_task.horizontal_num is '水平摆放序号';
comment on column staff_plan_task.vertical_count is '商品库存数量';
comment on column staff_plan_task.sender_code is '交班人编码';
comment on column staff_plan_task.receiver_code is '接班人编码';
comment on column staff_plan_task.creater_code is '创建者编码';
comment on column staff_plan_task.plan_state is '任务状态:(0-未处理 1-无差异 2-有差异 3-认可差异)';
comment on column staff_plan_task.createat is '创建日期，默认为系统当前时间';
comment on column staff_plan_task.updateat is '记录更新时间，默认为系统当前时间';
comment on column staff_plan_task.batch_code is '盘点批次编号';

-- 盘点任务流水表
drop table staff_plan_water cascade constraints;
create table staff_plan_water (
  siteDetailId   char(32) default lower(sys_guid()) not null ,
  store_code varchar2(120) not null, -- 门店编码
  store_fullname varchar2(200) not null, -- 门店名称
  plan_number varchar2(32) not null, -- 计划编号
  plan_task_uuid varchar2(32) not null, -- 任务UUID
  logic_area_code varchar2(32) not null, -- 冗余，不做关联
  logic_area_name varchar2(120) not null, -- 逻辑区域名称
  main_barcode varchar2(80) not null, -- 商品条码
  good_code varchar2(80) not null, -- 商品编号
  goods_name varchar2(160) not null, -- 商品名称
  common_name varchar2(200) not null, -- 通用名
  physical_region_code varchar2(32) not null, -- 物理区域编码
  physical_region_name varchar2(120) not null, -- 物理区域名称
  horizontal_num number(5) default 0 not null, -- 水平摆放序号
  vertical_count number(13,3) not null,
  sender_code varchar2(80) not null, -- 冗余ID，不做关联
  sender_name varchar2(120),-- 交班人姓名
  receiver_code varchar2(80) not null, -- 冗余ID，不做关联
  receiver_name varchar2(120),-- 接班人姓名
  creater_code varchar2(80) not null, -- 创建者编码
  last_count number(13,3) default 0 not null,-- 最后一次盘点数量
  differ_count number(13,3) default 0 not null,-- 库存差值: 最后一次盘点 - 垂直商品摆放数量
  show_type char(2),-- 冗余是否显示库存(不显示:0 显示:1)
  createat date default sysdate not null,
  constraint pk_staff_plan_water primary key(siteDetailId)
);
comment on table staff_plan_water is '盘点计划明细表';
comment on column staff_plan_water.siteDetailId is '主键';
comment on column staff_plan_water.store_code is '门店编码，冗余ID，不做关联';
comment on column staff_plan_water.store_fullname is '门店全称';
comment on column staff_plan_water.plan_number is '计划编号';
comment on column staff_plan_water.plan_task_uuid is '任务编号';
comment on column staff_plan_water.logic_area_code is '逻辑区域编码，不做关联';
comment on column staff_plan_water.logic_area_name is '逻辑区域名称';
comment on column staff_plan_water.main_barcode is '商品条码';
comment on column staff_plan_water.good_code is '商品编号';
comment on column staff_plan_water.goods_name is '商品名称';
comment on column staff_plan_water.common_name is '通用名';
comment on column staff_plan_water.physical_region_code is '物理区域编码，不做关联';
comment on column staff_plan_water.physical_region_name is '物理区域名称';
comment on column staff_plan_water.horizontal_num is '水平摆放序号';
comment on column staff_plan_water.vertical_count is '商品库存数量，会有小数的情况';
comment on column staff_plan_water.sender_code is '交班人编码，冗余ID，不做关联';
comment on column staff_plan_water.sender_name is '交班人真实姓名';
comment on column staff_plan_water.receiver_code is '接班人编码，冗余ID，不做关联';
comment on column staff_plan_water.receiver_name is '接班人真实姓名';
comment on column staff_plan_water.creater_code is '创建者编码';
comment on column staff_plan_water.last_count is '最后一次盘点数量';
comment on column staff_plan_water.differ_count is '库存差值: 最后一次盘点 - 垂直商品摆放数量';
comment on column staff_plan_water.show_type is '冗余是否显示库存(不显示:0 显示:1)';
comment on column staff_plan_water.createat is '创建日期，默认为系统当前时间';
