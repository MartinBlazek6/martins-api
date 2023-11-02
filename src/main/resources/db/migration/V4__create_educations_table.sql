-- db.migration/V4__create_educations_table.sql
CREATE TABLE educations (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255),
                            description VARCHAR(255),
                            duration VARCHAR(50),
                            resume_id INT,
                            FOREIGN KEY (resume_id) REFERENCES resume(id)
);
