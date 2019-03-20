--查看被锁的表
select b.owner,b.object_name,a.session_id,a.locked_mode from v$locked_object a,dba_objects b where b.object_id = a.object_id;

--查看那个用户那个进程照成死锁
select b.username,b.sid,b.serial#,logon_time from v$locked_object a,v$session b where a.session_id = b.sid order by b.logon_time;

--查看连接的进程
SELECT sid, serial#, username, osuser FROM v$session;

--3.查出锁定表的sid, serial#,os_user_name, machine_name, terminal，锁的type,mode
SELECT s.sid, s.serial#, s.username, s.schemaname, s.osuser, s.process, s.machine,
s.terminal, s.logon_time, l.type
FROM v$session s, v$lock l
WHERE s.sid = l.sid
AND s.username IS NOT NULL
ORDER BY sid;

-- 这个语句将查找到数据库中所有的DML语句产生的锁，还可以发现，
-- 任何DML语句其实产生了两个锁，一个是表锁，一个是行锁。

--杀掉进程 sid,serial#
alter system kill session'210,11562';


-- Oracle的锁表与解锁
SELECT /*+ rule */ s.username,
decode(l.type,'TM','TABLE LOCK',
'TX','ROW LOCK',
NULL) LOCK_LEVEL,
o.owner,o.object_name,o.object_type,
s.sid,s.serial#,s.terminal,s.machine,s.program,s.osuser
FROM v$session s,v$lock l,dba_objects o
WHERE l.sid = s.sid
AND l.id1 = o.object_id(+)
AND s.username is NOT Null

--kill session语句
alter system kill session'50,492';
--以下几个为相关表
SELECT * FROM v$lock;
SELECT * FROM v$sqlarea;
SELECT * FROM v$session;
SELECT * FROM v$process ;
SELECT * FROM v$locked_object;
SELECT * FROM all_objects;
SELECT * FROM v$session_wait;
--1.查出锁定object的session的信息以及被锁定的object名
SELECT l.session_id sid, s.serial#, l.locked_mode,l.oracle_username,
l.os_user_name,s.machine, s.terminal, o.object_name, s.logon_time
FROM v$locked_object l, all_objects o, v$session s
WHERE l.object_id = o.object_id
AND l.session_id = s.sid
ORDER BY sid, s.serial# ;
--2.查出锁定表的session的sid, serial#,os_user_name, machine name, terminal和执行的语句
--比上面那段多出sql_text和action
SELECT l.session_id sid, s.serial#, l.locked_mode, l.oracle_username, s.user#,
l.os_user_name,s.machine, s.terminal,a.sql_text, a.action
FROM v$sqlarea a,v$session s, v$locked_object l
WHERE l.session_id = s.sid
AND s.prev_sql_addr = a.address
ORDER BY sid, s.serial#;
--3.查出锁定表的sid, serial#,os_user_name, machine_name, terminal，锁的type,mode
SELECT s.sid, s.serial#, s.username, s.schemaname, s.osuser, s.process, s.machine,
s.terminal, s.logon_time, l.type
FROM v$session s, v$lock l
WHERE s.sid = l.sid
AND s.username IS NOT NULL
ORDER BY sid;

-- 这个语句将查找到数据库中所有的DML语句产生的锁，还可以发现，
-- 任何DML语句其实产生了两个锁，一个是表锁，一个是行锁。
-- 杀锁命令
alter system kill session 'sid,serial#'
SELECT /*+ rule */ s.username,
decode(l.type,'TM','TABLE LOCK',
'TX','ROW LOCK',
NULL) LOCK_LEVEL,
o.owner,o.object_name,o.object_type,
s.sid,s.serial#,s.terminal,s.machine,s.program,s.osuser
FROM v$session s,v$lock l,dba_objects o
WHERE l.sid = s.sid
AND l.id1 = o.object_id(+)
AND s.username is NOT NULL

-- 如果发生了锁等待，我们可能更想知道是谁锁了表而引起谁的等待
-- 以下的语句可以查询到谁锁了表，而谁在等待。
-- 以上查询结果是一个树状结构，如果有子节点，则表示有等待发生。
-- 如果想知道锁用了哪个回滚段，还可以关联到V$rollname，其中xidusn就是回滚段的USN
col user_name format a10
col owner format a10
col object_name format a10
col object_type format a10
SELECT /*+ rule */ lpad(' ',decode(l.xidusn ,0,3,0))||l.oracle_username User_name,
o.owner,o.object_name,o.object_type,s.sid,s.serial#
FROM v$locked_object l,dba_objects o,v$session s
WHERE l.object_id=o.object_id
AND l.session_id=s.sid
ORDER BY o.object_id,xidusn DESC

