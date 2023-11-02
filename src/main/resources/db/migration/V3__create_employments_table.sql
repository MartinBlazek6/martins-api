-- db.migration/V3__create_employments_table.sql
CREATE TABLE employments (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             projectName VARCHAR(255),
                             description VARCHAR(255),
                             resume_id INT,
                             FOREIGN KEY (resume_id) REFERENCES resume(id)
);
