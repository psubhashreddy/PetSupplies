/* INSERT ROLES */
insert into roles (role_id, role_name, role_desc) values (1, 'admin', 'administrators')
insert into roles (role_id, role_name, role_desc) values (2, 'user', 'users')

/* INSERT USERS */
insert into users(user_id,role_id,first_name,last_name,user_name,password,address,city,state,country,postal_code,phone,email) values(1,1,'subhash','peddyreddy','subhash','subhash','airoli','navi mumbai','maharastra','india','400079','9704854352','subhash1@yahoo.com')
insert into users(user_id,role_id,first_name,last_name,user_name,password,address,city,state,country,postal_code,phone,email) values(2,2,'subhash','peddyreddy','subhash1','subhash1','airoli','navi mumbai','maharastra','india','400079','9704854350','subhash@yahoo.com')