-- 회원정보 테이블
drop table member;
create table member (
    id  varchar2(50) constraint id_primary_key primary key
    ,password varchar2(50) constraint password_not_null not null
    ,name varchar2(50) constraint name_not_null not null
    ,age number(3) constraint age_not_null not null
    ,bmi number(6,3) constraint bmi_not_null not null
    ,height number(3) constraint height_not_null not null
    ,salary number(16) default 0 constraint salary_not_null not null
    ,latest_edu varchar2(50) constraint latest_edu_not_null not null
    ,cash number(16) default 0 constraint cash_not_null not null
    ,latest_edu_index number(2) default 7 constraint latest_edu_index_ck check(latest_edu_index <=7 and latest_edu_index >=1)
    ,grade_index number(2) default 6 constraint grade_index_ck check(grade_index <=6 and grade_index >=0)
    ,total_score number(5) default 0 constraint total_score_not_null not null
    ,normalized_total_score number(7,3) default 0 constraint norm_total_score_not_null not null
);
drop table male_table;
create table male_table(
    id  varchar2(50) constraint id_fk_male_table references member(id)
    ,taco number(1) default 0 constraint taco_not_null not null 
);

drop table female_table;
create table female_table(
    id  varchar2(50) constraint id_fk_female_table references member(id)
    ,surgery number(1) default 4 constraint surgery_not_null not null
);

-- 매칭과 계정블럭 테이블
drop table match_and_lock;
create table match_and_lock(
    id varchar2(50) constraint id_match_and_lock_fk references member(id)
    ,matched_id varchar2(50)
    ,invited number(1) default 0 constraint invited_not_null not null
    ,match_lock number(1) default 0 constraint match_lock_not_null not null
    ,success number(1) default 0 constraint success_not_null not null
    ,account_lock number(1) default 0 constraint account_lock_not_null not null
    ,lock_count number(1) default 0 constraint lock_count_not_null not null
);

-- 등급 테이블 생성 sql 구문
drop table grade_table;
create table grade_table(
    grade_index number(1) default 0 constraint grade_pk primary key
    ,grade varchar2(20) default '언랭' constraint grade_not_null not null
);

-- 등급 테이블 등급 분류
insert into grade_table(grade_index ,grade) values (0, '언랭');
insert into grade_table(grade_index ,grade) values (1, '브론즈');
insert into grade_table(grade_index ,grade) values (2, '실버');
insert into grade_table(grade_index ,grade) values (3, '골드');
insert into grade_table(grade_index ,grade) values (4, '플래티넘');
insert into grade_table(grade_index ,grade) values (5, '다이아');
insert into grade_table(grade_index ,grade) values (6, '비브라늄');

-- 제약사항을 확인하는 sql 구문
select
    owner
    ,constraint_name
    ,constraint_type
    ,table_name
from
    user_constraints;