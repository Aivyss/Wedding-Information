-- 회원정보 테이블
create table member (
    id  varchar2(50) constraint id_primary_key primary key
    ,password varchar2(50) constraint password_not_null not null
    ,name varchar2(50) constraint name_not_null not null
    ,age number(3) constraint age_not_null not null
    ,sex number(1) constraint sex_not_null not null
    ,bmi number(6,3) constraint bmi_not_null not null
    ,height number(3) constraint height_not_null not null
    ,salary number(16) default 0 constraint salary_not_null not null
    ,latest_edu varchar2(50) constraint latest_edu_not_null not null
    ,latest_edu_index number(2) default 7 constraint latest_edu_index_ck check(latest_edu_index <=7 and latest_edu_index >=1)
    ,grade_index number(2) default 0 constraint grade_index_fk references grade(grade_index) on delete cascade
    ,total_score number(5) default 0 constraint total_score_not_null not null
    ,normalized_total_score number(7,3) default 0 constraint norm_total_score_not_null not null
);

create table male_table(
    id  varchar2(50) constraint id_fk_male_table references member(id) on delete cascade
    ,taco number(1) default 0 constraint taco_not_null not null 
);

create table female_table(
    id  varchar2(50) constraint id_fk_female_table references member(id) on delete cascade
    ,surgery number(1) default 4 constraint surgery_not_null not null
);

-- 매칭과 계정블럭 테이블
create table match_and_lock(
    id varchar2(50) constraint id_match_and_lock_fk references member(id) on delete cascade
    ,matched_id varchar2(50)
    ,invited number(1) default 0 constraint invited_not_null not null
    ,match_lock number(1) default 0 constraint match_lock_not_null not null
    ,success number(1) default 0 constraint success_not_null not null
    ,account_lock number(1) default 0 constraint account_lock_not_null not null
    ,lock_count number(1) default 0 constraint lock_count_not_null not null
);

-- 등급 테이블 생성 sql 구문
create table grade(
    grade_index number(2) constraint grade_index_pk primary key
    ,grade varchar2(20) constraint grade_not_null not null
);

-- 등급 테이블 등급 분류
insert into grade(grade_index ,grade) values (0, '언랭');
insert into grade(grade_index ,grade) values (1, '브론즈');
insert into grade(grade_index ,grade) values (2, '실버');
insert into grade(grade_index ,grade) values (3, '골드');
insert into grade(grade_index ,grade) values (4, '플래티넘');
insert into grade(grade_index ,grade) values (5, '다이아');
insert into grade(grade_index ,grade) values (6, '비브라늄');

-- 충전금 테이블
create table cash_table(
    id  varchar2(50) constraint id_fk_cash_table references member(id) on delete cascade
    ,cash number(16) default 0
);

-- 테이블 드랍
drop table member;
drop table male_table;
drop table female_table;
drop table match_and_lock;
drop table cash_table;
drop table grade;

-- 제약사항을 확인하는 sql 구문
select
    owner
    ,constraint_name
    ,constraint_type
    ,table_name
from
    user_constraints;
    
update match_and_lock
	set lock_count=1
where
	id = '8';