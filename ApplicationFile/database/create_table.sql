use news;
create table category
(
  id bigint auto_increment
    primary key,
  name varchar(100) null
);

create table news
(
  id bigint auto_increment
    primary key,
  title varchar(255) charset utf8 null,
  shortDescription text null,
  content longtext null,
  createdBy varchar(100) charset utf8 null,
  createdDate timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  modifiedBy varchar(100) charset utf8 null,
  modifiedDate timestamp default '0000-00-00 00:00:00' not null,
  censor varchar(100) charset utf8 null,
  thumnail varchar(255) null,
  status varchar(50) null,
  categoryID bigint null,
  constraint fk_category_news
  foreign key (categoryID) references category (id)
);

create table role
(
  roleid bigint auto_increment
    primary key,
  rolename varchar(100) null
);

create table user
(
  userid bigint auto_increment
    primary key,
  username varchar(255) not null,
  password varchar(255) not null,
  firstname varchar(50) null,
  lastname varchar(50) null,
  createddate timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
  email varchar(255) null,
  sex tinyint null,
  roleid bigint null,
  constraint fk_user_role
  foreign key (roleid) references role (roleid)
);