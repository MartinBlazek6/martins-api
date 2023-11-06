CREATE TABLE hard_skills (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             technology VARCHAR(255),
                             level  VARCHAR(255),
                             resume_id INT,
                             FOREIGN KEY (resume_id) REFERENCES resume(id)
);
