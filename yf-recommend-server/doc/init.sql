-- 性别
insert into shop_sys_dict(id,status,name,value,sort) values (1,'user-sex', '女', 'F', '1');
insert into shop_sys_dict(id,status,name,value,sort) values (2,'user-sex', '男', 'M', '2');
insert into shop_sys_dict(id,status,name,value,sort) values (3,'user-sex', '保密', 'S', '3');

-- 计划状态
insert into shop_sys_dict (id,status, name, value, sort) values (4,'plan_state', '盘点中', '0', 1);
insert into shop_sys_dict (id,status, name, value, sort) values (5,'plan_state', '待提交', '1', 2);
insert into shop_sys_dict (id,status, name, value, sort) values (6,'plan_state', '待提交确认', '2', 3);
insert into shop_sys_dict (id,status, name, value, sort) values (7,'plan_state', '不同意交接', '3', 4);
insert into shop_sys_dict (id,status, name, value, sort) values (8,'plan_state', '已关闭', '4', 5);
insert into shop_sys_dict (id,status, name, value, sort) values (9,'plan_state', '已取消', '5', 6);
insert into shop_sys_dict (id,status, name, value, sort) values (10,'plan_state', '已交接确认', '6', 7);
