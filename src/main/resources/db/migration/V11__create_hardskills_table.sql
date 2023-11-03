CREATE TABLE hard_skills (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           technology VARCHAR(255),
                           level INT,
                           resume_id INT,
                           FOREIGN KEY (resume_id) REFERENCES resume(id)
);
