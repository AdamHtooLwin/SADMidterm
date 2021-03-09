insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_USER');

insert into employee (name, level, birthday, base_salary) values ('Chaklam', 2, parsedatetime('17-09-1991', 'dd-MM-yyyy'), 5000);
insert into employee (name, level, birthday, base_salary) values ('John', 1, parsedatetime('27-01-1981', 'dd-MM-yyyy'), 3000);
insert into employee (name, level, birthday, base_salary) values ('Peter', 0, parsedatetime('11-09-1995', 'dd-MM-yyyy'), 2500);

-- admin user --
insert into user (emp_id, username, password, active, email) values (1, 'admin', '$2a$10$otMn8Xre2SYZm.cZwa4q8e7hq3Y7/gkGoD9FOMvVmRvPr75p3POLm', true, 'admin@ait.asia');
insert into user_roles (users_emp_id, roles_id) values (1, 1);

-- user -- 
insert into user (emp_id, username, password, active, email) values (2, 'user', '$2a$10$otMn8Xre2SYZm.cZwa4q8e7hq3Y7/gkGoD9FOMvVmRvPr75p3POLm', true, 'user@ait.asia');
insert into user_roles (users_emp_id, roles_id) values (2, 2);

-- user -- 
insert into user (emp_id, username, password, active, email) values (3, 'user2', '$2a$10$otMn8Xre2SYZm.cZwa4q8e7hq3Y7/gkGoD9FOMvVmRvPr75p3POLm', true, 'user2@ait.asia');
insert into user_roles (users_emp_id, roles_id) values (3, 2);

-- addresses --
insert into address (city, house_no, street, zip_code, emp_id) values ('Bangkok', '30/6', 'Ramindra', '10220', 1);
insert into address (city, house_no, street, zip_code, emp_id) values ('Bangkok', '30/7', 'Victory Monument', '12220', 2);
insert into address (city, house_no, street, zip_code, emp_id) values ('Bangkok', '41/8', 'Victory Monument', '12220', 3);
