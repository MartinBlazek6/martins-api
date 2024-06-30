-- db.migration/V5__create_role_table.sql
CREATE TABLE role (
                      role_name VARCHAR(255) PRIMARY KEY,
                      role_description VARCHAR(255)
);
