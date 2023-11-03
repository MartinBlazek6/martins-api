-- db.migration/V3__create_employments_table.sql
CREATE TABLE employment (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             project_name VARCHAR(255),
                             technologies VARCHAR(255),
                             position VARCHAR(255),
                             duration VARCHAR(255),
                             resume_id INT,
                             FOREIGN KEY (resume_id) REFERENCES resume(id)
);
