CREATE TABLE user_role (
                           user_id INT,
                           role_name VARCHAR(255),
                           PRIMARY KEY (user_id, role_name),
                           FOREIGN KEY (user_id) REFERENCES user(id),
                           FOREIGN KEY (role_name) REFERENCES role(role_name)
);
