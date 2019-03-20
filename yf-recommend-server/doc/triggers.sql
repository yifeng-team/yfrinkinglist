-- 字典表触发器
/*
create or replace trigger trg_shop_sys_dict_id
  before insert on shop_sys_dict
  for each row
declare
  nextid number;
begin
  IF :new.id IS NULL or :new.id=0 THEN
    select seq_shop_sys_dict.nextval
    into nextid
    from sys.dual;
    :new.id:=nextid;
  end if;
end trg_sys_dict_id;
*/

-- 物理区域表触发器
/*
create or replace trigger trg_shop_physical_region_id
  before insert on shop_physical_region
  for each row
declare
  nextid number;
begin
  IF :new.id IS NULL or :new.id=0 THEN
    select seq_shop_physical_region.nextval
    into nextid
    from sys.dual;
    :new.id:=nextid;
  end if;
end trg_shop_physical_region_id;
*/
